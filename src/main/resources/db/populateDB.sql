DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, dateTime, description, calories) VALUES
  (100000, TIMESTAMP '2015-05-30 09:30:25 CET', 'Завтрак', 800),
  (100000, TIMESTAMP '2015-05-30 13:30:25 CET', 'Обед', 1500),
  (100000, TIMESTAMP '2015-05-30 18:30:25 CET', 'Ужин', 1000),
  (100000, TIMESTAMP '2015-05-31 09:30:25 CET', 'Завтрак', 800),
  (100000, TIMESTAMP '2015-05-31 13:30:25 CET', 'Обед', 1500),
  (100000, TIMESTAMP '2015-05-31 18:30:25 CET', 'Ужин', 1000),
  (100001, TIMESTAMP '2015-05-09 09:30:25 CET', 'Завтрак', 1000),
  (100001, TIMESTAMP '2015-05-09 13:30:25 CET', 'Обед', 1700),
  (100001, TIMESTAMP '2015-05-09 18:30:25 CET', 'Ужин', 800),
  (100001, TIMESTAMP '2015-05-10 09:30:25 CET', 'Завтрак', 1000),
  (100001, TIMESTAMP '2015-05-10 13:30:25 CET', 'Обед', 1000),
  (100001, TIMESTAMP '2015-05-10 18:30:25 CET', 'Ужин', 1000);