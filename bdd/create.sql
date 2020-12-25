drop table PLAT;
drop table FILM_COMMANDES;
drop table COMMANDE;
drop table CLIENT;
drop sequence commandeSequence;
 

create table CLIENT(
	idClient integer not null,
	nomClient varchar (20) not null,
	prenomClient varchar (20) not null,
	photoClient varchar (60) not null,
	emailClient varchar (40) not null,
	telClient varchar (14) not null,
	noAdrClient varchar (10) not null,
	rueAdrClient varchar (50) not null,
	cpAdrClient varchar (6) not null,
	villeAdrClient varchar (30) not null,
	constraint pk_CLIENT_id primary key (idClient)
);

create table COMMANDE(
	idCommande integer not null,
	idClient integer not null,
	dateCommande date not null,
	prixCommande float not null,
	noAdrLivraison varchar(10) not null,
	rueAdrLivraison varchar (50) not null,
	cpAdrLivraison varchar (6) not null,
	villeAdrLivraison varchar (30) not null,
	constraint pk_COMMANDE_id primary key (idCommande),
	constraint fk_COMMANDE_to_CLIENT foreign key (idClient) references CLIENT (idClient)
);

create table FILM_COMMANDES(
	idFilmCommande integer not null,
	idCommande integer not null,
	prixFilm float not null,
	constraint pk_FILM_COMMANDES_id primary key (idFilmCommande, idCommande),
	constraint fk_FILM_to_COMMANDE foreign key (idCommande) references COMMANDE (idCommande)
);

create table PLAT(
	idPlat varchar(25) not null,
	idCommande integer not null,
	constraint pk_CARTE_id primary key (idPlat, idCommande),
	constraint fk_CARTE_to_COMMANDE foreign key (idCommande) references COMMANDE (idCommande)
);


create sequence commandeSequence;


create or replace trigger commandeInsert
  before insert on COMMANDE
  for each row
begin
  select commandeSequence.nextval
  into :new.idCommande
  from dual;
end;
