package interfaceGraphique;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.SortedSet;

import javax.swing.BorderFactory;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;
import javax.swing.UIManager; 



/**
 * @author 33782
 *
 */
public class Accueil {
	
	private Ligue ligue;
	private GestionPersonnel gestionPersonnel;
	Color selectCouleur = Color.YELLOW;
    public JTextField inputName;
    public JDialog add;
    public JFrame accueil = new JFrame();
    private  Employe connectedEmploye;
    
    public Accueil(GestionPersonnel gestionPersonnel, Employe connectedEmploye){
	    this.gestionPersonnel = gestionPersonnel;
	    this.connectedEmploye = connectedEmploye;
    }

    public void Faccueil(){
		frame().setVisible(true);
    }
    
    public Employe getEmploye(Employe employe){
		return employe;
	}
    
    public JFrame frame(){
		JFrame home = new JFrame();
		home.getContentPane().setBackground(Color.decode("#f2ef13"));
		home.setSize(700,700);
		home.setLocationRelativeTo(null);
		home.setTitle("Accueil");
		home.setJMenuBar(menuBar());
		home.setLayout(new GridBagLayout());
		home.add(panelContainer());
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 return home;
	}

    private JPanel panelContainer(){
		 JPanel panelContainer = new JPanel();
		 panelContainer.setBackground(Color.decode("#f2ef13"));
		 panelContainer.setMinimumSize(new Dimension(500,350));
		 BorderLayout layout = new BorderLayout();
		 layout.setVgap(30);
		 panelContainer.setLayout(layout);
		 Box boxaddLigueBtn = Box.createHorizontalBox();
	     boxaddLigueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		 boxaddLigueBtn.add(addLigueBtn());
		 	 
		 panelContainer.add(title(), BorderLayout.NORTH);
		 if(connectedEmploye.estRoot()) {
			 panelContainer.add(boxaddLigueBtn, BorderLayout.SOUTH);
		 }
		 panelContainer.add(scrollListPanel(), BorderLayout.CENTER);
		 return panelContainer;
	 }
    
    private JLabel title(){
		 JLabel title = new JLabel("LISTE DES LIGUES");
		 title.setHorizontalAlignment(SwingConstants.CENTER);
		 title.setFont(new Font("Rockwell Nova", Font.BOLD, 25));
		 return title;
	 }
    
    private JButton addLigueBtn(){
		 JButton addLigueBtn = new JButton("Ajouter une ligue");
		 addLigueBtn.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		 addLigueBtn.setBackground(Color.decode("#222222"));
		 addLigueBtn.setForeground(Color.decode("#fafafa"));
		 addLigueBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Entrez le nom de la ligue"); 
				try {
					gestionPersonnel.addLigue(inputValue);
				} catch (SauvegardeImpossible e1) {
					e1.printStackTrace();
				}
				frame().setVisible(false);
				frame().setVisible(true);
			}
		});
		 return addLigueBtn;
	 }
    
    public  JTextField ligueNameInput(){
		 inputName = new JTextField();
		 return inputName;
	 }
	 
	private JMenuBar menuBar(){
		 JMenuBar menubar = new JMenuBar();
		 menubar.setPreferredSize(new Dimension(60,60));
		 JMenu menu = new JMenu("COMPTE ROOT");
		 menu.add(menuItem());
		 menu.setFont(new Font("Rockwell Nova", Font.BOLD, 20));
		 menu.setForeground(Color.decode("#FF0000"));
		 menubar.setBackground(Color.decode("#f2ef13"));
		 menubar.add(menu);
		return menubar;
	 }
	
	 private JMenuItem menuItem(){
		 JMenuItem itemMenu = new JMenuItem("G�rer le compte root");
		 itemMenu.setFont(new Font("Rockwell Nova", Font.PLAIN, 20));
		 itemMenu.setBackground(Color.decode("#222222"));
		 itemMenu.setForeground(Color.decode("#ffffff"));
		 itemMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(connectedEmploye.equals(gestionPersonnel.getRoot())) {
					GererRoot root = new GererRoot(gestionPersonnel, connectedEmploye);
					root.AccountData();
				}else {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas l'acc�s � cette page", "droits insuffisants", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		 return itemMenu;
	 }

	 public JList<Ligue>  listLigues()
	 {
		 SortedSet<Ligue> choix = gestionPersonnel.getLigues();
		 JList<Ligue> listLigues = new JList<>();
		 listLigues.setOpaque(true);
		 DefaultListModel<Ligue> listLigue = new DefaultListModel<>();
		 listLigues.setFont(new Font("Rockwell Nova", Font.BOLD, 22));
		 listLigues.setModel(listLigue);
		 for (Ligue ligue : choix) {
			   listLigue.addElement(ligue);
			}
		 listLigues.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()){
		            JList source = (JList)e.getSource();
		            Ligue selected = (Ligue) source.getSelectedValue();
		            GererEmploye ligue = new  GererEmploye(gestionPersonnel,selected, connectedEmploye);
		            ligue.GererEmploye();
		        }
				
			}
		});
		 listLigues.setModel(listLigue);
		 listLigues.setBackground(Color.decode("#f2ef13"));
		 listLigues.setForeground(Color.decode("#222222"));
		 DefaultListCellRenderer renderer =  (DefaultListCellRenderer)listLigues.getCellRenderer();  
		 renderer.setHorizontalAlignment(JLabel.CENTER);
		 listLigues.setFixedCellHeight(50);
		 return listLigues;
	 }
	 
	 private JScrollPane scrollList()
	 {
	    JScrollPane scrollpane = new JScrollPane(listLigues());
	    scrollpane.setPreferredSize(new Dimension(450,300));
	    scrollpane.getViewport().setBackground(Color.decode("#f2ef13"));
	    scrollpane.setBorder(BorderFactory.createLineBorder(Color.decode("#f2ef13"), 1));
	    return scrollpane;
	 }
	 
	 private JPanel scrollListPanel()
	 {
		 JPanel panel = new JPanel();
		 panel.add(scrollList());
		 return panel;
	 }
	

	 /**
	 * @return
	 */
	 
	 public  Ligue getLigue()
	 {
		 return ligue;
	 }
}