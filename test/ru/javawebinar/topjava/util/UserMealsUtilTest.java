package ru.javawebinar.topjava.util;

import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserMealsUtilTest {
    private static final List<UserMeal> mealList;
    private static final List<UserMealWithExceed> checkList;

    static {
        mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        checkList = Arrays.asList(
                new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, false),
                new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, true)
        );
    }

    @Test
    public void getFilteredWithExceeded() {
        List<UserMealWithExceed> userMealWithExceedList = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        assertEquals(checkList, userMealWithExceedList);
        assertEquals(false, userMealWithExceedList.get(0).isExceed());
        assertEquals(true, userMealWithExceedList.get(1).isExceed());
    }

    @Test
    public void getFilteredWithExceededCycle() {
        List<UserMealWithExceed> userMealWithExceedList = UserMealsUtil.getFilteredWithExceededCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        assertEquals(checkList, userMealWithExceedList);
        assertEquals(false, userMealWithExceedList.get(0).isExceed());
        assertEquals(true, userMealWithExceedList.get(1).isExceed());
    }
}