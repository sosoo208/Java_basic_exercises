package mylab.notification.di.annot.config;

import mylab.notification.di.annot.config.NotificationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {
        assertNotNull(notificationManager);

        EmailNotificationService emailService = notificationManager.getEmailService();
        assertNotNull(emailService);
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        SmsNotificationService smsService = notificationManager.getSmsService();
        assertNotNull(smsService);
        assertEquals("SKT", smsService.getProvider());

        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}
