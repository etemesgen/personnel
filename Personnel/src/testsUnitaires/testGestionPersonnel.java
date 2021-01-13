package testsUnitaires;
//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;


class testGestionPersonnel {
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	// Itération 1 test unitaire Gestion personnel
	@Test
	void testAddLigue() throws SauvegardeImpossible{
		Ligue ligue = new Ligue(gestionPersonnel, "ligueOne");
		assertEquals("ligueOne", ligue.getNom());
	}

}
