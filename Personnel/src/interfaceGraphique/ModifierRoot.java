package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.FlowLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifierRoot {
	public static void main(String[] args)
	{
		JLabel label = new JLabel("Modifier le root", JLabel.CENTER); 
		label.setFont(new Font("Rockwell Nova", Font.BOLD, 40));
		label.setVerticalAlignment(JLabel.TOP); 
		label.setForeground(Color.RED);
		
		
		JFrame frame = new JFrame(); //Créer une instance de JFrame  
		frame.setTitle("Menu root");
		frame.setSize(800, 500);
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.add(label); //Ajouter label à frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton button = new JButton("Retour");//créer une instance de JButton  
		button.setBounds(235, 390, 320, 50);//x axes, y axes, largeur, hauteur  
		button.setBackground(Color.BLACK); //Définir la couleur de fond  
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell Nova", Font.ITALIC, 15));
		frame.add(button);//Ajouter bouton dans JFrame  
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW); 
		JTextField field = new JTextField(" Nom : ", 20);
		field.setBounds(240, 60, 300, 40);
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
		JTextField field2 = new JTextField(" Prénom : ", 20);
		field2.setBounds(240, 115, 300, 40);
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
		
	    JPanel panel3 = new JPanel();
	    panel3.setBackground(Color.YELLOW); 
		JTextField field3 = new JTextField(" Mail : ", 100);
		field3.setBounds(240, 170, 300, 40);
		field3.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(field3);
		field3.setVisible(true);
	    
	
		field3.addActionListener(new ActionListener()
	    {
	           public void actionPerformed(ActionEvent e)
	           {
	                 String input = field3.getText();
	                 label.setText(input); 
	           }
	    });
		
		panel3.add(label); 
	    frame.add(panel3);
	    
	    JPanel panel4 = new JPanel();
	    panel4.setBackground(Color.YELLOW); 
		JTextField field4 = new JTextField(" Mot de passe : ", 100);
		field4.setBounds(240, 225, 300, 40);
		field4.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(field4);
		field4.setVisible(true);
	    
	
		field4.addActionListener(new ActionListener()
	    {
	           public void actionPerformed(ActionEvent e)
	           {
	                 String input = field4.getText();
	                 label.setText(input); 
	           }
	    });
		
		panel4.add(label); 
	    frame.add(panel4);
	    
	    JPanel panel5 = new JPanel();
	    panel5.setBackground(Color.YELLOW); 
		JTextField field5 = new JTextField(" Date d'arrivée : jj/mm/aaaa ", 10);
		field5.setBounds(240, 280, 300, 40);
		field5.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(field5);
		field5.setVisible(true);
	    
	
		field5.addActionListener(new ActionListener()
	    {
	           public void actionPerformed(ActionEvent e)
	           {
	                 String input = field5.getText();
	                 label.setText(input); 
	           }
	    });
		
		panel5.add(label); 
	    frame.add(panel5);
	    
	    JPanel panel6 = new JPanel();
	    panel6.setBackground(Color.YELLOW); 
		JTextField field6 = new JTextField(" Date ddépart : jj/mm/aaaa ", 10);
		field6.setBounds(240, 335, 300, 40);
		field6.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(field6);
		field5.setVisible(true);
	    
	
		field6.addActionListener(new ActionListener()
	    {
	           public void actionPerformed(ActionEvent e)
	           {
	                 String input = field6.getText();
	                 label.setText(input); 
	           }
	    });
		
		panel6.add(label);
	    frame.add(panel6);
	    frame.setVisible(true);
	}
}