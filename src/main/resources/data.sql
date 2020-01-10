INSERT INTO user_role(role) VALUES("ROLE_ADMIN"), ("ROLE_CUSTOMER"), ("ROLE_VENDOR"), ("ROLE_USER");

INSERT INTO user(full_name, password, user_name, uuid) VALUES ("Berta", "$2a$10$QBBhONgb4Wy.yxkBbt5wS.ab29XOJGhPXPVnXnCAiWzr/ci3HvmI.", "xXxBerta420xXx", "b784cfaf-9998-4f2e-b99c-16153793187c"),
("Dennis", "$2a$10$Lcegh9tty3tb/tfFm9aCXOEVJqFaCNx3R5n3mXYqgnp130QZi2ECO", "dennis", "f9c0eeac-d769-4403-9dc2-3947a70a824c");

INSERT INTO vendor(uuid, user_id) VALUES ("118cba8d-ac42-457d-ab9c-d9c26e95cac1", 1);

INSERT INTO customer(uuid, user_id) VALUES ("27e8f226-151b-4706-a2b5-fff6642d4361", 1), ("78f41966-dada-47dd-98ae-431a6931c041", 2);

INSERT INTO user_role_roles(user_id, role_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (2, 2), (2, 4);

INSERT INTO product(description, name, uuid) VALUES ("Tasty!", "Chips", "9f3edcd7-7b0d-41f5-90de-335f3b15ffb9"), ("Not so good", "Beef", "a6f6166d-5d94-4441-9114-75d30da21f73");

INSERT INTO store(address, description, uuid, vendor_id) VALUES ("Mellangärdet 23", "Cool store", "09558eca-87e1-436e-8ae8-96fe5e19a7eb", 1), ("Storgatan 43A", "Cool store", "22cc18b2-2d18-442d-8269-dc5f1e03b734", 1);

INSERT INTO firewolf.position(lat, lng, store_id) VALUES(23.4, 4, 1);

INSERT INTO firewolf.position(lat, lng, store_id) VALUES(23.6, 70, 2);

INSERT INTO inventory_product(price, stock, uuid, product_id, store_id) VALUES (23, 2, "ec17c771-22e5-4b1a-a906-73401f4f271b", 1, 1 ), (45, 6, "c9801a81-b0b3-42e8-a752-a9089d2aec6a", 2, 1);

INSERT INTO pending_order(expiration_date_time, placement_date_time, uuid, customer_id, store_id) VALUES ((SELECT now()), (SELECT now()), "0e135042-2426-44bb-86ab-3f1ca50b2193", 2, 1);

INSERT INTO pending_order_product(quantity, uuid, inventory_product_id, pending_order_id) VALUES (1, "533818dd-4925-45d4-8d2d-387edc01336c", 1, 1);

insert into pending_order(expiration_date_time, placement_date_time, uuid, customer_id, store_id)
values('2011-12-18 13:17:17','2011-12-18 13:47:17',"388abc70-1c34-4236-87d0-b9eb231dfbb2",2,1);

insert into pending_order_product(quantity, uuid, inventory_product_id, pending_order_id)
values (1,
"338abc70-1c34-4236-87d0-b9eb211dfbb7", 1,1);

CREATE OR REPLACE VIEW inventory_product_view AS
select
 s.uuid AS store_uuid,
 p.uuid AS product_uuid,
 p.name AS product_name,
 p.description AS product_description,
 s.address AS store_address,
 ip.price AS inventory_product_price,
 ip.uuid AS inventory_product_uuid,
 ip.stock AS inventory_product_stock
 FROM inventory_product ip
 JOIN product p ON ip.product_id = p.id
 JOIN store s ON ip.store_id = s.id;

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
