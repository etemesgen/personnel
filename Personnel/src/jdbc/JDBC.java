package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		try 
		{
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
			
		// requête qui séléctionnne toutes les données de l'employé
			String requete2 = "select * from employe";
			Statement instruction2 = connection.createStatement();
			ResultSet employe = instruction2.executeQuery(requete2);
			while (employe.next())
				gestionPersonnel.addLigue(employe.getInt(1), employe.getString(2));
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
	
	// insertion concernant l'employe
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible
	{
		try 
		{
			PreparedStatement instruction2;
			instruction2 = connection.prepareStatement("insert into employe (nom_employe, prenom_employe, mail, password, niveau_privilege, date_arrivee, date_depart, num_ligue) values (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			instruction2.setString(1, employe.getNom());
			instruction2.setString(2, employe.getPrenom());
			instruction2.setString(3, employe.getMail());
			instruction2.setString(4, employe.getPassword());
			/*instruction2.setInt(5, employe.getNiveauPrivilege());*/
			instruction2.setInt(6, employe.getLigue().getId());
			/*instruction2.setLocalDate(7, employe.getDateArrivee());
			instruction2.setLocalDate(8, employe.getDateDepart());*/
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
	@Override
	public void newAdmin(Employe employe) throws SauvegardeImpossible {

	}
}
