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
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayExceedValue = mealList.stream()
                .collect(Collectors.groupingBy((UserMeal::getDate), Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter((um) -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map((userMeal ->
                        new UserMealWithExceed(
                                userMeal.getDateTime()
                                , userMeal.getDescription()
                                , userMeal.getCalories()
                                , (dayExceedValue.get(userMeal.getDate()) > caloriesPerDay))))
                .collect(Collectors.toList());
    }
}
