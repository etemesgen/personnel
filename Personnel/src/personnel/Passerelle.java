package personnel;

public interface Passerelle 
{
	public GestionPersonnel getGestionPersonnel();
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel)  throws SauvegardeImpossible;
	public int insert(Ligue ligue) throws SauvegardeImpossible;
<<<<<<< HEAD
	public int insert(Employe employe) throws SauvegardeImpossible; //lien avec la class JDBC
=======
	public void newAdmin(Employe employe) throws SauvegardeImpossible;
>>>>>>> branch 'master' of https://github.com/etemesgen/personnel.git
}
