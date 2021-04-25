package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;

import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;




public class GererEmploye {
	
	private  GestionPersonnel gestionPersonnel;
	private  Ligue ligue;
	private  Accueil accueil;
	private Employe connectedEmploye;
	
	 public GererEmploye(GestionPersonnel gestionPersonnel, Ligue ligue, Employe connectedEmploye) {
		    this.gestionPersonnel = gestionPersonnel;
		    this.ligue = ligue;
		    this.connectedEmploye = connectedEmploye;
	}
	 
	public void listEmployes()
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
		employes.add(container());
		employes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return employes;
	}
	private JPanel container()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#ffffff"));
		panel.setMinimumSize(new Dimension(500,500));
		BorderLayout layout = new BorderLayout();
		layout.setVgap(25);
		panel.setLayout(layout);
		panel.add(backAndTitleComponent(), BorderLayout.NORTH);
		panel.add(employesList(), BorderLayout.CENTER);
		panel.add(renameAndDelete(), BorderLayout.SOUTH);
		return panel;
	}
	
	
	private JPanel backAndTitleComponent()
	{
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(2,1);
		layout.setVgap(15);
		panel.setLayout(layout);
		panel.setBackground(Color.decode("#f2ef13"));
		Box back = Box.createHorizontalBox();
		back.add(back());
		panel.add(back);
		panel.add(titleLigue());
		return panel;
	}
	
	private JPanel employesList()
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200,500));
		panel.setBackground(Color.decode("#ffffff"));
		if(ligue.getEmployes().size() > 0)
		{
			panel.add(list());
		}else {
			panel.add(notEmployesFunded());
		}
		return panel;
	}
	
	private JLabel notEmployesFunded()
	{
		JLabel label = new JLabel("Il existe aucun employ� dans cette ligue");
		label.setFont(new Font("Rockwell Nova", Font.BOLD, 22));
		label.setForeground(Color.decode("#222222"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label;
	}

	public JMenuBar menuBar()
	{
		 JMenuBar menubar = new JMenuBar();
		 menubar.setPreferredSize(new Dimension(60,60));
		 menubar.add(menuLigues());
		 menubar.setBackground(Color.decode("#f2ef13"));
		return menubar;
	}
	
	private JMenu menuLigues()
	{
		JMenu ligues = new JMenu("S�lection Ligue");
		 ligues.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		 ligues.setForeground(Color.decode("#FF0000"));
		 ligues.addSeparator();
         ligues.add(menuItem());
		 return ligues;
	}
	
	 private JMenuItem menuItem()
	 {
		 JMenuItem itemMenu = new JMenuItem("G�rer mon compte");
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
	
	public JList<Employe> list()
	{
		SortedSet<Employe> emp = ligue.getEmployes();
		JList<Employe> empL = new JList<>();
		DefaultListModel<Employe> listEmp = new DefaultListModel<>();
		empL.setModel(listEmp);
		
		for(Employe employe : emp)
		{
			listEmp.addElement(employe);
		}
		empL.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()){
		            JList source = (JList)e.getSource();
		            Employe selectedEmploye = (Employe) source.getSelectedValue();
		            frame().setVisible(false);
		            SelectionnerEmploye employe = new SelectionnerEmploye(gestionPersonnel, selectedEmploye, ligue, connectedEmploye);
		            employe.employeShow();
		        }
			}
		});
		 empL.setFont(new Font("Rockwell Nova", Font.BOLD, 22));
		 empL.setBackground(Color.decode("#ffffff"));
		 empL.setForeground(Color.decode("#222222"));
		 DefaultListCellRenderer renderer =  (DefaultListCellRenderer)empL.getCellRenderer();  
		 renderer.setHorizontalAlignment(JLabel.CENTER);
		 empL.setFixedCellWidth(500);
		 empL.setFixedCellHeight(40);
		return empL;
	}
	
	private JLabel titleLigue()
	{
		JLabel title = new JLabel();
		title.setText(ligue.getNom() + " administr�e par  " + ligue.getAdministrateur().getNom());
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Rockwell Nova", Font.BOLD, 27));
		title.setForeground(Color.decode("#222222"));
		return  title;
	}
	
	private JPanel renameAndDelete()
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(650,150));
		panel.setBackground(Color.decode("#f2ef13"));
		FlowLayout layout = new FlowLayout();
		panel.setLayout(layout);
		
		Box delete = Box.createHorizontalBox();
		delete.add(deleteLigue());
		
		
		Box rename = Box.createHorizontalBox();
		rename.add(renameLigue());
		
		
		Box addEmploye = Box.createHorizontalBox();
		addEmploye.add(addEmploye());
		
		panel.add(deleteLigue());
		panel.add(renameLigue());
		panel.add(addEmploye);
		return panel;
	}
	
	private JButton renameLigue()
	{
	    JButton renameLigue = new JButton("Renommer la ligue");
	    if(!gestionPersonnel.haveWritePermission(ligue, connectedEmploye)) {
	    	renameLigue.setEnabled(false);
	 }
	    renameLigue.setFont(new Font("Rockwell Nova", Font.BOLD, 18));
	    renameLigue.setForeground(Color.decode("#ffffff"));
	    renameLigue.setBackground(Color.decode("#222222"));
	    renameLigue.setPreferredSize(new Dimension(250,35));
	    renameLigue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Nom de la ligue"); 
				ligue.setNom(inputValue);
				Accueil pageLigues = new Accueil(gestionPersonnel, connectedEmploye);
				frame().setVisible(false);
				frame().dispose();
				pageLigues.frame().setVisible(true);
			}
		});
	    return renameLigue;
	}
	
	
	private JButton deleteLigue()
	{
		JButton deleteLigue = new JButton("Supprimer la ligue");
		 if(!gestionPersonnel.haveWritePermission(ligue, connectedEmploye)) {
			      deleteLigue.setEnabled(false);
		 }
		deleteLigue.setFont(new Font("Rockwell Nova", Font.BOLD, 18));
		deleteLigue.setForeground(Color.decode("#ffffff"));
		deleteLigue.setBackground(Color.decode("#222222"));
		deleteLigue.setPreferredSize(new Dimension(250,35));
		deleteLigue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ligue.removeAdmin();
				JOptionPane.showMessageDialog(null, "la ligue a �t� supprim�e", "supprimer la ligue", JOptionPane.INFORMATION_MESSAGE);
				Accueil pageLigues = new Accueil(gestionPersonnel, connectedEmploye);
				frame().setVisible(false);
				frame().dispose();
				pageLigues.frame().setVisible(true);
			}
		});
		return deleteLigue;
	}
	
	private JButton back()
	{
		JButton btn = new JButton("Retour");
		btn.setBackground(Color.decode("#222222"));
		btn.setForeground(Color.decode("#ffffff"));
		btn.setPreferredSize(new Dimension(130,30));
		btn.setFont(new Font("Rockwell Nova", Font.PLAIN, 22));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Accueil pageLigues = new Accueil(gestionPersonnel, connectedEmploye);
				frame().setVisible(false);
				pageLigues.frame().setVisible(true);
			}
		});
		return btn;
	}
	
	private JButton addEmploye()
	{
		JButton addEmploye = new JButton("Ajouter un employ�");
		if(!gestionPersonnel.haveWritePermission(ligue, connectedEmploye)) {
			addEmploye.setEnabled(false);
	 }
		addEmploye.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		addEmploye.setBackground(Color.decode("#222222"));
		addEmploye.setForeground(Color.decode("#ffffff"));
		addEmploye.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AjouterEmploye add = new AjouterEmploye(gestionPersonnel, ligue, connectedEmploye);
				frame().setVisible(false);
				add.AddEmploye();
			}
		});
		return addEmploye;
	}

}