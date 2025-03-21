package com.hrs.demo.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionUtilTest {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void testParseStringToDate() {
        // Arrange
        Function<String, Date> parser = FunctionUtils.parseStringToDate();
        String dateString = "2025-03-21";

        // Act
        Date result = parser.apply(dateString);

        // Assert
        assertNotNull(result);
        assertEquals(dateString, df.format(result));
    }

    @Test
    void testParseStringToDate_InvalidDate_ThrowsException() {
        // Arrange
        Function<String, Date> parser = FunctionUtils.parseStringToDate();
        String invalidDate = "21-03-2025"; // wrong format

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            parser.apply(invalidDate);
        });

        // The actual badRequest() might log or throw separately, so we mainly check RuntimeException here
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof java.text.ParseException);
    }

    @Test
    void testParseDateToString() {
        // Arrange
        Function<Date, String> formatter = FunctionUtils.parseDateToString();
        Date now = new Date();

        // Act
        String formattedDate = formatter.apply(now);

        // Assert
        assertNotNull(formattedDate);
        assertEquals(df.format(now), formattedDate);
    }
}
