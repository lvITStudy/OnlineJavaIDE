package com.yuyuko.service;

import com.yuyuko.compile.JavaSourceCompiler;
import com.yuyuko.execute.JavaClassExecuter;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.List;
import java.util.concurrent.*;

@Service
public class JavaExecuterService {
    public static final int TIME_LIMITED = 5;

    private ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(25));

    public String execute(String source){
        DiagnosticCollector<JavaFileObject> compileCollector = new DiagnosticCollector<>(); // 编译结果收集器

        // 编译源代码
        byte[] classBytes = JavaSourceCompiler.compile(source, compileCollector);

        // 编译不通过，获取并返回编译错误信息
        if (classBytes == null) {
            // 获取编译错误信息
            List<Diagnostic<? extends JavaFileObject>> compileError = compileCollector.getDiagnostics();
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".");
                compileErrorRes.append(System.lineSeparator());
            }
            return compileErrorRes.toString();
        }
        Future<String> future = taskExecutor.submit(()->JavaClassExecuter.execute(classBytes));

        String res;
        try{
            res = future.get(TIME_LIMITED, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            res = "Execution interrupted";
        } catch (ExecutionException e) {
            res = e.getCause().getMessage();
        } catch (TimeoutException e) {
            res = "Time Limit Exceeded";
        }finally {
            future.cancel(true);
        }
        return res;
    }
}
