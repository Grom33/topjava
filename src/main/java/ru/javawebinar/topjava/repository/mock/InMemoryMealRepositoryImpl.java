package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS01.forEach(meal -> save(1, meal));
        MealsUtil.MEALS02.forEach(meal -> save(2, meal));
    }

    @Override
    public Meal save(int userID, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userID);
            repository.put(meal.getId(), meal);
            return meal;
        } else {
            if (meal.getUserId() != userID) return null;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userID, int id) {
        Meal mealToDelete = this.get(userID, id);
        if (mealToDelete != null) {
            repository.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int userID, int id) {
        Meal meal = repository.get(id);
        if (meal != null) {
            return (meal.getUserId() == userID) ? meal : null;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userID) {
        List<Meal> meals = new ArrayList<>(repository.values());
        return meals.stream()
                .filter(meal -> meal.getUserId() == userID)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getFiltered(int userID, LocalDate begin, LocalDate end) {
        List<Meal> meals = new ArrayList<>(repository.values());
        return meals.stream()
                .filter(meal -> meal.getUserId() == userID)
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), begin, end))
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }
}

