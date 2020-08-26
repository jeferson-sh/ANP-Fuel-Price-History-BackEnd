-- Insert Admin User
-- PASSWORD = PASSWORD
insert into application_user
values(1,'ADMIN','ADMIN@EMAIL','$2y$12$sEOJVDRlyQnxjiX21R.SX./.szTA/pas/RZ/0/tmMZxZzf3Jjd9ye');

--Insert AUTHORITIES
insert into authority
values
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

--Insert relationship user authority
--insert into application_user_authorities
--values
--(1,1), 
--(1,2);

--Insert States
insert into STATE
values 
(1,'AC'),
(2,'AL'),
(3,'AP'),
(4,'AM'),
(5,'BA'),
(6,'CE'),
(7,'DF'),
(8,'ES'),
(9,'GO'),
(10,'MA'),
(11,'MT'),
(12,'MS'),
(13,'MG'),
(14,'PA'),
(15,'PB'),
(16,'PE'),
(17,'PI'),
(18,'PR'),
(19,'RJ'),
(20,'RN'),
(21,'RO'),
(22,'RR'),
(23,'RS'),
(24,'SE'),
(25,'SC'),
(26,'SP'),
(27,'TO');


--Insert Regions
insert into REGION
values
(1,'CO'),
(2,'NE'),
(3,'N'),
(4,'SE'),
(5,'S');

--Insert Products
insert into PRODUCT
values
(1,'GASOLINA'),
(2,'DIESEL'),
(3,'DIESEL S10'),
(4,'ETANOL');


