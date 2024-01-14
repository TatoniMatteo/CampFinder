INSERT INTO users (id, first_name, last_name, gender, email, scout_group, password, role)
VALUES (default, 'Mario', 'Rossi', 'MALE', 'mario.rossi@example.com', 'ScoutGroup1', 'password123', 'MANAGER'),
       (default, 'Anna', 'Verdi', 'FEMALE', 'anna.verdi@example.com', 'ScoutGroup2', 'securepass', 'USER'),
       (default, 'Giulia', 'Bianchi', 'FEMALE', 'giulia.bianchi@example.com', 'ScoutGroup3', 'pass1234', 'USER'),
       (default, 'Luca', 'Verdi', 'MALE', 'luca.verdi@example.com', 'ScoutGroup1', 'securepass456', 'USER');

INSERT INTO contacts (id, type, contact, note)
VALUES (default, 'EMAIL', 'info@example.com', 'email aziendale'),
       (default, 'PHONE', '+123456789', 'tel. sig.Giovanni'),
       (default, 'EMAIL', 'giulia.b@example.com', 'email personale'),
       (default, 'PHONE', '+987654321', 'tel. sig.ra Maria');

INSERT INTO places (id, name, address, manager_name, price, tent, structure, note, last_update)
VALUES (default, 'San Pio delle Camere', 'Via delle Rose, 42, 00123 Rivafiorita (FG) - Puglia', 'Giovanni', 50.00,
        true, false, 'Bellissima area verde', '2023-01-15'),
       (default, 'Monte Amaro Camping', 'Viale dei Gabbiani, 17, 45678 Collinoblu (MI) - Lombardia', 'Maria', 65.00,
        true, true, 'Vista panoramica', '2023-02-20'),
       (default, 'Lago Sereno', 'Piazza del Sole, 3, 98765 Monteverde (NA) - Campania', 'Luigi', 40.00, false, true,
        'Ideale per pescatori', '2023-03-10');

INSERT INTO reviews (id, place_id, user_id, services_rating, manager_rating, overall_rating, comment, date)
VALUES (default, 1, 1, 4, 5, 2, 'Luogo fantastico, servizi eccellenti!', '2023-01-16'),
       (default, 2, 3, 5, 4, 4, 'Posto meraviglioso, servizi top!', '2023-03-01'),
       (default, 3, 4, 3, 5, 4, 'Bellissimo lago, ottimo per il relax', '2023-04-15');

INSERT INTO place_contact (id, place_id, contact_id)
VALUES (default, 1, 1),
       (default, 1, 2),
       (default, 2, 3),
       (default, 3, 4);

INSERT INTO images (id, path, creation, place_id)
VALUES (1, '/image/places/foto1.jpeg', '2024-01-14', 1),
       (2, '/image/places/foto2.jpeg', '2024-01-14', 2);



