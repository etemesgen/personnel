package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Accueil {
	public static void main(String[] args)
	{
		JLabel l = new JLabel("GESTION DU PERSONNEL DES LIGUES", JLabel.CENTER); //créer le texte
		l.setFont(new Font("Rockwell Nova", Font.PLAIN, 38));
		
		JFrame f = new JFrame(); //créer une instance de JFrame  
		f.setTitle("GESTION DU PERSONNEL DES LIGUES");
		f.setSize(800, 500);
		f.getContentPane().setBackground(Color.YELLOW);
		f.add(l); // ajouter label à frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);//rendre le frame visible
        
		JButton b = new JButton("Suivant");//créer une instance de JButton  
		b.setBounds(600,380,100, 40);//x axes, y axes, largeur, hauteur  
		b.setBackground(Color.BLACK);   
		b.setForeground(Color.WHITE);
		//b.setBorder();
		f.add(b);//ajouter bouton dans JFrame  
	}
}
