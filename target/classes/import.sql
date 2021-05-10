insert into location (`id`,`name`,`latitude`,`longitude`) values (1,'Ahmedabad',22.698637,73.115573);
insert into location (`id`,`name`,`latitude`,`longitude`) values (2,'Nadiad',22.712172,73.111302);
insert into location (`id`,`name`,`latitude`,`longitude`) values (3,'Anand',22.708617,73.115640);
insert into location (`id`,`name`,`latitude`,`longitude`) values (4,'Surat',22.707014,73.107799);
insert into location (`id`,`name`,`latitude`,`longitude`) values (5,'Bhuj',22.701072,73.111302);
insert into location (`id`,`name`,`latitude`,`longitude`) values (6,'Vadodara', 22.698538, 73.224303);
insert into location (`id`,`name`,`latitude`,`longitude`) values (7,'Rajkot',22.703652, 73.115334);
insert into location (`id`,`name`,`latitude`,`longitude`) values (8,'Umreth',22.695707, 73.119411);
insert into location (`id`,`name`,`latitude`,`longitude`) values (9,'Bharuch',22.726060, 73.119992);
insert into location (`id`,`name`,`latitude`,`longitude`) values (10,'Daman',22.714523, 73.116061);

insert into user (`id`,`name`,`email`,`password`,`balance`,`location_id`) values (1,'User One','user1@email.com','user1',50000,1);

insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (1,'Honda','Civic',80,true,1);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (2,'Honda','Accord',100,true,2);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (3,'VW','Arteon',200,true,3);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (4,'VW','Passat',220,true,4);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (5,'Mercedez-Benz','CLA',220,true,5);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (6,'Mercedez-Benz','GLA',250,true,6);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (7,'Audi','A1',230,true,7);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (8,'Audi','A1',260,true,8);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (9,'BMW','X1',220,true,9);
insert into car (`id`,`name`,`model`,`rent_per_min`,`available`,`location_id`) values (10,'BMW','X2',190,true,10);