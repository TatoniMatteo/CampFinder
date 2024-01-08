INSERT INTO users (id, first_name, last_name, gender, email, scout_group, password, role)
VALUES (1, 'Mario', 'Rossi', 'MALE', 'mario.rossi@example.com', 'ScoutGroup1', 'password123', 'MANAGER'),
       (2, 'Anna', 'Verdi', 'FEMALE', 'anna.verdi@example.com', 'ScoutGroup2', 'securepass', 'USER'),
       (3, 'Giulia', 'Bianchi', 'FEMALE', 'giulia.bianchi@example.com', 'ScoutGroup3', 'pass1234', 'USER'),
       (4, 'Luca', 'Verdi', 'MALE', 'luca.verdi@example.com', 'ScoutGroup1', 'securepass456', 'USER');

INSERT INTO contacts (id, type, contact, note)
VALUES (1, 'EMAIL', 'info@example.com', 'email aziendale'),
       (2, 'PHONE', '+123456789', 'tel. sig.Giovanni'),
       (3, 'EMAIL', 'giulia.b@example.com', 'email personale'),
       (4, 'PHONE', '+987654321', 'tel. sig.ra Maria');

INSERT INTO places (id, name, address, manager_name, price, tent, structure, note, last_update)
VALUES (1, 'San Pio delle Camere', 'Via Campagna 123', 'Giovanni', 50.00, true, false, 'Bellissima area verde',
        '2023-01-15'),
       (2, 'Monte Amaro Camping', 'Via Montagna 456', 'Maria', 65.00, true, true, 'Vista panoramica', '2023-02-20'),
       (3, 'Lago Sereno', 'Via Acqua 789', 'Luigi', 40.00, false, true, 'Ideale per pescatori', '2023-03-10');

INSERT INTO reviews (id, place_id, user_id, services_rating, manager_rating, overall_rating, comment, date)
VALUES (1, 1, 1, 4, 5, 2, 'Luogo fantastico, servizi eccellenti!', '2023-01-16'),
       (2, 2, 3, 5, 4, 4, 'Posto meraviglioso, servizi top!', '2023-03-01'),
       (3, 3, 4, 3, 5, 4, 'Bellissimo lago, ottimo per il relax', '2023-04-15');

INSERT INTO place_contact (id, place_id, contact_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 3),
       (4, 3, 4);


