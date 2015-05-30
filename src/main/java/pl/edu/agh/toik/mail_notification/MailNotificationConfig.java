package main.java.pl.edu.agh.toik.mail_notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan("main.java.pl.edu.agh.toik.mail_notification")
public class MailNotificationConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        mailProperties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        mailProperties.put("mail.smtp.socketFactory.class", env.getProperty("mail.smtp.socketFactory.class"));
        javaMailSender.setJavaMailProperties(mailProperties);

        javaMailSender.setHost(env.getProperty("mail.host"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        javaMailSender.setProtocol(env.getProperty("mail.protocol"));
        javaMailSender.setUsername(env.getProperty("mail.username"));
        javaMailSender.setPassword(env.getProperty("mail.password"));

        return javaMailSender;
    }

}
