package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() throws SauvegardeImpossible 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		try 
		{
			String requete = "SELECT * FROM ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
		/*	while (ligues.next()) {
				gestionPersonnel.addLigue(ligues.getString(2));
				System.out.println(ligues.getString(2));
			}*/
		// requête qui séléctionnne toutes les données de l'employé
//			String requete2 = "select * from employe";
//			Statement instruction2 = connection.createStatement();
//			ResultSet employe = instruction2.executeQuery(requete2);
//			while (employe.next())
//				gestionPersonnel.addLigue(employe.getString("nom_employe"));
			while (ligues.next()) {
				Ligue ligue =  gestionPersonnel.addLigue(ligues.getInt("num_ligue"), ligues.getString("nom_ligue"));
			PreparedStatement response = connection.prepareStatement("SELECT * FROM employe where num_ligue = ?");
			response.setInt(1, ligues.getInt("num_ligue"));
			ResultSet employe1 = response.executeQuery();
		//	Ligue ligue = gestionPersonnel.getLigues().last();
	
			while (employe1.next()) {
				int id = employe1.getInt ("id_employe");
				String nom = employe1.getString ("nom_employe");
				String prenom = employe1.getString("prenom");
				String mail = employe1.getString("mail");
				String password = employe1.getString("password");
				LocalDate dateArrivee = LocalDate.parse(employe1.getString("date_arrivee")); 
				LocalDate dateDepart =  LocalDate.parse(employe1.getString("date_depart"));
				Employe employes = ligue.addEmploye(nom, prenom, mail, password, dateArrivee, dateDepart);
				    
				    if(employe1.getBoolean("admin")) {
				    	ligue.setAdministrateur(employes);
			}
		}
	}
}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	//insertion ligue
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("INSERT INTO ligue (nom_ligue) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1); // Valeur de l'auto-incrément
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	@Override
	public void update(Ligue ligue) throws SauvegardeImpossible {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE ligue SET nom_ligue = ? WHERE num_ligue = ?");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	//suppression de ligue
	@Override
	public void delete(Ligue ligue) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement listLigue;
			listLigue = connection.prepareStatement("DELETE FROM ligue WHERE num_ligue = ?");
			listLigue.setInt(1, ligue.getId());
			listLigue.executeUpdate();
		//	System.out.println("Ligue " + ligue.getNom() + " supprimé");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}
	
	// insertion employe
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible
	{
		try 
		{
			PreparedStatement instruction2;
			//Ajouter niveau privilege et admin dans requete
			instruction2 = connection.prepareStatement("insert into employe (nom_employe, prenom_employe, mail, password, niveau_privilege, date_arrivee, date_depart, num_ligue) values (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			instruction2.setString(1, employe.getNom());
			instruction2.setString(2, employe.getPrenom());
			instruction2.setString(3, employe.getMail());
			instruction2.setString(4, employe.getPassword());
			instruction2.setInt(5, employe.getLigue().getId());
			instruction2.setString(6, String.valueOf(employe.getDateArrivee()));
			instruction2.setString(7, String.valueOf(employe.getDateDepart()));
			instruction2.executeUpdate();
			ResultSet id = instruction2.getGeneratedKeys();
			id.next();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		return 0;
	}
	
	public void update(Employe employe, String dataList) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
		    instruction = connection.prepareStatement("UPDATE employe SET " + dataList + "= ? WHERE id_employe = ?");
		
				Map <String, String> map = new HashMap<>();
						map.put("nom_employe", employe.getNom());
						map.put("prenom_employe", employe.getPrenom());
						map.put("mail", employe.getMail());
						map.put("password", employe.getPassword());
						map.put("date_arrivee", String.valueOf(employe.getDateArrivee()));
						map.put("date_depart", String.valueOf(employe.getDateDepart()));
			instruction.setString(1, map.get(dataList));
		    instruction.setInt(2, employe.getId());
				instruction.executeUpdate();
			}
		
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public void delete(Employe employe) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement listEmploye;
			listEmploye = connection.prepareStatement("DELETE FROM employe WHERE id_employe = ?");
			listEmploye.setInt(1, employe.getId());
			listEmploye.executeUpdate();
		//	System.out.println("Ligue " + employe.getNom() + " supprimé"); Seulement pour debugger
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}
//	@Override
//	public void newAdmin(Employe employe) throws SauvegardeImpossible {
//
//	}
//
//	public Employe getAdmin(Employe estAdmin) throws SauvegardeImpossible
//{
//		try {
//			Statement intruction = connection.createStatement();
//			String requete = "SELECT * FROM employe WHERE niveau_privilege = 2";
//			ResultSet response = intruction.executeQuery(requete);
//			String nom = response.getString("nom_employe");
//			String prenom = response.getString("prenom_employe");
//			String mail =  response.getString("mail");
//			String password = response.getString("password");
//			estAdmin.setNom(nom);
//			estAdmin.setPrenom(prenom);
//			estAdmin.setMail(mail);
//			estAdmin.setPassword(password);
//			return estAdmin;
//		}
//		catch (SQLException e) {
//		e.printStackTrace();
//		throw new SauvegardeImpossible(e);
//		}
//	}
//
//	@Override
//	public void updateLigue(Ligue ligue) throws SauvegardeImpossible {
//		
//		
//	}
//
//	@Override
//	public void updateEmploye(Employe employe) throws SauvegardeImpossible {
//		
//		
//	}
//
//	@Override
//	public void removeAdmin(Ligue ligue) throws SauvegardeImpossible {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setAdmin(Employe employe) throws SauvegardeImpossible {
//		// TODO Auto-generated method stub
//		
//	}
//	

	@Override
	public void update(Employe employe) throws SauvegardeImpossible {
		// TODO Auto-generated method stub
		
	}
}

