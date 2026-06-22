import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Hippodrome_Test {

    @Test
    void constructorWithNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void constructorWithNullShouldThrowExceptionWithCorrectMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorWithEmptyListShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    void constructorWithEmptyListShouldThrowExceptionWithCorrectMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesShouldReturnSameList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i + 1.0));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();

        assertEquals(horses, returnedHorses);
        assertEquals(horses.size(), returnedHorses.size());
        for (int i = 0; i < horses.size(); i++) {
            assertSame(horses.get(i), returnedHorses.get(i));
        }
    }

    @Test
    void moveShouldCallMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerShouldReturnHorseWithMaxDistance() {
        List<Horse> horses = Arrays.asList(
                new Horse("Horse1", 1.0, 5.0),
                new Horse("Horse2", 1.0, 10.0),
                new Horse("Horse3", 1.0, 7.0)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals("Horse2", winner.getName());
        assertEquals(10.0, winner.getDistance());
    }
}
