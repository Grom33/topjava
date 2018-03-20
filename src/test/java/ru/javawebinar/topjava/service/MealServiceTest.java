package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MEAL01);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        List<Meal> meals = service.getAll(USER_ID);
        assertNotContain(meals, MEAL01);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> meals = service.getBetweenDates(
                LocalDate.of(2015, 5, 30),
                LocalDate.of(2015, 5, 30),
                USER_ID);
        assertMatch(meals, MEAL03, MEAL02, MEAL01);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> meals = service.getBetweenDateTimes(
                LocalDateTime.of(2015, 5, 30, 9, 0, 0),
                LocalDateTime.of(2015, 5, 30, 16, 30, 25),
                USER_ID);
        assertMatch(meals, MEAL02, MEAL01);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, MEALS_OF_USER);
    }

    @Test
    public void update() {
        Meal firstMeal = new Meal(MEAL02_ID, LocalDateTime.of(2015, 5, 30, 9, 30, 25), "Обед", 1500);

        firstMeal.setDescription("UPDATE");
        firstMeal.setCalories(1000);
        service.update(firstMeal, USER_ID);

        Meal firstMealFromDB = service.get(MEAL02_ID, USER_ID);
        assertMatch(firstMealFromDB, MEAL_UPDATED);
    }

    @Test
    public void create() {
        Meal meal = new Meal(LocalDateTime.of(2015, 5, 30, 9, 30, 25), "Обед222", 1500);

        Meal meal1FromDB = service.create(meal, USER_ID);
        assertMatch(meal1FromDB, meal);
    }

    @Test(expected = NotFoundException.class)
    public void updateForeignMeal() {
        service.update(MEAL07, USER_ID);

        Meal firstMealFromDB = service.get(MEAL02_ID, USER_ID);
        assertMatch(firstMealFromDB, MEAL_UPDATED);
    }

    @Test(expected = NotFoundException.class)
    public void getForeignMeal() {
        Meal meal = service.get(MEAL08.getId(), USER_ID);
        assertMatch(meal, MEAL01);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeignMeal() {
        service.delete(MEAL08.getId(), USER_ID);
    }
}