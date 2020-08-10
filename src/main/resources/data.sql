-- Insert Admin User
-- PASSWORD = PASSWORD
insert into USERS
values(1,'ADMIN', '$2y$12$sEOJVDRlyQnxjiX21R.SX./.szTA/pas/RZ/0/tmMZxZzf3Jjd9ye','ADMIN@EMAIL');

--Insert Data AUTHORITIES
insert into AUTHORITIES
values(1,'ROLE_ADMIN');

insert into AUTHORITIES
values(2,'ROLE_USER');

--Insert relationship
insert into USERS_AUTHORITIES 
values(1,1);
insert into USERS_AUTHORITIES 
values(1,2);
