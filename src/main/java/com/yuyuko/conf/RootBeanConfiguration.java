package com.yuyuko.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
        basePackages = "com.yuyuko.*",
        excludeFilters = {
                @ComponentScan.Filter(Controller.class),
        }
)
public class RootBeanConfiguration {

}
