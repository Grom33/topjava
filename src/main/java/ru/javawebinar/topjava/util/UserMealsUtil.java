package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayExceedValue = new HashMap<>();
        Map<LocalDate, Boolean> dayExceed = new HashMap<>();

        Stream<UserMeal> streamUserMeal = mealList.stream();

        List<UserMealWithExceed> streamUserMeal1WithExceed = streamUserMeal
                .peek((userMeal -> {
                    if (dayExceedValue.containsKey(toLocalDate(userMeal.getDateTime()))) {
                        dayExceedValue.put(toLocalDate(userMeal.getDateTime()), (userMeal.getCalories() + dayExceedValue.get(toLocalDate(userMeal.getDateTime()))));
                    } else {
                        dayExceedValue.put(toLocalDate(userMeal.getDateTime()), userMeal.getCalories());
                    }
                }))
                .filter((um) -> TimeUtil.isBetween(TimeUtil.toLocaTime(um.getDateTime()), startTime, endTime))
                .map((userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), dayExceed)))
                .collect(Collectors.toList());

        dayExceedValue.forEach((ldt, cal) -> dayExceed.put(ldt, (cal > caloriesPerDay)));

        return streamUserMeal1WithExceed;
    }

    public static LocalDate toLocalDate(LocalDateTime ldt) {
        return LocalDate.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth());
    }

}
