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
<<<<<<< HEAD
=======
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
>>>>>>> branch 'master' of https://github.com/etemesgen/personnel.git
--       TABLE : Ligue
-- -----------------------------------------------------------------------------

CREATE TABLE Ligue
 (
   num_ligue INT(11) AUTO_INCREMENT, 
<<<<<<< HEAD
   nom_ligue VARCHAR(35) NOT NULL ,
=======
   nom_ligue VARCHAR(35) NOT NULL  ,
>>>>>>> branch 'master' of https://github.com/etemesgen/personnel.git
	PRIMARY KEY (num_ligue) 
 );
 
<<<<<<< HEAD
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
   
=======
 foreign key('num_ligue') references Ligue('num_ligue');
>>>>>>> branch 'master' of https://github.com/etemesgen/personnel.git
