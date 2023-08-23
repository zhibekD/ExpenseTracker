package model.src.tests.model;

import model.src.main.model.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTest {

    private Date testDate;
    private Date testBoundaryDateOne;
    private Date testBoundaryDateTwo;

    @BeforeEach
    void runBefore() {
        testDate = new Date(6, 15, 2023);
        testBoundaryDateOne = new Date(1,1,1000);
        testBoundaryDateTwo= new Date(12,31,1000);

    }

    @Test
    void testConstructor() {
        assertEquals(6, testDate.getMonth());
        assertEquals(15, testDate.getDay());
        assertEquals(2023, testDate.getYear());

        assertEquals(1, testBoundaryDateOne.getMonth());
        assertEquals(1, testBoundaryDateOne.getDay());
        assertEquals(1000, testBoundaryDateOne.getYear());

        assertEquals(12, testBoundaryDateTwo.getMonth());
        assertEquals(31, testBoundaryDateTwo.getDay());
        assertEquals(1000, testBoundaryDateTwo.getYear());
    }

    @Test
    void testGetDate() {
        assertEquals("06-15-2023", testDate.getDate());

        testDate.setMonth(12);
        testDate.setDay(30);
        testDate.setYear(2021);
        assertEquals("12-30-2021", testDate.getDate());

        assertEquals("01-01-1000", testBoundaryDateOne.getDate());
        assertEquals("12-31-1000", testBoundaryDateTwo.getDate());
    }

    @Test
    void testBetterFormat() {
        assertEquals("06", testDate.betterFormat(testDate.getMonth()));
        assertEquals("15", testDate.betterFormat(testDate.getDay()));

        testDate.setMonth(12);
        testDate.setDay(1);
        assertEquals("12", testDate.betterFormat(testDate.getMonth()));
        assertEquals("01", testDate.betterFormat(testDate.getDay()));
    }

    @Test
    void testSetMonth() {
        testDate.setMonth(2);
        assertEquals(2, testDate.getMonth());
    }

    @Test
    void testSetDay() {
        testDate.setDay(3);
        assertEquals(3, testDate.getDay());
    }

    @Test
    void setSetYear() {
        testDate.setYear(2023);
        assertEquals(2023, testDate.getYear());
    }

    @Test
    void testGetMonth() {
        assertEquals(6, testDate.getMonth());
    }

    @Test
    void testGetDay() {
        assertEquals(15, testDate.getDay());
    }

    @Test
    void testGetYear() {
        assertEquals(2023, testDate.getYear());
    }
}
