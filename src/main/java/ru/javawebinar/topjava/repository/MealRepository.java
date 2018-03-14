package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.Collection;

public interface MealRepository {
    Meal save(int userID, Meal meal);

    boolean delete(int userID, int id);

    Meal get(int userID, int id);

    Collection<Meal> getAll(int userID);

    Collection<Meal> getFiltered(int userID, LocalDate begin, LocalDate end);
}
