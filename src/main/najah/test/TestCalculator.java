package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCalculator {

    static Calculator calc;

    @BeforeAll
    static void initAll() {
        calc = new Calculator();
        System.out.println("@BeforeAll - Calculator setup complete\n");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("\n@AfterAll - Calculator teardown complete");
    }

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach - preparing test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach - test finished");
    }

    // ── add ──────────────────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Add two positive numbers")
    void testAddTwoNumbers() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    @Order(2)
    @DisplayName("Add with no arguments returns 0") // When calling calc.add() with no arguments(empty array).
    void testAddNoArgs() {
        assertEquals(0, calc.add());
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Parameterized add: a + b = expected")
    @CsvSource({"1000,555,1555", "0,0,0", "5,5,10", "-12,1,-11"})
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    // ── divide ───────────────────────────────────────────────────────────────

    @Test
    @Order(4)
    @DisplayName("Divide valid numbers")
    void testDivide() {
        assertEquals(5, calc.divide(10, 2));
        assertEquals(3, calc.divide(9, 3));
    }

    @Test
    @Order(5)
    @DisplayName("Divide by zero throws ArithmeticException")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    // ── factorial ────────────────────────────────────────────────────────────

    @Test
    @Order(6)
    @DisplayName("Factorial of 0 and positive numbers")
    void testFactorial() {
        assertEquals(1,   calc.factorial(0));
        assertEquals(1,   calc.factorial(1));
        assertEquals(120, calc.factorial(5));
    }

    @Test
    @Order(7)
    @DisplayName("Factorial of negative input throws IllegalArgumentException")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }

    // ── timeout ──────────────────────────────────────────────────────────────

    @Test
    @Order(8)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Add completes within 500 milliseconds(half a second)")
    void testAddTimeout() {
        assertEquals(1500, calc.add(100, 200, 300, 400, 500));
    }

    // ── disabled (intentionally failing) ─────────────────────────────────────

    @Test
    @Order(9)
    @Disabled("Intentionally failing: expects 99 but add(1,2)=3. Fix: change expected value to 3.")
    @DisplayName("Intentionally failing test (disabled)")
    void testIntentionallyFailing() {
        assertEquals(99, calc.add(1, 2)); // wrong expected value on purpose
    }
}