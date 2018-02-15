package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private Map<LocalDate, Boolean> dayExceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, Map<LocalDate, Boolean> de) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.dayExceed = de;
    }

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories) {
        this(dateTime, description, calories, null);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        if (!(dayExceed == null)) {
            return dayExceed.get(UserMealsUtil.toLocalDate(dateTime));
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMealWithExceed that = (UserMealWithExceed) o;
        return calories == that.calories &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateTime, description, calories);
    }
}
