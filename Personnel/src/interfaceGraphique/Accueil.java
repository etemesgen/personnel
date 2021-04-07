package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Accueil {
	public static void main(String[] args)
	{
		JLabel l = new JLabel("GESTION DU PERSONNEL DES LIGUES", JLabel.CENTER); //Créer le texte et le centrer
		l.setFont(new Font("Rockwell Nova", Font.PLAIN, 38));
		
		JFrame f = new JFrame(); //Créer une instance de JFrame  
		f.setTitle("Accueil");
		f.setSize(800, 500);
		f.getContentPane().setBackground(Color.YELLOW);
		f.add(l); //Ajouter label à frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);//Rendre le frame visible
        
		JButton b = new JButton("Suivant");//créer une instance de JButton  
		b.setBounds(600,380,100, 40);//x axes, y axes, largeur, hauteur  
		b.setBackground(Color.BLACK); //Définir la couleur de fond  
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		f.add(b);//Ajouter bouton dans JFrame  
	}
}
