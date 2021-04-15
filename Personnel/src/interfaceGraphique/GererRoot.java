package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;


public class GererRoot {
	public static void main(String[] args)
	{
		JLabel label = new JLabel("Gérer le Root", JLabel.CENTER); //Créer le texte et le centrer
		label.setFont(new Font("Rockwell Nova", Font.PLAIN, 35));
		label.setVerticalAlignment(JLabel.TOP);
		label.setBackground(Color.RED);
		
		JFrame frame = new JFrame(); //Créer une instance de JFrame  
		frame.setTitle("Gérer le Root");
		frame.setSize(800, 500);
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.add(label); //Ajouter label à frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JButton button = new JButton("Modifier le Root");//créer une instance de JButton  
		button.setBounds(250,280,300, 40);//x axes, y axes, largeur, hauteur  
		button.setBackground(Color.GRAY); //Définir la couleur de fond  
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button);//Ajouter bouton dans JFrame  
        
		JButton button2 = new JButton("Retour");//créer une instance de JButton  
		button2.setBounds(350,350,100, 40);//x axes, y axes, largeur, hauteur  
		button2.setBackground(Color.GRAY); //Définir la couleur de fond  
		button2.setForeground(Color.WHITE);
		button2.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button2);//Ajouter bouton dans JFrame  
		
		// TABLE
		String[][] data = {
		            { "1", "Bob", "Marley", "bob.marley@outlook.com" }
		        };
		  
		        // Column Names
		        String[] columnNames = { "ID", "NOM", "PRENOM", "MAIL" };
		  
		        // Initializing the JTable
		        JTable table = new JTable(data, columnNames);
		        table.setBounds(30, 40, 200, 300);
		        table.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		    
		        // adding it to JScrollPane
		        JScrollPane scroll = new JScrollPane(table);
		        frame.add(scroll);
		        
		        // Modifiying header table 
		        JTableHeader tableHeader = table.getTableHeader();
		        Font headerFont = new Font("Rockwell Nova", Font.PLAIN, 15);
		        tableHeader.setFont(headerFont);
	}
}
