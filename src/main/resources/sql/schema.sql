create table users(
    id int not null auto_increment unique,
    username varchar(20) not null unique,
    password varchar(120) not null,
    enabled tinyint default 0,
    primary key(id)
);
create table authorities(
    username varchar(20) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
