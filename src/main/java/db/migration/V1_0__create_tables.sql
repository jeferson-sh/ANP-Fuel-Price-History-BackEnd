--Create table users
create table application_user(
    id bigint, 
    name text, 
    username text,
    password text, 
    primary key(id) 
);

-- Cretate Table authorities
create table authority(
    id bigint, 
    authority text, 
    primary key(id)
);

--create table application_user_authorities
--(
  --application_user_id bigint not null,
  --authorities_id bigint not null,
  --CONSTRAINT application_user_authorities_pk PRIMARY KEY (application_user_id, authorities_id),
  --CONSTRAINT fk_users 
    --  FOREIGN KEY (application_user_id) REFERENCES application_user(id),
  --CONSTRAINT FK_authorities 
    --  FOREIGN KEY (authorities_id) REFERENCES authority(id)
--);

--banners
create table banner(
    id bigint, 
    name text, 
    primary key(id)
);

--counties
create table county(
    id bigint,
    name text(2), 
    primary key(id)
);

--products
create table product(
    id int, 
    name text, 
    primary key(id)
);

--regions
create table region(
    id int(1), 
    name text, 
    primary key(id)
);

--resellers
create table reseller(
    id bigint, 
    name text, 
    cnpj text, 
    primary key(id)
);

--states
create table state(
    id bigint, 
    uf text(2), 
    primary key(id)
);

--fuels prices history
create table fuel_price_history(
    id bigint, 
    name text, 
    banner_id bigint, 
    count_id bigint, 
    product_id bigint, 
    region_id bigint, 
    reseller_id bigint, 
    state_id bigint,  
    primary key(id),
    foreign key (banner_id) references banner(id),
    foreign key (count_id) references banner(id),
    foreign key (product_id) references product(id),
    foreign key (region_id) references region(id),
    foreign key (reseller_id) references reseller(id),
    foreign key (state_id) references state(id)
);




