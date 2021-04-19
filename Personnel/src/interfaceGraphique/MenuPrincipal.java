package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MenuPrincipal {
	public static void main(String[] args)
	{
		/*JLabel label = new JLabel("<html>Gestion du personnel des ligues <br> Séléctionner une option : </html>", JLabel.CENTER);
		JLabel label = new JLabel();
		label.setText("<html><body> Gestion du personnel des ligues <br> Séléctionner une option :</body></html>");*/
		JLabel label = new JLabel("Gestion du personnel des ligues", JLabel.CENTER);
		label.setPreferredSize(new Dimension(50, 75));
		label.setBounds(50, 30, 0, 0);
		label.setFont(new Font("Rockwell Nova", Font.BOLD, 40));
		label.setVerticalAlignment(JLabel.TOP); 
		label.setForeground(Color.RED);
		
		JFrame frame = new JFrame();   
		frame.setTitle("Menu Principal");
		frame.setSize(800, 500);
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		/*JLabel label1 = new JLabel("Sélectionner une option :", JLabel.CENTER); 
		label1.setFont(new Font("Rockwell Nova", Font.BOLD, 35));
		label1.setVerticalAlignment(JLabel.TOP);
		label1.setForeground(Color.BLACK);
		frame.add(label1);*/
		
		
		JButton button = new JButton("Retour"); 
		button.setBounds(10,10,100, 40);
		button.setBackground(Color.BLACK);   
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button);
				

		JButton button2 = new JButton("Gérer le compte root");
		button2.setBounds(220, 160, 320, 60); 
		button2.setBackground(Color.GRAY);
		button2.setForeground(Color.WHITE);
		button2.setFont(new Font("Rockwell Nova", Font.ITALIC, 15));
		frame.add(button2);
		
		
		JButton button3 = new JButton("Gérer les ligues");
		button3.setBounds(220, 240, 320, 60); 
		button3.setBackground(Color.GRAY);
		button3.setForeground(Color.WHITE);
		button3.setFont(new Font("Rockwell Nova", Font.ITALIC, 15));
		frame.add(button3);
		
		JButton button4 = new JButton("Quitter");
		button4.setBounds(220, 320, 320, 60); 
		button4.setBackground(Color.GRAY);
		button4.setForeground(Color.WHITE);
		button4.setFont(new Font("Rockwell Nova", Font.ITALIC, 15));
		frame.add(button4);
		
	}
}

