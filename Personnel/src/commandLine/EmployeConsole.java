package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.time.LocalDate;

import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.DateImpossible;
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

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateArrivee(employe));
			menu.add(changerDateDepart(employe));
			menu.addBack("q");
			return menu;
	}
	

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
	
	/* Impossible de saisir les dates */
	private Option changerDateArrivee(final Employe employe)
	{
		return new Option("Changer la date d'arrivée", "a", () -> {
		try {
			System.out.println("Nouvelle date d'arrivée : ");
			employe.setDateArrivee(employe.getDateArrivee());
		} catch (DateImpossible e) {
			System.out.println ("La date d'arrivée doit être inférieur à la date de départ");
		}});
	}
	

	private Option changerDateDepart(final Employe employe)
	{
		return new Option ("Changer la date depart", "d", () -> {
			try {
				System.out.println ("Nouvelle Date de départ :");
				employe.setDateDepart(employe.getDateDepart());
		} catch (DateImpossible e) {
			System.out.println ("La date de départ doit être supérieur à la date d'arrivée");
		}
		});
	} 
}