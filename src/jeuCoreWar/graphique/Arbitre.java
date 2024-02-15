package jeuCoreWar.graphique;

import java.awt.*;
import javax.swing.*;

public class Arbitre extends JPanel { 


	
	private String joueurGagnant;
	

	public Arbitre(String joueurGagnant) {
		this.affichageScore(joueurGagnant);
	}
	
	public void affichageScore(String leGagnant) {
		this.setLayout(new GridLayout(10,1,1,5));
		this.setBackground(Color.gray);
		
		// ----------- Nos éléments d'affichage ----------- //
		JLabel label1 = new JLabel("Résultats des scores");
		label1.setFont(new Font("Arial", Font.BOLD, 50));
		
		JPanel panel2   = new JPanel();
		panel2.setBackground(Color.gray);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS)); // Evite espace vide panel enfant
		JLabel label2_1 = new JLabel("Le gagnant de cette partie est : ");
		
		
		
		JLabel label2_2 = new JLabel(leGagnant);
		label2_2.setFont(new Font("Arial", Font.ITALIC, 30));
		

		
		this.ajoutEspaceVerticalAuPanel(this, 5);
		this.add(label1);
		this.ajoutEspaceVerticalAuPanel(this, 5);
		
		panel2.add(label2_1);
		panel2.add(label2_2);
		this.add(panel2);
		
		this.ajoutEspaceVerticalAuPanel(this, 3);

		this.ajoutEspaceVerticalAuPanel(this, 2);

		
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS); // Pour pouvoir centrer
		this.setLayout(layout);
		
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.setAlignmentX(Component.CENTER_ALIGNMENT); 


	}
	
	public void ajoutEspaceVerticalAuPanel(JPanel panel, int n) {
		// Problème, si on a un new JLabel(" ") en param ça l'affiche qu'une seule fois
		// Solution pas de param JLabel, mais directement dans la fonction
		for (int i=0; i<n; i++) {

			panel.add(new JLabel(" "));
		}
	}

}
