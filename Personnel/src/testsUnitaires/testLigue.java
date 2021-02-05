package testsUnitaires;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}

	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", null, null); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	   // It�ration 1 test unitaire employ� 
	   @Test	
	   void getNom() throws SauvegardeImpossible{
		      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
	          assertEquals("ligueOne", ligue.getNom());
	   }
	   
	   @Test
	   void setNom() throws SauvegardeImpossible{
		      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
	          ligue.setNom("ligueOne");
	          assertEquals("ligueOne", ligue.getNom());
	   }
	   
	   @Test
	   void remove() throws SauvegardeImpossible{
		      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
	          ligue.remove();
	          assertEquals(0, ligue.getEmployes().size());
	   }   	
	   
	   @Test
	   void toStringEmploye() throws SauvegardeImpossible{
		      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
	          assertEquals("ligueOne", ligue.toString());
	   }
<<<<<<< HEAD
	   
=======

	   @Test
>>>>>>> branch 'master' of https://github.com/etemesgen/personnel.git
	   void compareTo() throws SauvegardeImpossible{
		      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
		      Ligue ligueTwo = gestionPersonnel.addLigue("ligueTwo");
	          assertTrue(ligueTwo.compareTo(ligue)>0);
	   }
	   
<<<<<<< HEAD
	   void getAdmin() throws SauvegardeImpossible{
=======

       @Test
       void getAdmin() throws SauvegardeImpossible{
>>>>>>> branch 'master' of https://github.com/etemesgen/personnel.git
		      Ligue ligue = gestionPersonnel.addLigue("ligueOne");
		      assertEquals(gestionPersonnel.getRoot(), ligue.getAdministrateur());
	   }
	   
}
