INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2016-01-01');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2016-01-01');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2016-01-01');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);

INSERT INTO player (id, name, latitude, longitude, user_id) VALUES (1, 'admin', 41.076804, 23.553638, 1);
INSERT INTO player (id, name, latitude, longitude, user_id) VALUES (2, 'user1', 41.075833, 23.553614, 2);
INSERT INTO player (id, name, latitude, longitude, user_id) VALUES (3, 'giorgis', 40.1, 44.7, 3);
INSERT INTO player (id, name, latitude, longitude, user_id) VALUES (4, 'yoko', 42.3, 45.2, 3);

INSERT INTO quest (id, name, latitude, longitude, description) VALUES (0, 'myQuest1', 40.3, 45.4, 'Do something1(Description)');
INSERT INTO quest (id, name, latitude, longitude, description) VALUES (1, 'myQuest2', 40.2002, 45.4002, 'Do something2(Description)');
INSERT INTO quest (id, name, latitude, longitude, description) VALUES (2, 'myQuest3', 40.9, 45.7, 'Do something3(Description)');

INSERT INTO quest (id, name, latitude, longitude, description) VALUES (3, 'myQuest3', 40.20003, 45.4, 'Do something2(Description)');
INSERT INTO quest (id, name, latitude, longitude, description) VALUES (4, 'myQuest4', 40.2, 45.4, 'Do something3(Description)');



INSERT INTO item (id, name, description, type, value, price) VALUES (0, 'Shield','personal armor, meant to intercept attacks', 'Defence', 80,20);
INSERT INTO item (id, name, description, type, value, price) VALUES (1, 'Axe', 'battle axe,is an axe specifically designed for attack', 'Attack', 90,50);
INSERT INTO item (id, name, description, type, value, price) VALUES (2, 'Arrow', 'An arrow is a shafted projectile that is shot with a bow', 'Attack', 70,30);
INSERT INTO item (id, name, description, type, value, price) VALUES (3, 'Elixir', 'Elixir of life,You gain Life Points (HP)', 'HP', 100,60);
INSERT INTO item (id, name, description, type, value, price) VALUES (4, 'Energy', 'energy equipment,provide speed', 'Defence', 100,60);