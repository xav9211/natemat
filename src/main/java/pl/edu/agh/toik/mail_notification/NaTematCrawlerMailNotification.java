package main.java.pl.edu.agh.toik.mail_notification;

import main.java.pl.edu.agh.toik.mail_notification.service.MailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaTematCrawlerMailNotification {

    @Autowired
    private MailNotificationService mailNotificationService;

    public MailNotificationService getMailNotificationService() {
        return mailNotificationService;
    }
}
