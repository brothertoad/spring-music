create table artists (
id serial primary key,
name varchar(100),
sortName varchar(100)
);

create table albums (
id serial primary key,
artist integer references artists on delete cascade,
name varchar(100),
sortName varchar(100),
numTracks integer
);

create table songs (
id serial primary key,
album integer references albums on delete cascade,
title varchar(100),
sortTitle varchar(100),
trackNum integer,
ripped boolean default false,
discarded boolean default false,
replace boolean default false
);
