INSERT INTO task (title, description, status, priority, due_date) VALUES
 ('Title 1', 'Desc 1', 'TODO', 'CRITICAL', '2018-01-15T17:09:42.411' ),
 ('Title 2', 'Desc 2', 'DONE', 'HIGH', '2017-01-13T17:09:42.411' ),
 ('Title 3', 'Desc 3', 'IN_PROGRESS', 'HIGH', '2017-01-13T17:09:42.411' ),
 ('Title 4', 'Desc 4', 'DONE', 'LOW', '2017-01-13T17:09:42.411' ),
 ('Title 5', 'Desc 5', 'TODO', 'LOW', '2017-01-13T17:09:42.411' );

 INSERT INTO _user(email, first_Name, second_Name, role, password) VALUES
 ('mostafa@gmail.com', 'Mostafa', 'Elbeih', 'ROLE_ADMIN', '$2a$10$NiPadYqKBkFcFdgIVncREuy68LD8yo39i..wBdWpEOTRAUlwLTjTe'),
 ('ahmed@gmail.com', 'Ahmed', 'Hossam', 'ROLE_USER', 'ahmed'),
 ('sam@gmail.com', 'Sam', 'Smith', 'ROLE_USER', 'sam'),
 ('adam@gmail.com', 'Adam', 'Burt', 'ROLE_USER', 'adam'),
 ('maged@gmail.com', 'Maged', 'Tamer', 'ROLE_USER', 'maged');