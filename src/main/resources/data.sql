-- ADD SAMPLE DATE FOR EMPLOYEE TABLE
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (1, 'John Doe', 50000, 'Engineering');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (2, 'Jane Doe', 60000, 'Marketing');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (3, 'Robert Smith', 70000, 'Human Resources');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (4, 'Emma Johnson', 55000, 'Public Relations');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (5, 'Oliver Brown', 65000, 'Engineering');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (6, 'Sophia Davis', 75000, 'Sales');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (7, 'Michael Miller', 58000, 'Marketing');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (8, 'Emily Wilson', 68000, 'Administration');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (9, 'Noah Thomas', 78000, 'Engineering');
INSERT INTO EMPLOYEE (ID, EMPLOYEE_NAME, EMPLOYEE_SALARY, DEPARTMENT) VALUES (10, 'Ava Martinez', 55000, 'Human Resources');

-- ADD SAMPLE DATE FOR USERS TABLE
INSERT INTO `users`(username, password, enabled)
VALUES('applegate', '{bcrypt}$2a$12$4pvd2UbhRtnBxlW3X4yt7uWJIeFbC218NpYhLEhuAtYbpH.ZkMy9a', true),
      ('smith', '{bcrypt}$2a$12$4pvd2UbhRtnBxlW3X4yt7uWJIeFbC218NpYhLEhuAtYbpH.ZkMy9a', true),
      ('su', '{bcrypt}$2a$12$4pvd2UbhRtnBxlW3X4yt7uWJIeFbC218NpYhLEhuAtYbpH.ZkMy9a', true);

-- ADD SAMPLE DATE FOR AUTHORITIES TABLE
INSERT INTO `authorities`(username, authority)
VALUES('applegate', 'ROLE_EMPLOYEE'),
      ('smith', 'ROLE_EMPLOYEE'),
      ('smith', 'ROLE_MANAGER'),
      ('su', 'ROLE_EMPLOYEE'),
      ('su', 'ROLE_MANAGER'),
      ('su', 'ROLE_ADMIN');