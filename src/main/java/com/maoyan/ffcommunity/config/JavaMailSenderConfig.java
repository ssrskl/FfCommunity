package com.maoyan.ffcommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailSenderConfig {
    /**
     * 配置JavaMailSender
     *
     * @return
     */
    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("1071352028@qq.com");
        javaMailSender.setPassword("ngdfnbqvzgnrbfha");
        javaMailSender.setJavaMailProperties(new java.util.Properties() {{
            put("mail.smtp.auth", true);
            put("mail.smtp.ssl.enable", true);
        }});
        return javaMailSender;
    }
}
