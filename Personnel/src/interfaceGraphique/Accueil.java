package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Accueil {
	public static void main(String[] args)
	{
		JLabel label = new JLabel("GESTION DU PERSONNEL DES LIGUES", JLabel.CENTER); //Créer le texte et le centrer
		label.setFont(new Font("Rockwell Nova", Font.PLAIN, 38));
		
		JFrame frame = new JFrame(); //Créer une instance de JFrame  
		frame.setTitle("Accueil");
		frame.setSize(800, 500);
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.add(label); //Ajouter label à frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);//Rendre le frame visible
        
		JButton button = new JButton("Suivant");//créer une instance de JButton  
		button.setBounds(600,380,100, 40);//x axes, y axes, largeur, hauteur  
		button.setBackground(Color.BLACK); //Définir la couleur de fond  
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button);//Ajouter bouton dans JFrame  
	}
}
