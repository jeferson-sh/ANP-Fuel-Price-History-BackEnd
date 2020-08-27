--Create table users
create table users(
    id bigint auto_increment not null,
    name text not null,
    username text not null,
    password text not null,
    primary key(id)
);

-- Cretate Table authorities
create table authorities(
    id bigint auto_increment not null,
    authority text not null,
    primary key(id)
);

create table users_authorities(
    user_id bigint not null,
    authority_id bigint not null,
    CONSTRAINT users_authorities_pk PRIMARY KEY (user_id, authority_id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT FK_authorities FOREIGN KEY (authority_id) REFERENCES authorities(id)
);

--banners
create table banner(
    id bigint auto_increment not null,
    name text not null,
    primary key(id)
);

--counties
create table county(
    id bigint auto_increment not null,
    name text not null,
    primary key(id)
);

--products
create table product(
    id int auto_increment not null,
    name text not null,
    primary key(id)
);

--regions
create table region(
    id int auto_increment not null,
    name text not null,
    primary key(id)
);

--resellers
create table reseller(
    id bigint auto_increment not null,
    name text not null,
    cnpj text not null,
    primary key(id)
);

--states
create table state(
    id int auto_increment not null,
    uf text not null,
    primary key(id)
);

--fuels prices history
create table fuel_price_history(
    id bigint auto_increment not null,
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