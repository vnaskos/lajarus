INSERT INTO player (id, name, latitude, longitude) VALUES (1, 'admin', 40.2, 45.4);
INSERT INTO player (id, name, latitude, longitude) VALUES (2, 'user1', 40.2003, 45.4);
INSERT INTO player (id, name, latitude, longitude) VALUES (3, 'giorgis', 40.1, 44.7);
INSERT INTO player (id, name, latitude, longitude) VALUES (4, 'yoko', 42.3, 45.2);

INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2016-01-01');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2016-01-01');
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2016-01-01');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);

INSERT INTO quest (id, name, latitude, longitude, description) VALUES (0, 'myQuest1', 40.3, 45.4, 'Do something1(Description)');
INSERT INTO quest (id, name, latitude, longitude, description) VALUES (1, 'myQuest2', 40.2, 45.5, 'Do something2(Description)');
INSERT INTO quest (id, name, latitude, longitude, description) VALUES (2, 'myQuest3', 40.9, 45.7, 'Do something3(Description)');

INSERT INTO quest (id, name, latitude, longitude, description) VALUES (3, 'myQuest3', 40.20003, 45.4, 'Do something2(Description)');
INSERT INTO quest (id, name, latitude, longitude, description) VALUES (4, 'myQuest4', 40.2, 45.4, 'Do something3(Description)');