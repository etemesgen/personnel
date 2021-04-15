package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AjouterLigue {
	public static void main(String[] args)
	{
	JLabel label = new JLabel("Ajouter une ligue", JLabel.CENTER); 
	label.setFont(new Font("Rockwell Nova", Font.BOLD, 40));
	label.setVerticalAlignment(JLabel.TOP); 
	label.setForeground(Color.RED);
	
	
	JFrame frame = new JFrame(); //Créer une instance de JFrame  
	frame.setTitle("Nouvelle ligue");
	frame.setSize(800, 500);
	frame.getContentPane().setBackground(Color.YELLOW);
	frame.add(label); //Ajouter label à frame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JButton button = new JButton("Retour");//créer une instance de JButton  
	button.setBounds(280, 250, 200, 70);//x axes, y axes, largeur, hauteur  
	button.setBackground(Color.BLACK); //Définir la couleur de fond  
	button.setForeground(Color.WHITE);
	button.setFont(new Font("Rockwell Nova", Font.ITALIC, 15));
	frame.add(button);//Ajouter bouton dans JFrame  
	
	JPanel panel = new JPanel();
	panel.setBackground(Color.YELLOW); 
	JTextField field = new JTextField(" Nom : ", 20);
	field.setBounds(240, 150, 300, 60);
	field.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
	frame.add(field);
	field.setVisible(true);
	
	field.addActionListener(new ActionListener()
    {
           public void actionPerformed(ActionEvent e)
           {
                 String input = field.getText();
                 label.setText(input); 
           }
    });
	
	panel.add(label); 
    frame.add(panel);
    
    frame.setVisible(true);
    
 }
}
