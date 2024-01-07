INSERT INTO users (id, first_name, last_name, gender, email, scout_group, password, role)
VALUES (1, 'Mario', 'Rossi', 'MALE', 'mario.rossi@example.com', 'ScoutGroup1', 'password123', 'MANAGER'),
       (2, 'Anna', 'Verdi', 'FEMALE', 'anna.verdi@example.com', 'ScoutGroup2', 'securepass', 'USER');

INSERT INTO contacts (id, type, contact, note)
VALUES (1, 'EMAIL', 'info@example.com', 'email aziendale'),
       (2, 'PHONE', '+123456789', 'tel. sig.Giovanni');

INSERT INTO places (id, name, address, manager_name, price, tent, structure, note, last_update)
VALUES (1, 'San Pio delle Camere', 'Via Campagna 123', 'Giovanni', 50.00, true, false, 'Bellissima area verde',
        '2023-01-15');

/*
INSERT INTO images (id, path, creation, place_id)
VALUES (1, '/image/site/foto1.jpeg', '2023-01-16', 1),
       (2, '/image/site/foto2.jpeg', '2023-05-20', 1);
 */

INSERT INTO reviews (id, place_id, user_id, services_rating, manager_rating, overall_rating, comment, date)
VALUES (1, 1, 1, 4, 5, 2, 'Luogo fantastico, servizi eccellenti!', '2023-01-16');

INSERT INTO place_contact (id, place_id, contact_id)
VALUES (1, 1, 1),
       (2, 1, 2);
