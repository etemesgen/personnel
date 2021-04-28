package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class PageConnexion<listEmployes> {
	
	    static GestionPersonnel gestionPersonnel;
	    private  static Accueil accueil;
	    GererEmploye listemp;
	    Ligue ligue;
	    Employe employe;
	    private JTextField passwordTxt;
	    private TextField login;
	    Employe connectedEmploye;
	    private JLabel passIncorrect;
	    
	    public PageConnexion(GestionPersonnel gestionPersonnel){
			this.gestionPersonnel = gestionPersonnel;
			this.listemp =  new GererEmploye(gestionPersonnel, ligue, connectedEmploye);
		}
		
	    public void signIn(){
	    	frame().setVisible(true);
	    }
	
	    public JFrame frame() {
		JFrame frame = new JFrame(); //Créer une instance de JFrame  
		frame.setTitle("Page de connexion");
		frame.getContentPane().setBackground(Color.decode("#f2ef13"));
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar());
		frame.add(container());
		return frame;
	}

	    
	    private JPanel container(){
	    	JPanel panel = new JPanel();
	    	panel.setPreferredSize(new Dimension(450,300));
	    	panel.setBackground(Color.decode("#f2ef13"));
	 //   	panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#c9b02e")));
	 //   	panel.setBorder(BorderFactory.createLineBorder(Color.decode("#c9b02e"), 1));
	        panel.add(loginPasswordInput());
	        return panel;
	    }
	    
	    private JPanel  loginPasswordInput(){
	    	JPanel panel = new JPanel();
	    	panel.setBackground(Color.decode("#f2ef13"));
	    	GridLayout layout = new GridLayout(0,2);
	    	layout.setVgap(40);
	    	layout.setHgap(10);
	        panel.setLayout(layout);
	        panel.add(login());
	        panel.add(loginInput());
	        panel.add(password());
	        panel.add(passInput());
	        panel.add(btnConnexion());
	        panel.add(passwordFailed());
	        return panel;
	    }
	    
	    private JLabel login(){
	    	JLabel loginL = new JLabel("Identifiant : ");
	        loginL.setFont(new Font("Rockwell Nova", Font.BOLD, 25));
	        loginL.setForeground(Color.decode("#222222"));
	        return loginL;
	    }
	    
	    
	    private TextField loginInput(){
	    	login = new TextField();
	        login.setPreferredSize(new Dimension(150,40));
	        
	        return login;
	    }
	    
	    private JLabel password(){
	    	JLabel passwordL = new JLabel("Mot de passe : ");
	        passwordL.setFont(new Font("Rockwell Nova", Font.BOLD, 25));
	        passwordL.setForeground(Color.decode("#222222"));
	        return passwordL;
	    }
	    
	    private JTextField passInput(){
	    	passwordTxt = new JTextField();
	        return passwordTxt;
	    }
	    
	    
	    private  JButton btnConnexion(){
	    	 JButton btnconnexion = new JButton("Se connecter");
	         btnconnexion.setPreferredSize(new Dimension(200,50));
	         btnconnexion.setBounds(600,380,100, 40);
	         btnconnexion.setBackground(Color.decode("#222222"));
	         btnconnexion.setForeground(Color.decode("#fafafa"));
	         btnconnexion.setFont(new Font("Rockwell Nova", Font.PLAIN, 20));
	         btnconnexion.addActionListener(new ActionListener()
	         {


	    		/**
	    		 *
	    		 */
	    		@Override
	    		public void actionPerformed(ActionEvent arg0) {
	    			
	    			if(passwordTxt.getText().equals(gestionPersonnel.getRoot().getPassword())){
	    				connectedEmploye = gestionPersonnel.getRoot();
	    				   Accueil accueil = new Accueil(gestionPersonnel, connectedEmploye);
	    				   accueil.frame().setVisible(true);
	    				   accueil.getEmploye(gestionPersonnel.getRoot());
	    			}
	    			else {
	    				for(Ligue ligue : gestionPersonnel.getLigues()) {
	       		    	 for(Employe employe : ligue.getEmployes()) {
	       		    	    if(passwordTxt.getText().equals(employe.getPassword()) && login.getText().equals(employe.getMail())) { 
	       		    			connectedEmploye = employe;
	       		    			Accueil accueil = new Accueil(gestionPersonnel, connectedEmploye);
	       		    			accueil.getEmploye(connectedEmploye);
	         				    accueil.frame().setVisible(true);
	       		    		 }else if(!passwordTxt.getText().equals(employe.getPassword()) || !login.getText().equals(employe.getMail())) {
	       		    			passIncorrect.setText("Login ou mot de passe incorrect!");
	       		    		 }
	       		    	 }
	       		     }
	    			}
	    		}
	         });
	         
	         return btnconnexion;
	    }
	    
	    private JLabel passwordFailed(){
	    	passIncorrect = new JLabel();
	    	return passIncorrect;
	    }
	    
	    private JMenuBar menuBar(){
			 JMenuBar menubar = new JMenuBar();
			 menubar.setPreferredSize(new Dimension(50,50));
			 menubar.setBackground(Color.decode("#f2ef13"));
			 JMenu menu = new JMenu("CONNEXION");
			 menu.setHorizontalAlignment(JMenu.CENTER);
			 menu.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
			 menu.setForeground(Color.decode("#FF0000"));
			 menu.setSize(80,80);
			 menubar.add(menu);
			return menubar;
		 }
	    
	    public void Accueil(){
	    	 JFrame pageAccueil = new JFrame();
	    	 pageAccueil.setVisible(true);
	    	 pageAccueil.setTitle("Home page");
	    	 pageAccueil.getContentPane().setLayout(new FlowLayout());
	    	 pageAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	 pageAccueil.pack();
	    }
	    
	public static void main(String[] args){
			PageConnexion PageConnexion = new PageConnexion(GestionPersonnel.getGestionPersonnel());
    	interfaceGraphique.PageConnexion.gestionPersonnel.getRoot();
    	PageConnexion.signIn();      	 
	}
}
