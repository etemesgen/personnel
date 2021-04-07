package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PageConnexion {
	public static void main(String[] args)
	{
		JLabel l = new JLabel("Identifiez-vous", JLabel.CENTER); //Créer le texte et le centrer
		l.setFont(new Font("Rockwell Nova", Font.PLAIN, 35));
	/*	Border border = l.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		l.setBorder(new CompoundBorder(border, margin));*/
		
		JFrame f = new JFrame(); //Créer une instance de JFrame  
		f.setTitle("Page de connexion");
		f.setSize(800, 500);
		f.getContentPane().setBackground(Color.YELLOW);
		f.add(l); //Ajouter label à frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);//Rendre le frame visible
        
		JButton b = new JButton("Se connecter");//créer une instance de JButton  
		b.setBounds(300,300, 200, 70);//x axes, y axes, largeur, hauteur  
		b.setBackground(Color.BLACK); //Définir la couleur de fond  
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		f.add(b);//Ajouter bouton dans JFrame  
	}
}
