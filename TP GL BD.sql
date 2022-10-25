use gl;

CREATE TABLE users ( 
Username varchar(30),
Password varchar (30),
CONSTRAINT pk_users PRIMARY KEY (Username)
);


CREATE TABLE donneur (
	id_Donnneur INT,
	nom_donneur VARCHAR(30) NOT NULL,
	Contact INT DEFAULT NULL,
	Email VARCHAR(30) DEFAULT '',
	age_donneur INT NOT NULL,
	genre VARCHAR(30) NOT NULL check ( genre in ('male','female')),
	groupe_sanguin VARCHAR(30) NOT NULL check (groupe_sanguin in ('A+','A-','B+','B-','AB+','AB-','O+','O-')),
	CONSTRAINT pk_donneur PRIMARY KEY (id_Donnneur)
);


CREATE TABLE Event(
    id_Event VARCHAR(30),
    nom_Event VARCHAR(30) ,
    nom_Res VARCHAR(30) NOT NULL,
    location VARCHAR(30) NOT NULL,
    type_event VARCHAR(30) NOT NULL CHECK ( type_event in ('Sensibilisation','Organisation dons')),
    date_event DATE,
    heur_event VARCHAR(10),
    CONSTRAINT pk_Event PRIMARY KEY (id_Event)
);


CREATE TABLE rendezvous(
    id_RV VARCHAR(10),
    nom VARCHAR(30),
    prenom VARCHAR(30),
    date DATE not null,
    heure VARCHAR(10) not null,
    CONSTRAINT pk_RV PRIMARY KEY (id_RV)
);


CREATE TABLE stock(
numPack INT AUTO_INCREMENT,
nomDonneur VARCHAR(30) not null,
groupSanguin VARCHAR(30) check (groupSanguin in ('A+','A-','B+','B-','AB+','AB-','O+','O-')),
typeDon VARCHAR(30) check ( typeDon in ('sang total' ,'globules rouges','plasma,','plaquettes')),
dateDon DATE,
nbr_sac INT default 1 check (nbr_sac>0),
CONSTRAINT PK_stock PRIMARY KEY (numPack)
);


CREATE TABLE don(
    id_Donnneur INT,
    nom_donneur VARCHAR(30),
    Hopital VARCHAR(30),
    groupe_sanguin VARCHAR(30) NOT NULL check (groupe_sanguin in ('A+','A-','B+','B-','AB+','AB-','O+','O-')),
    date_collecte DATE,
    typeDon VARCHAR(30) check ( typeDon in ('sang total' ,'globules rouges','plasma,','plaquettes')),
    nom_Medecin_infermiere VARCHAR(30) default '',
    nbr_sac INT default 1 check (nbr_sac>0),
    CONSTRAINT pk_don PRIMARY KEY (id_Donnneur)
);


CREATE TABLE demande(
        VARCHAR(30) NOT NULL,
        nom_demandeur VARCHAR(30) NOT NULL,
        Hopital VARCHAR(30) DEFAULT '' ,
        typeDon VARCHAR(30) check ( typeDon in ('sang total' ,'globules rouges','plasma','plaquettes')),
        groupe_sanguin VARCHAR(30) check (groupe_sanguin in ('A+','A-','B+','B-','AB+','AB-','O+','O-')),
        state VARCHAR(30) DEFAULT('En attente') check (state in ('En attente','Approuvé','Transferé')) ,
        isUrgent VARCHAR(30) check ( isUrgent in ('urgent','normal')),
        nbr_sac int not null,
        Contact INT,
        dateDem DATE,
        CONSTRAINT pk_demande PRIMARY KEY (id_Demmande)
);


CREATE TRIGGER stockTrigger after 
insert on don For each row 
insert into stock(nomDonneur,groupSanguin,typeDon,dateDon,nbr_sac) values (new.nom_donneur,new.groupe_sanguin,new.typeDon,new.date_collecte,new.nbr_sac);