package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage implements Storage {
    private Map<String, Meal> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void update(Meal meal) {
        map.put(meal.getUuid(), meal);
    }

    @Override
    public void save(Meal meal) {
        map.put(meal.getUuid(), meal);
    }

    @Override
    public Meal get(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        map.remove(uuid);
    }

    @Override
    public void addListOfMeals(List<Meal> meals) {
        meals.forEach(m -> map.put(m.getUuid(), m));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(map.values());
    }
}
