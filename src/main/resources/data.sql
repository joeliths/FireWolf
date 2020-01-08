insert into user(id, full_name, password,user_name,uuid)
values(1,"Ash Ketchum", "123456", "kaos", "383abc70-1c34-4236-87d0-b9eb211dfbb5");
insert into user(id, full_name, password,user_name,uuid)
values(2,"MasterChief", "imcool69", "halo4life", "383abc70-1c34-4236-87d0-b9eb281dfbb5");
insert into customer(user_id, uuid)
values(1, "388abc70-1c34-4236-87d0-b9eb211dfbb5");
insert into vendor(user_id, uuid)
values(2,"388abc70-1c34-4236-87d0-b9eb211dfbb6");
insert into store(id, address, description, uuid, vendor_id)
values(1,"Varbersgatan 37", "en trevlig liten butik",
"388abc70-1c34-4236-87d0-b9eb211dfbb7", 2);
insert into position (id, lat, lng, store_id)
values(1,57.701366,11.987293,1);
insert into store(id, address, description, uuid, vendor_id)
values(2,"Varbersgatan 39", "en otrevlig stor butik",
"388abc91-1c34-4236-87d0-b9eb211dfbb3", 2);
insert into position (id, lat, lng, store_id)
values(2,57.701005,11.991638,2);
