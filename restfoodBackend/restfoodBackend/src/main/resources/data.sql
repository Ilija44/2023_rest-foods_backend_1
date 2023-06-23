INSERT INTO burger (id, name, price)
VALUES /*da söttemer esse ine tue aber ich bin nd kreativ*/;

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