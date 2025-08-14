package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        assertNotNull(userService, "UserService should be injected");

        assertNotNull(userService.getUserRepository(), "UserRepository should be injected");

        assertEquals("MySQL", userService.getUserRepository().getDbType());

        assertNotNull(userService.getSecurityService(), "SecurityService should be injected");

        assertTrue(userService.registerUser("hong", "홍길동", "1234"), "User should be registered");
    }
}
