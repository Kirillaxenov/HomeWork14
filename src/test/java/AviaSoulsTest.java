import org.example.AviaSouls;
import org.example.Ticket;
import org.example.TicketTimeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    @Test
    public void testEquals() {
        Ticket t1 = new Ticket("Москва", "Берлин", 100, 12, 15);
        Ticket t2 = new Ticket("Москва", "Берлин", 100, 12, 15);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testCompareToLess() {
        Ticket t1 = new Ticket("Москва", "Берлин", 100, 12, 15);
        Ticket t2 = new Ticket("Москва", "Париж", 200, 12, 15);
        Assertions.assertTrue(t1.compareTo(t2) < 0);
    }

    @Test
    void testCompareToGreater() {
        Ticket t1 = new Ticket("Москва", "Париж", 300, 12, 15);
        Ticket t2 = new Ticket("Москва", "Берлин", 200, 12, 15);
        Assertions.assertTrue(t1.compareTo(t2) > 0);
    }

    @Test
    void testCompareToEqual() {
        Ticket t1 = new Ticket("Москва", "Берлин", 300, 12, 15);
        Ticket t2 = new Ticket("Москва", "Лондон", 300, 12, 15);
        Assertions.assertEquals(t1.compareTo(t2), 0);
    }

    @Test
    void testAdd() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Берлин", 100, 12, 15);
        Ticket t2 = new Ticket("Москва", "Брюсель", 200, 12, 15);
        Ticket t3 = new Ticket("Москва", "Париж", 300, 12, 15);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        Ticket[] expected = {t1, t2, t3};
        Assertions.assertArrayEquals(manager.findAll(), expected);
    }

    @Test
    void testSearch() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Париж", 100, 12, 15);
        Ticket t2 = new Ticket("Москва", "Брюсель", 200, 12, 15);
        Ticket t3 = new Ticket("Москва", "Париж", 300, 12, 15);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        Ticket[] expected = {t1, t3};
        Assertions.assertArrayEquals(manager.search("Москва", "Париж"), expected);
    }

    @Test
    void testSearchAndSortByTicketTimeComparator() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Париж", 800, 11, 19);
        Ticket t2 = new Ticket("Москва", "Брюсель", 400, 9, 14);
        Ticket t3 = new Ticket("Москва", "Нью-Йорк", 300, 12, 15);
        Ticket t4 = new Ticket("Москва", "Лондон", 100, 8, 19);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        Ticket[] result = manager.searchAndSortBy("Москва", "Нью-Йорк", new TicketTimeComparator());
        Assertions.assertEquals("Москва", result[0].getFrom());
        Assertions.assertEquals("Нью-Йорк", result[0].getTo());
        Assertions.assertEquals(12, result[0].getTimeFrom());
        Assertions.assertEquals(15, result[0].getTimeTo());
    }

    @Test
    public void testSearchAndSortByTicketTimeComparatorNoMatches() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Париж", 800, 11, 19);
        Ticket t2 = new Ticket("Москва", "Брюсель", 400, 9, 14);
        Ticket t3 = new Ticket("Москва", "Нью-Йорк", 300, 12, 15);
        Ticket t4 = new Ticket("Москва", "Лондон", 100, 8, 19);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchAndSortBy("Москва", "Минск", new TicketTimeComparator());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByTicketTimeComparatorMultipleMatches() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Париж", 800, 11, 19);
        Ticket t2 = new Ticket("Москва", "Брюсель", 400, 9, 14);
        Ticket t3 = new Ticket("Москва", "Нью-Йорк", 300, 12, 15);
        Ticket t4 = new Ticket("Москва", "Париж", 100, 8, 20);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);
        Ticket[] result = manager.searchAndSortBy("Москва", "Париж", new TicketTimeComparator());
        Assertions.assertEquals("Москва", result[0].getFrom());
        Assertions.assertEquals("Париж", result[0].getTo());
        Assertions.assertEquals(11, result[0].getTimeFrom());
        Assertions.assertEquals(19, result[0].getTimeTo());

        Assertions.assertEquals("Москва", result[1].getFrom());
        Assertions.assertEquals("Париж", result[1].getTo());
        Assertions.assertEquals(8, result[1].getTimeFrom());
        Assertions.assertEquals(20, result[1].getTimeTo());

    }
}
