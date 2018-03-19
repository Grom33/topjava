package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = 100002;

    public static final Meal MEAL01 = new Meal(100002, LocalDateTime.of(2015, 5, 30, 9, 30, 25), "Завтрак", 800);
    public static final Meal MEAL02 = new Meal(100003, LocalDateTime.of(2015, 5, 30, 13, 30, 25), "Обед", 1500);
    public static final Meal MEAL03 = new Meal(100004, LocalDateTime.of(2015, 5, 30, 18, 30, 25), "Ужин", 1000);
    public static final Meal MEAL04 = new Meal(100005, LocalDateTime.of(2015, 5, 31, 9, 30, 25), "Завтрак", 800);
    public static final Meal MEAL05 = new Meal(100006, LocalDateTime.of(2015, 5, 31, 13, 30, 25), "Обед", 1500);
    public static final Meal MEAL06 = new Meal(100007, LocalDateTime.of(2015, 5, 31, 18, 30, 25), "Ужин", 1000);
    public static final Meal MEAL07 = new Meal(100008, LocalDateTime.of(2015, 5, 9, 9, 30, 25), "Завтрак", 1000);
    public static final Meal MEAL08 = new Meal(100009, LocalDateTime.of(2015, 5, 9, 13, 30, 25), "Обед", 1700);
    public static final Meal MEAL09 = new Meal(100010, LocalDateTime.of(2015, 5, 9, 18, 30, 25), "Ужин", 800);
    public static final Meal MEAL10 = new Meal(100011, LocalDateTime.of(2015, 5, 10, 9, 30, 25), "Завтрак", 1000);
    public static final Meal MEAL11 = new Meal(100012, LocalDateTime.of(2015, 5, 10, 13, 30, 25), "Обед", 1000);
    public static final Meal MEAL12 = new Meal(100013, LocalDateTime.of(2015, 5, 10, 18, 30, 25), "Ужин", 1000);

    public static final Meal MEAL_UPDATED = new Meal(100003, LocalDateTime.of(2015, 5, 30, 9, 30, 25), "UPDATE", 1000);

    public static final List<Meal> MEALS_OF_USER = Arrays.asList(MEAL06, MEAL05, MEAL04, MEAL03, MEAL02, MEAL01);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertNotContain(List<Meal> actual, Meal expected) {
        assertThat(actual).doesNotContain(expected);
    }

    public static void assertMatch(List<Meal> actual, List<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
