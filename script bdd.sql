-- -----------------------------------------------------------------------------
--      Nom de la base : GestionPersonnel
--      Projet : PPE application JAVA
--      Auteur : Diariyata & Edomiyas
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
--       CREATION DE LA BASE 
-- -----------------------------------------------------------------------------

CREATE DATABASE 
GestionPersonnel;

-- -----------------------------------------------------------------------------
--       TABLE : Employe
-- -----------------------------------------------------------------------------

CREATE TABLE Employe
   (
    id_employe INT(11) AUTO_INCREMENT,   
    nom_employe VARCHAR(40) NOT NULL  ,
	prenom_employe VARCHAR(50) NOT NULL  ,
	mail VARCHAR(100) NOT NULL  ,
	password VARCHAR(11) NOT NULL  , 
	niveau_privilège INT(11) NULL  ,
	date_arrivee DATE NOT NULL  ,
	date_depart DATE NULL ,
	 PRIMARY KEY (id_employe) 
   );
   
-- -----------------------------------------------------------------------------
--       TABLE : Ligue
-- -----------------------------------------------------------------------------

CREATE TABLE Ligue
 (
   num_ligue INT(11) AUTO_INCREMENT, 
   nom_ligue VARCHAR(35) NOT NULL ,
   nom_ligue VARCHAR(35) NOT NULL  ,
	PRIMARY KEY (num_ligue) 
 );
 
-- -----------------------------------------------------------------------------
--       TABLE : Employe
-- -----------------------------------------------------------------------------

CREATE TABLE Employe
   (
    id_employe INT(11) AUTO_INCREMENT,   
    nom_employe VARCHAR(40) NOT NULL  ,
	prenom_employe VARCHAR(50) NOT NULL  ,
	mail VARCHAR(100) NOT NULL  ,
	password VARCHAR(11) NOT NULL  , 
	niveau_privilege INT(11) NULL  ,
	date_arrivee DATE NOT NULL  ,
	date_depart DATE NULL ,
	num_ligue INT(11) NULL ,
	 PRIMARY KEY (id_employe) ,
	 constraint fk_employe_ligue foreign key (num_ligue) references Ligue (num_ligue)
   );
   
 foreign key('num_ligue') references Ligue('num_ligue');
