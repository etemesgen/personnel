package interfaceGraphique;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import personnel.DateImpossible;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;



public class ModifierEmploye {
	
	private static Ligue ligue;
	private static GestionPersonnel gestionPersonnel;
	private static Accueil ligues;
	private JTextField nom;
	private JTextField prenom;
	private JTextField mail;
	private JTextField pass;
	private JTextField dateArrivee;
	private JTextField dateDepart;
	private Employe connectedEmploye;
	private Employe employe;
	
    
	public ModifierEmploye(GestionPersonnel gestionPersonnel, Employe employe, Ligue ligue, Employe connectedEmploye) {
		   this.gestionPersonnel = gestionPersonnel;
		   this.ligue = ligue;
		   this.employe = employe;
		   this.connectedEmploye = connectedEmploye;
	}
	
	
	public void listData()
	{
		frame().setVisible(true);
	}
	
	public JFrame frame()
	{
		JFrame employes = new JFrame();
		employes.getContentPane().setBackground(Color.decode("#f2ef13"));
		employes.setSize(700,700);
		employes.setLocationRelativeTo(null);
		employes.setJMenuBar(menuBar());
		employes.setLayout(new GridBagLayout());
		employes.add(panel());
		employes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return employes;
	}
	
	 private JMenuBar menuBar()
	 {
		 JMenuBar menubar = new JMenuBar();
		 menubar.setPreferredSize(new Dimension(60,60));
		 JMenu menu = new JMenu("MON COMPTE");
		 menu.add(menuItem());
		 menu.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		 menu.setSize(70,70);
		 menu.setForeground(Color.decode("#FF0000"));
		 menubar.add(menu);
		 menubar.setBackground(Color.decode("#f2ef13"));
		return menubar;
	 }

	 private JMenuItem menuItem()
	 {
		 JMenuItem itemMenu = new JMenuItem("Gérer mon compte");
		 itemMenu.setFont(new Font("Rockwell Nova", Font.PLAIN, 20));
		 itemMenu.setBackground(Color.decode("#222222"));
		 itemMenu.setForeground(Color.decode("#ffffff"));
		 itemMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				    frame().setVisible(false);
					GererRoot account = new GererRoot(gestionPersonnel, connectedEmploye);
					account.AccountData();
			}
		});
		 return itemMenu;
	 }
	
	private JPanel panel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#f2ef13"));
		panel.setLayout(new GridLayout(0,2, 25,25));
		panel.setPreferredSize(new Dimension(750,650));		
		panel.add(nomL());
		panel.add(nameInput());
		panel.add(prenomL());
		panel.add(secondNameInput());
		panel.add(emailL());
		panel.add(mailInput());
		panel.add(passwordL());
		panel.add(passwordInput());
		panel.add(dateArriveeL());
		panel.add(DateArriveeInput());
		panel.add(dateDepartL());
		panel.add(DateDepartInput());
		panel.add(addEmploye());
		panel.add(cancelAdd());
		return panel;
	}
	
	private JLabel nomL()
	{
		JLabel nom = new JLabel("Nom : ");
		nom.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		nom.setForeground(Color.decode("#222222"));
		 return nom;	
	}
	
	private JLabel prenomL()
	{
		JLabel prenom = new JLabel("Prenom : ");
		prenom.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		prenom.setForeground(Color.decode("#222222"));
		 return prenom;	
	}
	
	private JLabel emailL()
	{
		JLabel emailL = new JLabel("Email : ");
		emailL.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		emailL.setForeground(Color.decode("#222222"));
		 return emailL;	
	}
	
	private JLabel passwordL()
	{
		JLabel passwordL = new JLabel("Mot de passe : ");
		passwordL.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		passwordL.setForeground(Color.decode("#222222"));
		 return passwordL;	
	}
	
	private JLabel dateArriveeL()
	{
		JLabel dateArriveeL = new JLabel("Date d'arrivée : ");
		dateArriveeL.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		dateArriveeL.setForeground(Color.decode("#222222"));
		 return dateArriveeL;	
	}
	
	private JLabel dateDepartL()
	{
		JLabel dateDepartL = new JLabel("Date de départ : ");
		dateDepartL.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		dateDepartL.setForeground(Color.decode("#222222"));
		 return dateDepartL;	
	}
	
	private JButton addEmploye()
	{
		JButton addbtn = new JButton("Enregistrer");
		addbtn.setBackground(Color.decode("#222222"));
		addbtn.setForeground(Color.decode("#FFFFFF"));
		addbtn.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			  try {
				employe.setNom(nom.getText());
			} catch (SauvegardeImpossible e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			  try {
				employe.setPrenom(prenom.getText());
			} catch (SauvegardeImpossible e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			  try {
				employe.setMail(mail.getText());
			} catch (SauvegardeImpossible e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			  try {
				employe.setPassword(pass.getText());
			} catch (SauvegardeImpossible e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			  try {
				employe.setDateArrivee(LocalDate.parse(dateArrivee.getText()));
			} catch (DateImpossible e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  try {
				employe.setDateDepart(dateDepart.getText().isEmpty() ? null : LocalDate.parse(dateDepart.getText()));
			 } catch (DateImpossible e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  frame().setVisible(false);
			  frame().dispose();
			  GererEmploye employespage = new GererEmploye(gestionPersonnel, ligue, connectedEmploye);
			  employespage.GererEmploye();
			}
		});
		return addbtn;
	}
	
	private JButton cancelAdd()
	{
		JButton cancelbtn = new JButton("Annuler");
		cancelbtn.setBackground(Color.decode("#222222"));
		cancelbtn.setForeground(Color.decode("#FFFFFF"));
		cancelbtn.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		cancelbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  frame().setVisible(false);
				  frame().dispose();
				  GererEmploye employespage = new GererEmploye(gestionPersonnel, ligue, connectedEmploye);
				  employespage.GererEmploye();
			}
		});
		return cancelbtn;
	}
	
	private JTextField nameInput()
	{
		nom = new JTextField();
		nom.setEditable(true);
		if(employe.getNom() != null)
		{
			nom.setText(employe.getNom());
		}
		return nom;
	}
	
	private JTextField secondNameInput()
	{
		prenom = new JTextField();
		prenom.setEditable(true);
		if(employe.getPrenom() != null)
		{
			prenom.setText(employe.getPrenom());
		}
		return prenom;
	}
	
	
	private  JTextField mailInput()
	{
		mail = new JTextField();
		mail.setEditable(true);
		if(employe.getMail() != null)
		{
			mail.setText(employe.getMail());
		}
		return mail;
	}
	
	private  JTextField passwordInput()
	{
		pass = new JTextField();
		pass.setEditable(true);
		if(employe.getPassword() != null)
		{
			pass.setText(employe.getPassword());
		}
		return pass;
	}
	
	private  JTextField DateArriveeInput()
	{
		dateArrivee = new JTextField();
		dateArrivee.setEditable(true);
		if(employe.getDateArrivee() != null)
		{
			dateArrivee.setText(String.valueOf(employe.getDateArrivee()));
		}
		return dateArrivee;
	}
	
	private JTextField DateDepartInput()
	{
		dateDepart = new JTextField();
		dateDepart.setEditable(true);
		if(employe.getDateDepart() != null) {
			dateDepart.setText(String.valueOf(employe.getDateDepart()));
		}
		return dateDepart;
	}
	
	
}