package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.time.LocalDate; /*It�ration 2*/

import javax.sound.midi.SysexMessage;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.DateImpossible;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.SauvegardeImpossible;


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
		return new Option("Afficher l'employ�", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	/* It�ration 3 */
/*	ListOption<Employe> nommerAdmin()
	{
		return (employe) -> nommerAdmin(employe);		
	}*/

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("G�rer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateArrivee(employe));
			menu.add(changerDateDepart(employe));
//			menu.add(nommerAdministrateur(employe));
			menu.add(supprimer(employe));
			menu.addBack("q");
			return menu;
	}
	
	/* It�ration 3 */
/*	Option nommerAdmin(Employe employe) 
	{
			Menu menu = new Menu("D�finir en tant qu'admin " + employe.getNom(), "e");
			menu.add(nommerAdministrateur(employe));
			menu.addBack("q");
			return menu;
			
	}
*/	

	private Option changerNom(final Employe employe) 
	{
		return new Option("Changer le nom", "n", 
				() -> {try {
					employe.setNom(getString("Nouveau nom : "));
				} catch (SauvegardeImpossible e) {
					// TODO Auto-generated catch block
					System.out.println("Impossible d'acc�der � la base de donn�es");
				}}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le pr�nom", "p", () -> {try {
			employe.setPrenom(getString("Nouveau pr�nom : "));
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'acc�der � la base de donn�es");
		}});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {try {
			employe.setMail(getString("Nouveau mail : "));
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'acc�der � la base de donn�es");
		}});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {try {
			employe.setPassword(getString("Nouveau password : "));
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'acc�der � la base de donn�es");
		}});
	}
	
	/* It�ration 2 Changer les dates */
	private Option changerDateArrivee(final Employe employe)
	{

		return new Option("Changer la date d'arriv�e", "a", () -> {
		try {
			System.out.println("Nouvelle date d'arriv�e : ");
			employe.setDateArrivee(LocalDate.parse(getString("Date d'arriv�e (YYYY-MM-DD) : ")));
		} catch (DateImpossible e) {
			System.out.println("La date d'arriv�e doit �tre inf�rieur � la date de d�part !");
		}});
	}

	private Option changerDateDepart(final Employe employe)
	{
		return new Option("Changer la date de d�part", "d", () -> {
		try {
			System.out.println("Nouvelle date de d�part : ");
			employe.setDateDepart(LocalDate.parse(getString("Date d'arriv�e (YYYY-MM-DD) : ")));
		} catch (DateImpossible e) {
			System.out.println("La date de d�part doit �tre sup�rieur � la date d'arriv�e !");
		}});
	}
	
	private Option supprimer(Employe employe)
	{
		return new Option("Supprimer", "s", () -> {
			try {
				employe.remove();
			} catch (SauvegardeImpossible e) {
				// TODO Auto-generated catch block
				System.out.println("Impossible d'acc�der � la base de donn�es");
			}
		});
	}

	/* It�ration 3 
	private Option nommerAdministrateur (final Employe employe)
	{
		return new Option ("Nommer comme administrateur de la ligue" , "n", () -> 
		{
//			employe.setAdministrateur(gestionPersonnel.getLigue(employe));
		});
		
	}*/

 }

