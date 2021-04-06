package interfaceGraphique;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Accueil {
	public static void main(String[] args)
	{
		JFrame f = new JFrame(); //cr�er une instance de JFrame  
		f.setTitle("GESTION DU PERSONNEL DES LIGUES");
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		JButton b = new JButton("Suivant");//cr�er une instance de JButton  
		b.setBounds(600,380,100, 40);//x axes, y axes, largeur, hauteur  
		          
		f.add(b);//ajouter bouton dans JFrame  
		          
		f.setSize(800,500);//400 largeur et 500 hauteur  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//rendre le frame visible  
		
		JLabel l = new JLabel(); //cr�er le texte
        l.setText("GESTION DU PERSONNEL DES LIGUES"); // ajouter du texte � label
     
        JPanel p = new JPanel(); // cr�er un panel
        
        p.add(l); // ajouter label � panel
        f.add(p); // ajouter panel � frame
	}
}
