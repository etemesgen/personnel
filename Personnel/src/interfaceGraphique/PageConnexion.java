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

public class PageConnexion {
	public static void main(String[] args)
	{
		JLabel label = new JLabel("Identifiez-vous", JLabel.CENTER); //Créer le texte et le centrer
		label.setFont(new Font("Rockwell Nova", Font.PLAIN, 35));
		label.setVerticalAlignment(JLabel.TOP);
		
		
		JFrame frame = new JFrame(); //Créer une instance de JFrame  
		frame.setTitle("Page de connexion");
		frame.setSize(800, 500);
	//	frame.getContentPane().setBackground(Color.YELLOW);
		frame.add(label); //Ajouter label à frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Retour");//créer une instance de JButton  
		button.setBounds(10,10,100, 40);//x axes, y axes, largeur, hauteur  
		button.setBackground(Color.BLACK); //Définir la couleur de fond  
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button);//Ajouter bouton dans JFrame  
				
		
        
		JButton button2 = new JButton("Se connecter");//créer une instance de JButton  
		button2.setBounds(300,300, 200, 70);//x axes, y axes, largeur, hauteur  
		button2.setBackground(Color.BLACK); //Définir la couleur de fond  
		button2.setForeground(Color.WHITE);
		button2.setFont(new Font("Rockwell Nova", Font.ITALIC, 15));
		frame.add(button2);//Ajouter bouton dans JFrame  
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW); 
		JTextField field = new JTextField(" Identifiant : ", 20);
		field.setBounds(250,100, 300, 50);
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
        
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW); 
		JTextField field2 = new JTextField(" Mot de passe : ", 20);
		field2.setBounds(250,200, 300, 50);
		field2.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(field2);
		field2.setVisible(true);
	    

		field2.addActionListener(new ActionListener()
        {
               public void actionPerformed(ActionEvent e)
               {
                     String input = field2.getText();
                     label.setText(input); 
               }
        });
		
		panel2.add(label); 
        frame.add(panel2);
		
		/** Button Reaction **/
		/* panel.add(button);
		 
         button.addActionListener(new ActionListener()
         {
                 public void actionPerformed(ActionEvent e)
                 {
                        String input = field.getText();
                        label.setText(input);
                 }
         });
         */
  
         frame.setVisible(true);//Rendre le frame visible
	}
}
