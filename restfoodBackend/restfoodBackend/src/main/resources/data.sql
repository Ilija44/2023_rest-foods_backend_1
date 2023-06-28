INSERT INTO burger (name, beschreibung, zutaten, preis, vegetarian, img_url)
VALUES ('Cheeseburger', 'Ein Burger mit Käse', 'Rindfleisch, Käse, Salat, Tomaten', 5.99, true, 'https://www.eatclub.tv/wp-content/uploads/sites/4/2022/02/smashed-bacon-cheeseburger.jpg');

INSERT INTO burger (name, beschreibung, zutaten, preis, vegetarian, img_url)
VALUES ('Chicken Burger', 'Ein Burger mit Hähnchenfleisch', 'Hähnchenfleisch, Salat, Zwiebeln, Mayonnaise', 6.99, false, 'https://luma-delikatessen.ch/magazin-media/filer_public/1d/33/1d338577-d98a-4c01-9b1b-376eea5bc51c/luma-rezept-crispy-chicken-burger-006_low.jpg');

INSERT INTO burger (name, beschreibung, zutaten, preis, vegetarian, img_url)
VALUES ('Veggie Burger', 'Ein fleischloser Burger', 'Gemüse-Patty, Salat, Tomaten, Gewürze', 4.49, true, 'https://assets.biggreenegg.eu/app/uploads/2021/03/05092206/banner-vega-falafelburger-2021m03-1-1200x600-1.jpg');

INSERT INTO burger (name, beschreibung, zutaten, preis, vegetarian, img_url)
VALUES ('Mushroom Swiss Burger', 'Ein Burger mit Pilzen und Swiss-Käse', 'Rindfleisch, Pilze, Swiss-Käse, Salat, Zwiebeln', 6.79, false, 'https://stpierrebakery-com.s3.amazonaws.com/app/uploads/2021/11/Truffle-Mushroom-Burger.jpg');



INSERT INTO role (role_id, role_name)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO authority (authority_id, authority_name)
VALUES (1, 'CREATE'),
       (2, 'READ'),
       (3, 'UPDATE'),
       (4, 'DELETE');

INSERT INTO role_authority (id_role, id_authority)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2);

INSERT INTO users (user_id, username, password, user_role)
VALUES (1, 'Kellner1', '1234', 1),
       (2, 'Kellner2', '1234', 1),
       (3, 'Kunde1', '1234', 2),
       (4, 'Kunde2', '1234', 2);