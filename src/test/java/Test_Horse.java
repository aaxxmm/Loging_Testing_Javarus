import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Test_Horse{

    // Тесты конструктора
    @Test
    void constructorWithNullNameShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    void constructorWithNullNameShouldThrowExceptionWithCorrectMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "  \n  "})
    void constructorWithBlankNameShouldThrowException(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 1.0, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void constructorWithBlankNameShouldThrowExceptionWithCorrectMessage(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorWithNegativeSpeedShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", -1.0, 1.0));
    }

    @Test
    void constructorWithNegativeSpeedShouldThrowExceptionWithCorrectMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorWithNegativeDistanceShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", 1.0, -1.0));
    }

    @Test
    void constructorWithNegativeDistanceShouldThrowExceptionWithCorrectMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    // Тесты методов
    @Test
    void getNameShouldReturnCorrectName() {
        Horse horse = new Horse("TestName", 2.0, 3.0);
        assertEquals("TestName", horse.getName());
    }

    @Test
    void getSpeedShouldReturnCorrectSpeed() {
        Horse horse = new Horse("Test", 2.5, 3.0);
        assertEquals(2.5, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnCorrectDistance() {
        Horse horse = new Horse("Test", 2.0, 3.5);
        assertEquals(3.5, horse.getDistance());
    }

    @Test
    void getDistanceShouldReturnZeroWhenConstructorWithTwoParams() {
        Horse horse = new Horse("Test", 2.0);
        assertEquals(0.0, horse.getDistance());
    }

    // Тест метода move
    @Test
    void moveShouldCallGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 2.0, 3.0);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void moveShouldCalculateDistanceCorrectly(double randomValue) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 2.0, 3.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomValue);

            double expectedDistance = 3.0 + 2.0 * randomValue;
            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.001);
        }
    }
}
