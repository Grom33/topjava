package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(int userID, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userID);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id) {
        Meal mealToDelete = this.get(id);
        if (mealToDelete != null) {
            repository.remove(id);
        }
    }

    @Override
    public Meal get(int id) {
        Meal mealToGet = repository.get(id);
        Meal result;
        if (mealToGet!=null){
            result = (mealToGet.getUserId() == AuthorizedUser.id()) ? mealToGet : null;
        }else{
            result = null;
        }
        return result;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}

