INSERT INTO person (enable, authority, bloc_date, id, phone_number, last_name, user_name, email, first_name, password) VALUES
    (true, 'ADMIN', '1970-01-01', 1, '5380426061', 'Güneşoğlu', 'gokhan123', 'gokhangunesoglu@gmail.com', 'Gökhan Muzaffer', '$2a$10$FHCYfRHbRvQQpBZtcdXWSObFCi20g/rmIrnny8Pp8QMEfQ6wT8lxW'),
    (true, 'OWNER', '1970-01-01', 1, '1231231231', 'Güneşoğlu', 'serkan123', 'serkangunesoglu@gmail.com', 'Serkan', '$2a$10$FHCYfRHbRvQQpBZtcdXWSObFCi20g/rmIrnny8Pp8QMEfQ6wT8lxW'),
    (true, 'STAFF', '1970-01-01', 1, '1231231231', 'Güneşoğlu', 'aynur123', 'aynurgunesoglu@gmail.com', 'Aynur', '$2a$10$FHCYfRHbRvQQpBZtcdXWSObFCi20g/rmIrnny8Pp8QMEfQ6wT8lxW');

INSERT INTO location (enable, id, city, town, district) VALUES
    (true, 1, 'İstanbul', 'Kadıköy', 'Hasanpaşa');


-- şifre
select * from location;


SELECT l.city FROM Location l;