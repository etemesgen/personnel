package testsUnitaires;
//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import personnel.DateImpossible;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

class testEmploye {
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		
   // Itération 1 test unitaire employé 
   @Test	
   void getPrenom() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          assertEquals("Doe", employe.getPrenom());
   }
   
   @Test
   void setPrenom() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          employe.setPrenom("Bale");
          assertEquals("Bale", employe.getPrenom());
   }
   
   @Test	
   void getNom() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          assertEquals("John", employe.getNom());
   }
   
   @Test
   void setNom() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          employe.setNom("Gareth");
          assertEquals("Gareth", employe.getNom());
   }
   
   @Test
   void isRoot() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
	      Employe employe = gestionPersonnel.getRoot();
          Employe employe2 = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          assertTrue(employe.estRoot());
          assertFalse(employe2.estRoot());
   }
   
   @Test
   void checkPassword() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          assertTrue(employe.checkPassword("admin"));
          assertFalse(employe.checkPassword("admiN"));
   }
   
   @Test
   void setPassword() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          employe.setPassword("pass");
          assertTrue(employe.checkPassword("pass"));
          assertFalse(employe.checkPassword("admin"));
   }
   
   @Test
   void remove() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          employe.remove();
          assertEquals(0, ligue.getEmployes().size());
   }
   
   @Test
   void compareTo() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe premierEmploye = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
          Employe deuxiemeEmploye = ligue.addEmploye("Gareth", "Bale", "Garethbale@mail.com", "admin2", null, null);
          assertEquals(2, deuxiemeEmploye.compareTo(premierEmploye));
   }
   
   @Test
   void toStringEmploye() throws SauvegardeImpossible{
	      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
          Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", null, null);
    //      Employe rootEmploye = gestionPersonnel.getRoot();
          assertEquals("John Doe Johndoe@mail.com (ligueOne)", employe.toString());
    //      assertEquals("root  (super-utilisateur)", rootEmploye.toString());
   }
   
   @Test
   void getDateDepart() throws SauvegardeImpossible{
	   Ligue ligue = gestionPersonnel.addLigue("ligueOne");
       Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", LocalDate.parse("2018-12-30"), LocalDate.parse("2018-12-28"));
       assertEquals(LocalDate.parse("2018-12-30"), employe.getDateDepart());
   }
   
   @Test
   void setDateDepart() throws SauvegardeImpossible, DateImpossible{
	  try {
	     Ligue ligue = gestionPersonnel.addLigue("ligueOne");
         Employe employe = ligue.addEmploye("John", "Doe", "Johndoe@mail.com", "admin", LocalDate.parse("2018-12-30"), LocalDate.parse("2018-12-28"));
         employe.setDateDepart(LocalDate.parse("2018-12-31"));
         assertEquals(LocalDate.parse("2018-12-31"), employe.getDateDepart());
         employe.setDateDepart(null);
         assertEquals(null, employe.getDateDepart());
         employe.setDateDepart(LocalDate.parse("2018-12-27"));
         fail("L'exception DateImpossible devrait être lancé");
	   } catch(DateImpossible err){
		  assertTrue(err instanceof DateImpossible);
	 }
	  
  }
   
}
