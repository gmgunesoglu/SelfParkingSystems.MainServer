-- Person tablosu
INSERT INTO person (enable, authority, bloc_date, id, phone_number, last_name, user_name, email, first_name, password) VALUES
    (true, 'ADMIN', '1970-01-01', 1, '5380426061', 'Güneşoğlu', 'gokhan123', 'gokhangunesoglu@gmail.com', 'Gökhan Muzaffer', '$2a$10$FHCYfRHbRvQQpBZtcdXWSObFCi20g/rmIrnny8Pp8QMEfQ6wT8lxW'),
    (true, 'OWNER', '1970-01-01', 2, '1231231231', 'Güneşoğlu', 'serkan123', 'serkangunesoglu@gmail.com', 'Serkan', '$2a$10$FHCYfRHbRvQQpBZtcdXWSObFCi20g/rmIrnny8Pp8QMEfQ6wT8lxW'),
    (true, 'STAFF', '1970-01-01', 3, '1231231231', 'Güneşoğlu', 'aynur123', 'aynurgunesoglu@gmail.com', 'Aynur', '$2a$10$FHCYfRHbRvQQpBZtcdXWSObFCi20g/rmIrnny8Pp8QMEfQ6wT8lxW');

-- Location tablosu
INSERT INTO location (enable, id, city, town, district) VALUES
    (true, 1, 'İSTANBUL', 'KADIKÖY', 'HASANPAŞA'),
    (true, 2, 'ANKARA', 'ÇANKAYA', 'ANITTEPE'),
    (true, 3, 'ANKARA', 'ÇANKAYA', 'AKPINAR');

-- Park tablosu
INSERT INTO park (enable, reservation_duration, id, location_id, owner_id, name, base_url, secret_key, address) VALUES
    (true, 60, 1, 1, 2, 'Gunesoglu Hasanpasa', 'http://park1.com', 'secret1', 'Address1'),
    (true, 60, 2, 2, 2, 'Gunesoglu Anıttepe', 'http://park2.com', 'secret2', 'Address2'),
    (false, 60, 3, 3, 2, 'Gunesoglu Akpınar', 'http://park3.com', 'secret3', 'Address3');

-- payment_recipe tablosu
INSERT INTO payment_recipe (enable, hours_2, hours_4, hours_6, hours_10, hours_24, id, owner_id, name) VALUES
   (true, 20.0, 28.0, 52.0, 80.0, 120.0, 1, 2, 'Hasanpasa Açık'),
   (true, 22.0, 31.0, 56.0, 85.0, 130.0, 2, 2, 'Hasanpasa Kapalı'),
   (true, 20.0, 30.0, 50.0, 90.0, 140.0, 3, 2, 'Anıttepe'),
   (true, 30.0, 45.0, 75.0, 135.0, 210.0, 4, 2, 'Anıttepe Lüks'),
   (true, 18.0, 25.0, 50.0, 75.0, 110.0, 5, 2, 'Akpınar'),
   (true, 20.0, 27.0, 53.0, 80.0, 120.0, 6, 2, 'Akpınar 2');

-- slot tablosu
INSERT INTO slot (enable, id, name, park_id, payment_recipe_id, state) VALUES
   (true, 1, 'Slot1', 1, 1, 'FREE'),
   (true, 2, 'Slot2', 1, 1, 'FREE'),
   (true, 3, 'Slot3', 1, 2, 'FREE'),
   (true, 4, 'Slot4', 1, 2, 'FREE'),
   (true, 5, 'Slot5', 1, 2, 'FREE'),
   (true, 6, 'Slot6', 1, 1, 'FREE'),
   (true, 7, 'Slot7', 1, 1, 'FREE'),
   (true, 8, 'Slot8', 1, 2, 'FREE'),
   (true, 9, 'Slot9', 1, 1, 'FREE'),
   (true, 10, 'Slot10', 1, 1, 'FREE'),
   (true, 11, 'Slot1', 2, 4, 'FREE'),
   (true, 12, 'Slot2', 2, 4, 'FREE'),
   (true, 13, 'Slot3', 2, 4, 'FREE'),
   (true, 14, 'Slot4', 2, 3, 'FREE'),
   (true, 15, 'Slot5', 2, 3, 'FREE'),
   (true, 16, 'Slot1', 3, 5, 'FREE'),
   (true, 17, 'Slot2', 3, 5, 'FREE'),
   (true, 18, 'Slot3', 3, 6, 'FREE'),
   (true, 19, 'Slot4', 3, 6, 'FREE'),
   (true, 20, 'Slot5', 3, 6, 'FREE');


select * from park;
