create table Customers(
			id int not null,
			Name varchar(30) not null,
			Address varchar(64) not null
			);

create unique index Customers_id_uindex
			on Customers (id);

alter table Customers
			add constraint Customers_pk
			primary key (id);

alter table Customers modify id int auto_increment;

create table Listings
(
	id int not null,
	Region varchar(30) not null,
	Address varchar(64) not null,
	Price int not null,
	Capacity int not null
);

create unique index Listings_id_uindex
	on Listings (id);

alter table Listings
	add constraint Listings_pk
		primary key (id);

alter table Listings modify id int auto_increment

create table Transactions
(
	id int auto_increment primary key,
	cid int not null,
	lid int not null,
	startDate date not null,
	endDate date not null,
	constraint Transactions_Customers_id_fk
		foreign key (cid) references Customers (id),
	constraint Transactions_Listings_id_fk
		foreign key (lid) references Listings (id)
);

create unique index Transactions_id_uindex
	on Transactions (id);

