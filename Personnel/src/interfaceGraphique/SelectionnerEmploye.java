package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class SelectionnerEmploye {
	public static void main(String[] args)
	{	
		JFrame frame = new JFrame(); //Cr�er une instance de JFrame  
		frame.setTitle("S�lectionner une ligue");
		frame.setSize(800, 500);
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("S�lection ligue", JLabel.CENTER); //Cr�er le texte et le centrer
		label.setFont(new Font("Rockwell Nova", Font.PLAIN, 35));
		label.setVerticalAlignment(JLabel.TOP);
		label.setForeground(Color.RED);
		frame.add(label);
	
		JButton button = new JButton("Nommer Admin");//cr�er une instance de JButton  
		button.setBounds(50,280,300, 40);//x axes, y axes, largeur, hauteur  
		button.setBackground(Color.GRAY); //D�finir la couleur de fond  
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button);//Ajouter bouton dans JFrame  
        
		JButton button2 = new JButton("Modifier");//cr�er une instance de JButton  
		button2.setBounds(125,350,150, 40);//x axes, y axes, largeur, hauteur  
		button2.setBackground(Color.GRAY); //D�finir la couleur de fond  
		button2.setForeground(Color.WHITE);
		button2.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button2);//Ajouter bouton dans JFrame  
		
		JButton button3 = new JButton("Supprimer");//cr�er une instance de JButton  
		button3.setBounds(440,280,300, 40);//x axes, y axes, largeur, hauteur  
		button3.setBackground(Color.GRAY); //D�finir la couleur de fond  
		button3.setForeground(Color.WHITE);
		button3.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button3);//Ajouter bouton dans JFrame  
        
		JButton button4 = new JButton("Retour");//cr�er une instance de JButton  
		button4.setBounds(515,350,150, 40);//x axes, y axes, largeur, hauteur  
		button4.setBackground(Color.GRAY); //D�finir la couleur de fond  
		button4.setForeground(Color.WHITE);
		button4.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		frame.add(button4);//Ajouter bouton dans JFrame  
		
		// TABLE
		String[][] data = {
		            { "1", "Bob", "Marley", "bob.marley@outlook.com", "bhdg", "01/02/2021", "01/03/2021"}
		        };
		        // Column Names
		        String[] columnNames = { "ID", "NOM", "PRENOM", "MAIL", "MOT DE PASSE", "DATE ARRIV.", "DATE DEPA."};
		 
		        // Initializing the JTable
		        JTable table = new JTable(data, columnNames);
		        table.setBounds(30, 240, 0, 0);
		        table.setFont(new Font("Rockwell Nova", Font.PLAIN, 15));
		    
		        // adding it to JScrollPane
		        JScrollPane scroll = new JScrollPane(table);
		        frame.add(scroll);
		        
		        // Modifiying header table 
		        JTableHeader tableHeader = table.getTableHeader();
		        Font headerFont = new Font("Rockwell Nova", Font.PLAIN, 15);
		        tableHeader.setFont(headerFont);
		        
		    
		        frame.setVisible(true);
	}
}
