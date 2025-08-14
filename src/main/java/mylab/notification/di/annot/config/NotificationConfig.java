package mylab.notification.di.annot.config;

import mylab.notification.di.annot.config.NotificationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public NotificationManager notificationManager() {
        return new NotificationManager(emailNotificationService(), smsNotificationService());
    }

    @Bean
    public EmailNotificationService emailNotificationService() {
        return new EmailNotificationService("smtp.gmail.com", 587);
    }

    @Bean
    public SmsNotificationService smsNotificationService() {
        return new SmsNotificationService("SKT");
    }
}

