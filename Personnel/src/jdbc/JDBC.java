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
			System.out.println("Pilote JDBC non installÃ©.");
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
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getString("nom_ligue"));
			PreparedStatement response = connection.prepareStatement("Select * from employe where num_ligue = ?");
			response.setInt(1, ligues.getInt("num_ligue"));
			ResultSet employe = response.executeQuery();
			Ligue ligue = gestionPersonnel.getLigues().last();
			
			while (employe.next()) {
				int id = employe.getInt ("id_employe");
				String nom = employe.getString ("nom_employe");
				String prenom = employe.getString("prenom_employe");
				String mail = employe.getString("mail");
				String password = employe.getString("password");
				LocalDate dateArrivee = LocalDate.parse(employe.getString("dateArrivee_employe"));
				LocalDate dateDepart = employe.getString("dateDepart_employe") != null ? LocalDate.parse(employe.getString("dateDepart_employe")) : null;
				Employe employes = ligue.addEmploye(nom, prenom, mail, password);
				    
				    if(employe.getBoolean("admin")) {
				    	ligue.setAdministrateur(employes);
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
			instruction = connection.prepareStatement("insert into ligue (nom_ligue) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	//suppression de ligue
	@Override
	public void deleteLigue(Ligue ligue) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement listLigue;
			listLigue = connection.prepareStatement("DELETE FROM ligue WHERE num_ligue = ?");
			listLigue.setInt(1, ligue.getId());
			listLigue.executeUpdate();
			System.out.println("Ligue " + ligue.getNom() + " supprimé");
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
			instruction2 = connection.prepareStatement("insert into employe (nom_employe, prenom_employe, mail, password, date_arrivee, date_depart, num_ligue) values (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
	
	public void updateEmploye(Employe employe, String dataList) throws SauvegardeImpossible 
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
	public void deleteEmploye(Employe employe) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement listEmploye;
			listEmploye = connection.prepareStatement("DELETE FROM employe WHERE id_employe = ?");
			listEmploye.setInt(1, employe.getId());
			listEmploye.executeUpdate();
			System.out.println("Ligue " + employe.getNom() + " supprimé");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}
	@Override
	public void newAdmin(Employe employe) throws SauvegardeImpossible {

	}

	public Employe getAdmin(Employe estAdmin) throws SauvegardeImpossible
{
		try {
			Statement intruction = connection.createStatement();
			String requete = "SELECT * FROM employe WHERE niveau_privilege = 2";
			ResultSet response = intruction.executeQuery(requete);
			String nom = response.getString("nom_employe");
			String prenom = response.getString("prenom_employe");
			String mail =  response.getString("mail");
			String password = response.getString("password");
			estAdmin.setNom(nom);
			estAdmin.setPrenom(prenom);
			estAdmin.setMail(mail);
			estAdmin.setPassword(password);
			return estAdmin;
		}
		catch (SQLException e) {
		e.printStackTrace();
		throw new SauvegardeImpossible(e);
		}
	}

	@Override
	public void updateLigue(Ligue ligue) throws SauvegardeImpossible {
		
		
	}

	@Override
	public void updateEmploye(Employe employe) throws SauvegardeImpossible {
		
		
	}
	
}

