create table if not exists district(
    code char(6) not null unique,
    name varchar(40) not null,
    enabled tinyint default 1,
    primary key (code)
);
create table if not exists organization(
    id int not null auto_increment unique,
    name varchar(40) unique,
    enabled tinyint default 1,
    primary key (id)
);
create table if not exists users(
    id int not null auto_increment unique,
    username varchar(20) not null unique,
    password varchar(120) not null,
    district_code char(6),
    organization_id int,
    realname varchar(20) not null,
    telephone char(11),
    enabled tinyint default 0,
    primary key(id)
);
create table if not exists authorities(
    username varchar(20) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
