package personnel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeSet;
import java.util.SortedSet;


/**
 * Employ� d'une ligue h�berg�e par la M2L. Certains peuvent 
 * �tre administrateurs des employ�s de leur ligue.
 * Un seul employ�, rattach� � aucune ligue, est le root.
 * Il est impossible d'instancier directement un employ�, 
 * il faut passer la m�thode {@link Ligue#addEmploye addEmploye}.
 */

public class Employe implements Serializable, Comparable<Employe>
{
	private static final long serialVersionUID = 4795721718037994734L;
	private String nom, prenom, password, mail;
	private Ligue ligue;
	private SortedSet<Employe> employes; //it�ration 3
	private GestionPersonnel gestionPersonnel;
	private LocalDate dateDepart, dateArrivee;
	private int id;
	
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password, LocalDate dateArrivee, LocalDate dateDepart)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.ligue = ligue;
		this.dateArrivee = dateArrivee;  //It�ration 1
		this.dateDepart = dateDepart;  //It�ration 1
	
	}

	public Employe (GestionPersonnel gestionPersonnel, int id, String nom) //it�ration 3
		{
			this.nom = nom;
			employes = new TreeSet<>();
			this.gestionPersonnel = gestionPersonnel;
	//		administrateur = gestionPersonnel.getRoot();
			this.id = id;
		}

	/**
	 * Retourne vrai si l'employ� est administrateur de la ligue 
	 * pass�e en param�tre.
	 * @return vrai si l'employ� est administrateur de la ligue 
	 * pass�e en param�tre.
	 * @param ligue la ligue pour laquelle on souhaite v�rifier si this 
	 * est l'admininstrateur.
	 */
	
	public boolean estAdmin(Ligue ligue)
	{
		return ligue.getAdministrateur() == this;
	}
	
	/**
	 * Retourne vrai si l'employ� est le root.
	 * @return vrai si l'employ� est le root.
	 */
	
	public boolean estRoot()
	{
		return gestionPersonnel.getRoot() == this;
	}
	
	/**
	 * Retourne le nom de l'employ�.
	 * @return le nom de l'employ�. 
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom de l'employ�.
	 * @param nom le nouveau nom.
	 */
	
	public void setNom(String nom) throws SauvegardeImpossible
	{
		this.nom = nom;
		gestionPersonnel.update(this);
	}

	/**
	 * Retourne le pr�nom de l'employ�.
	 * @return le pr�nom de l'employ�.
	 */
	
	public String getPrenom()
	{
		return prenom;
	}
	
	/**
	 * Change le pr�nom de l'employ�.
	 * @param pr�nom le nouveau pr�nom de l'employ�. 
	 */

	public void setPrenom(String prenom) throws SauvegardeImpossible
	{
		this.prenom = prenom;
		gestionPersonnel.update(this);
	}

	/**
	 * Retourne le mail de l'employ�.
	 * @return le mail de l'employ�.
	 */
	
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Change le mail de l'employ�.
	 * @param mail le nouveau mail de l'employ�.
	 */

	public void setMail(String mail) throws SauvegardeImpossible  
	{
		this.mail = mail;
		gestionPersonnel.update(this);
	}
	
	public LocalDate getDateArrivee() {  //Getter pour la date d'arriv�e
		return dateArrivee;
	}
	
	public void setDateArrivee(LocalDate dateArrivee) throws DateImpossible{  //Setter pour la date d'arriv�e
		if(dateArrivee != null && dateDepart != null &&  dateDepart.isBefore(dateArrivee)) throw new DateImpossible();
		this.dateArrivee = dateArrivee;
	}
	
	public LocalDate getDateDepart() {  //Getter pour la date de d�part
		return dateDepart;
	}
	
	public void setDateDepart(LocalDate dateDepart) throws DateImpossible{  //Setter pour la date de d�part
		if(dateArrivee != null && dateDepart != null &&  dateArrivee.isAfter(dateDepart)) throw new DateImpossible();
		this.dateDepart = dateDepart;
	}
	
	/**
	 * Retourne vrai si le password pass� en param�tre est bien celui
	 * de l'employ�.
	 * @return vrai si le password pass� en param�tre est bien celui
	 * de l'employ�.
	 * @param password le password auquel comparer celui de l'employ�.
	 */
	
	public String getPassword()
	{
		return password;
	}

	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	/**
	 * Change le password de l'employ�.
	 * @param password le nouveau password de l'employ�. 
	 * @throws SauvegardeImpossible 
	 */
	
	public void setPassword(String password) throws SauvegardeImpossible
	{
		this.password= password;
		gestionPersonnel.update(this);
	}

	/**
	 * Retourne la ligue �laquelle l'employ� est affect�e.
	 * @return la ligue � laquelle l'employ� est affect�e.
	 */
	
	public Ligue getLigue()
	{
		return ligue;
	}

	/**
	 * Supprime l'employ�e. Si celui-ci est un administrateur, le root
	 * r�cup�re les droits d'administration sur sa ligue.
	 */
	
	public void remove() throws SauvegardeImpossible
	{
		Employe root = gestionPersonnel.getRoot();
		if (this != root)
		{
			if (estAdmin(getLigue()))
				getLigue().setAdministrateur(root);
			getLigue().remove(this);
		}
		else
			throw new ImpossibleDeSupprimerRoot();
	}

	@Override
	public int compareTo(Employe autre)
	{
		int cmp = getNom().compareTo(autre.getNom());
		if (cmp != 0)
			return cmp;
		return getPrenom().compareTo(autre.getPrenom());
	}
	
	@Override
	public String toString()
	{
		String res = nom + " " + prenom + " " + mail + " " + dateArrivee + " " + dateDepart + " (" ;
		if (estRoot())
			res += "super-utilisateur";
		else
			res += ligue.toString();
		return res + ")";
	}
	
	public int getId() { //it�ration 3
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void update(String string) throws SauvegardeImpossible {
		 gestionPersonnel.update(this);
	}
}
