package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.time.LocalDate;
import java.util.ArrayList;
import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;

import personnel.*;

public class LigueConsole 
{
	private GestionPersonnel gestionPersonnel;
	private EmployeConsole employeConsole;

	public LigueConsole(GestionPersonnel gestionPersonnel, EmployeConsole employeConsole)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = employeConsole;
	}


	Menu menuLigues()
	{
		Menu menu = new Menu("G�rer les ligues", "l");
		menu.add(afficherLigues());
		menu.add(ajouterLigue());
		menu.add(selectionnerLigue());
		menu.addBack("q");
		return menu;
	}

	private Option afficherLigues()
	{
		return new Option("Afficher les ligues", "l", () -> {System.out.println(gestionPersonnel.getLigues());});
	}

	private Option afficher(final Ligue ligue)
	{
		return new Option("Afficher la ligue", "l", 
				() -> 
				{
					System.out.println(ligue);
					System.out.println("administr�e par " + ligue.getAdministrateur());
				}
		);
	}
	private Option afficherEmployes(final Ligue ligue)
	{
		return new Option("Afficher les employes", "l", () -> {System.out.println(ligue.getEmployes());});
	}

	private Option ajouterLigue()
	{
		return new Option("Ajouter une ligue", "a", () -> 
		{
			try
			{
				gestionPersonnel.addLigue(getString("nom : "));
			}
			catch(SauvegardeImpossible exception)
			{
				System.err.println("Impossible de sauvegarder cette ligue");
			}
		});
	}
	
	private Menu editerLigue(Ligue ligue)
	{
		Menu menu = new Menu("Editer " + ligue.getNom());
		menu.add(afficher(ligue));
		menu.add(gererEmployes(ligue));
 		menu.add(changerAdministrateur(ligue, "c"));
		menu.add(changerNom(ligue));
		menu.add(supprimer(ligue));
		menu.addBack("q");
		return menu;
	}
	
	// It�ration 2
	private Menu editerEmploye(Ligue ligue) {
		Menu menu = new Menu("Editer " + ligue.getEmployes());
		menu.add(modifierEmploye(ligue));
//		menu.add(selectionAdministrateur(ligue));
		menu.add(supprimerEmploye(ligue));
		return menu;
	}

	private Option changerNom(final Ligue ligue)
{
	return new Option("Renommer", "r", 
			() -> {try {
				ligue.setNom(getString("Nouveau nom : "));
			} catch (SauvegardeImpossible e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
}
	
//It�ration 2 : Changer d'administrateur
/*		private Option changerAdministrateur (final Ligue ligue, final Employe admin)
		{
			return new Option ("Changer d'administrateur" , "c", () -> {ligue.setAdministrateur(admin);});
			
		}
*/
private List<Ligue> selectionnerLigue()
	{
		return new List<Ligue>("S�lectionner une ligue", "e", 
				() -> new ArrayList<>(gestionPersonnel.getLigues()),
				(element) -> editerLigue(element)
				);
	}
	

	private Option ajouterEmploye(final Ligue ligue) throws SauvegardeImpossible
	{
		return new Option("ajouter un employ�", "a",
				() -> 
				{
					System.out.println("Ajouter un employ�");
					try {
						ligue.addEmploye(getString("nom : "), 
							getString("prenom : "),
							getString("mail : "), 
							getString("password : "), 
							LocalDate.parse(getString("Date d'arriv�e (YYYY-MM-DD) : ")), 
							LocalDate.parse(getString("Date de d�part (YYYY-MM-DD) : ")));
					} catch (SauvegardeImpossible e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			/*It�ration 2 Ajout des dates */	
					//	LocalDate.parse(getString("Date d'arriv�e (YYYY-MM-DD) : ")), LocalDate.parse(getString("Date de d�part (YYYY-MM-DD) : "));		
			}
		);
	}
	
	private LocalDate getInt(String string) {
		return null;
	}


	private Menu gererEmployes(Ligue ligue)
	{
		Menu menu = new Menu("G�rer les employ�s de " + ligue.getNom(), "e");
		menu.add(afficherEmployes(ligue));
		try {
			menu.add(ajouterEmploye(ligue));
		} catch (SauvegardeImpossible e) {
			System.err.println("Impossible de sauvegarder cet employ�");
			e.printStackTrace();
		}
		menu.add(selectionnerEmploye(ligue));
/*		menu.add(modifierEmploye(ligue));
		menu.add(supprimerEmploye(ligue));*/  
//		menu.add(selectionAdministrateur(ligue));
		menu.add(changerAdministrateur(ligue, "c"));
		menu.addBack("q");
		return menu;
	}
	
	// It�ration 2 S�lectionner un employ� avant de modifier
	private List<Employe> selectionnerEmploye(Ligue ligue)
	{
		return new List<>("S�lectionner un employ�", "s", 
				() -> new ArrayList<>(ligue.getEmployes()),
				employeConsole.editerEmploye()
				);
	}

	private List<Employe> supprimerEmploye(final Ligue ligue)
	{
		return new List<>("Supprimer un employ�", "x", 
				() -> new ArrayList<>(ligue.getEmployes()),
				(index, element) -> {try {
					element.remove();
				} catch (SauvegardeImpossible e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				);
	}

	private List<Employe> modifierEmploye(final Ligue ligue)
	{
		return new List<>("Modifier un employ�", "e", 
				() -> new ArrayList<>(ligue.getEmployes()),
				employeConsole.editerEmploye()
				);
	}
	
	private Option supprimer(Ligue ligue)
	{
		return new Option("Supprimer", "d", () -> {try {
			ligue.getAdministrateur().remove();
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
	}

	/*It�ration 3*/
	/*private List<Employe> selectionAdministrateur(Ligue ligue)
	{
		return new List<>("Nommer en tant qu'administrateur ", "n", 
				() -> new ArrayList<>(ligue.getEmployes()),
				employeConsole.nommerAdmin());
	}*/
	
	private List<Employe> changerAdministrateur(final Ligue ligue, String key)
	{
		List<Employe> liste = new List<>("Changer d'administrateur", key, 
				() -> new ArrayList<>(ligue.getEmployes()),
				(index, element) -> {
					try {
						ligue.setAdministrateur(element);
					} catch (SauvegardeImpossible e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		liste.addBack("q");
		return liste;
	}
}
