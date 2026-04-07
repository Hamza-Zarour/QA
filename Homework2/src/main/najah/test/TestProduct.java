package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Tests")
public class TestProduct {

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Apple Watch", 1000.0);
    }

    // ── constructor ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Valid product has correct name and price")
    void testValidProduct() {
        assertEquals("Apple Watch", product.getName());
        assertEquals(1000.0, product.getPrice());
    }

    @Test
    @DisplayName("Negative price throws IllegalArgumentException")
    void testNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Bad Watch", -1.0));
    }

    // ── applyDiscount ────────────────────────────────────────────────────────

    @Test
    @DisplayName("Apply valid discount updates discount and final price")
    void testApplyDiscount() {
        product.applyDiscount(10);
        assertEquals(10.0, product.getDiscount());
        assertEquals(900.0, product.getFinalPrice(),0.001); //delta(0.001) is a margin of error when comparing two double values (needed in Java).
    }

    @Test
    @DisplayName("Discount above 50 or below 0 throws IllegalArgumentException")
    void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(55));
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
        assertEquals(0, product.getDiscount());
    }

    @ParameterizedTest
    @DisplayName("Parameterized: discount percentage produces correct final price")
    @CsvSource({"0,1000.0", "10,900.0", "50,500.0", "25,750.0"})
    void testDiscountParameterized(double discount, double expected) {
        product.applyDiscount(discount);
        assertEquals(expected, product.getFinalPrice(), 0.001);
    }

    // ── getFinalPrice ────────────────────────────────────────────────────────

    @Test
    @DisplayName("getFinalPrice with no discount equals original price")
    void testGetFinalPriceNoDiscount() {
        assertEquals(1000.0, product.getFinalPrice(), 0.001);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("getFinalPrice completes within 1-second timeout")
    void testGetFinalPriceTimeout() {
        product.applyDiscount(20);
        assertEquals(800.0, product.getFinalPrice(), 0.001);
    }
}