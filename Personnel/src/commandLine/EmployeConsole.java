package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.time.LocalDate;

import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class EmployeConsole 
{
	private GestionPersonnel gestionPersonnel;
	private LigueConsole ligueConsole;
	
	public EmployeConsole(GestionPersonnel gestionPersonnel, LigueConsole ligueConsole)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.ligueConsole = ligueConsole;
	}
	
	
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employé", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}
	
	
	// Itération 2 : Selectionner un employé avant de le modifier
/*	Option SelectionnerEmploye(Employe employe){ 
		
		
	}*/

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "c");
	//		menu.add(SelectionnerEmploye());
	//		Menu menu = new Menu("Sélectionner un employé " + employe.getNom(), "s");
    //		menu.add(SelectionnerEmploye());
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateArrivee(employe)); //itération 2 : ajouter les dates au menu dialogue
			menu.add(changerDateDepart(employe)); //itération 2 : ajouter les dates au menu dialogue
			menu.addBack("q");
			return menu;
	}
	
/*	private List<Employe> SelectionnerEmploye(){
		return new List<>("Sélectionner un employé", "s", 
				() -> new ArrayList<>(Ligue.getEmployes()),
				(element) -> editerEmploye(element)
				);
		
	}*/

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	
	private Option changerDateArrivee(final Employe employe)
	{
		return new Option ("Changer la date d'arrivee", "a", () -> {
			try {
			employe.setDateArrivee(employe.getDateArrivee());
		} catch (DateImpossible e) {
			e.printStackTrace();
		}
		System.out.println ("Nouvelle Date d'arrivee :");});
	} //itération 2 : ajouter les dates au menu dialogue

	private Option changerDateDepart(final Employe employe)
	{
		return new Option ("Changer la date depart", "d", () -> {
			try {
			employe.setDateDepart(employe.getDateDepart());
		} catch (DateImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println ("Nouvelle Date de départ :");});
	} //itération 2 : ajouter les dates au menu dialogue
}