INSERT INTO user_role(role) VALUES("ROLE_ADMIN"), ("ROLE_CUSTOMER"), ("ROLE_VENDOR"), ("ROLE_USER");

INSERT INTO user(full_name, password, user_name, uuid) VALUES ("Berta", "$2a$10$QBBhONgb4Wy.yxkBbt5wS.ab29XOJGhPXPVnXnCAiWzr/ci3HvmI.", "xXxBerta420xXx", (SELECT uuid())),
("Dennis", "$2a$10$Lcegh9tty3tb/tfFm9aCXOEVJqFaCNx3R5n3mXYqgnp130QZi2ECO", "dennis", (SELECT uuid()));

INSERT INTO vendor(uuid, user_id) VALUES ((SELECT uuid()), 1);

INSERT INTO customer(uuid, user_id) VALUES ((SELECT uuid()), 1), ((SELECT uuid()), 2);

INSERT INTO user_role_roles(user_id, role_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (2, 3), (2, 4);

INSERT INTO product(description, name, uuid) VALUES ("Tasty!", "Chips", (SELECT uuid())), ("Not so good", "Beef", (SELECT uuid()));

INSERT INTO store(address, description, uuid, vendor_id) VALUES ("Mellangärdet 23", "Cool store", (SELECT uuid()), 1), ("Storgatan 43A", "Cool store", (SELECT uuid()), 1);

INSERT INTO store(address, description, uuid, vendor_id) VALUES ("Mellangärdet 23", "Cool store", (SELECT uuid()), 1);

INSERT INTO fire_wolf.position(lat, lng, store_id) VALUES(23.4, 4, 1);

INSERT INTO fire_wolf.position(lat, lng, store_id) VALUES(23.6, 70, 2);

INSERT INTO inventory_product(price, stock, uuid, product_id, store_id) VALUES (23, 2, (SELECT uuid()), 1, 1 ), (45, 6, (SELECT uuid()), 2, 1);

CREATE OR REPLACE VIEW inventory_product_view AS
select
 p.uuid AS product_uuid,
 p.name AS product_name,
 p.description AS product_description,
 ip.price AS inventory_product_price,
 ip.uuid AS inventory_product_uuid,
 ip.stock AS inventory_product_stock
 FROM inventory_product ip
 JOIN product p ON ip.product_id = p.id;

 CREATE OR REPLACE VIEW pending_order_product_view AS
SELECT
 p.name,
 p.description,
 pop.quantity,
 pop.uuid,
 i.price * pop.quantity AS price,
 po.uuid AS pending_order_uuid
 FROM pending_order po
 JOIN pending_order_product pop ON pop.pending_order_id = po.id
 JOIN inventory_product i ON i.id = pop.inventory_product_id
 JOIN product p ON i.product_id = p.id;
