package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.storage.MapStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        log.debug("create storage");
        storage = new MapStorage();
        storage.addListOfMeals(MealsUtil.getMock());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uuid = req.getParameter("uuid");
        String desc = req.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal;
        if (uuid.isEmpty()) {
            meal = new Meal(dateTime, desc, calories);
            storage.update(meal);
        } else {
            meal = new Meal(uuid, dateTime, desc, calories);
            storage.save(meal);
        }
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            List<MealWithExceed> mealsWithExceedList = MealsUtil.getWithExceeded(storage.getAll(), 2000);
            request.setAttribute("meals", mealsWithExceedList);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy  hh:mm a");
            request.setAttribute("formatter", formatter);
            log.debug("forward to meals.jsp");

            request.getRequestDispatcher("/listOfMeals.jsp").forward(request, response);
            return;
        }
        Meal m;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("meals");
                return;
            case "clear":
                storage.clear();
                response.sendRedirect("meals");
                return;
            case "new":
                request.getRequestDispatcher("new.jsp").forward(request, response);
                return;
            case "edit":
                m = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", m);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }
}
