package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserService Tests")
@Execution(ExecutionMode.CONCURRENT)
public class TestUserService {

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    // ── isValidEmail ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Valid email returns true")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("test@example.com"));
    }

    @Test
    @DisplayName("Null email returns false")
    void testNullEmail() {
        assertFalse(userService.isValidEmail(null));
    }

    @ParameterizedTest
    @DisplayName("Parameterized: various invalid emails return false")
    @ValueSource(strings = {"notemail", "@nodomain", "noat.com", ""})
    void testInvalidEmailsParameterized(String email) {
        assertFalse(userService.isValidEmail(email));
    }

    // ── authenticate ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Correct credentials authenticate successfully")
    void testAuthenticateValid() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Wrong password returns false")
    void testAuthenticateWrongPassword() {
        assertFalse(userService.authenticate("admin", "wrong2005pass"));
    }

    @Test
    @DisplayName("Wrong username returns false")
    void testAuthenticateWrongUsername() {
        assertFalse(userService.authenticate("hammooz", "1234"));
    }

    // ── timeout ──────────────────────────────────────────────────────────────

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("authenticate completes within 1-second timeout")
    void testAuthenticateTimeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }
}