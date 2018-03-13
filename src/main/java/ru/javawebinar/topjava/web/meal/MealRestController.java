package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Collection<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(AuthorizedUser.id(), id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(AuthorizedUser.id(), meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(AuthorizedUser.id(), id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(AuthorizedUser.id(), meal);
    }

    public List<MealWithExceed> getAll(LocalTime beginTime, LocalTime endTime) {
        log.info("getAll {} {}", beginTime, endTime);

        beginTime = beginTime == null ? LocalTime.MIN : beginTime;
        endTime = endTime == null ? LocalTime.MAX : endTime;

        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay(), beginTime, endTime);
    }
}