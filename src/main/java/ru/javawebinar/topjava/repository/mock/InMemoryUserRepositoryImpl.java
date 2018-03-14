package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
   // private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        User deletedUser = repository.remove(id);
        return deletedUser!=null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return repository.put(user.getId(),user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> result = new ArrayList<>(repository.values());
        Comparator<User> USER_NAME_COMPARATOR = Comparator.comparing(User::getName);
        result.sort(USER_NAME_COMPARATOR);
        return result;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        Optional<User> searchUser = repository.values().stream()
                .filter(user->user.getEmail().equals(email))
                .findFirst();
       return searchUser.orElse(null);
    }
}
