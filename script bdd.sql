-- -----------------------------------------------------------------------------
--             Génération d'une base de données pour
--                           MySQL
--                     (20/1/2021 13:00:00)
-- -----------------------------------------------------------------------------
--      Nom de la base : GestionPersonnel
--      Projet : PPE application JAVA
--      Auteur : Diariyata & Edomiyas
--      Date de dernière modification : 
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
    id_employe INT(11) NOT NULL AUTO_INCREMENT,   
    nom_employe VARCHAR(40) NULL  ,
	prenom_employe VARCHAR(50) NULL  ,
	mail VARCHAR(100) NULL  ,
	password VARCHAR(11) NULL  , 
	niveau_privilège VARCHAR(25) NULL  ,
	date_arrivee DATE NOT NULL  ,
	date_depart DATE NULL ,
	 PRIMARY KEY (id_employe) 
   );
   
-- -----------------------------------------------------------------------------
--       TABLE : Ligue
-- -----------------------------------------------------------------------------

CREATE TABLE Ligue
 (
   num_ligue INT(11) NOT NULL AUTO_INCREMENT, 
   nom_ligue VARCHAR(35) NULL  ,
	PRIMARY KEY (num_ligue) 
 );