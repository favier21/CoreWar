package jeuCoreWar.graphique;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

import jeuCoreWar.mars.Ordonnanceur;

import java.awt.*;

public class InterfaceGraphique extends JPanel{

	private int taille;
	private int[] tab;
	private Menu m ;
	private int compter; // c'est pour faire des tests


	//Constructeur de la Class
	public InterfaceGraphique(Menu m, int taille){
		this.taille = taille;
		this.compter = this.compter;// c'est pour faire des tests
		this.tab = new int[taille];
		this.setBackground(Color.GRAY);
		this.m = m;

		Arrays.fill(this.tab,1);
		this.addMouseListener(m);
	}

	//Recuperer le tableau
	public int[] getTabMemoire(){
		return this.tab;
	}
	public void setTabMemoire(int i,int valeur){
		this.tab[i] = valeur;
	}
	public int widthPanel(){
		return this.m.getWidth();
		//return this.m.getWidth()-(this.m.getWidth()*2/100);
	}

	public int heightPanel(){
		//return this.m.getHeight()-(this.m.getHeight()*4/100);
		return this.m.getHeight();
	}

	@Override
	public void paint(Graphics g) {
		int sizeSquare;
		int t = (int) Math.floor(Math.sqrt(this.taille));

		if (this.heightPanel()>=this.widthPanel()){
			sizeSquare = this.widthPanel()/t;
		}
		else{
			sizeSquare = this.heightPanel()/t;
		}

		super.paint(g);
		String color; // pour les test
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < t; j++) {
				if (this.getTabMemoire()[t*i+j] == 1) {
					g.setColor(Color.BLACK);
					color ="BLACK";
				}
				else if (this.getTabMemoire()[t*i+j] == 2) {
					g.setColor(Color.BLUE);
					color ="BLUE";
				}
				else if (this.getTabMemoire()[t*i+j] == 3) {
					g.setColor(Color.RED);
					color ="RED";
				}
				else if (this.getTabMemoire()[t*i+j] == 4) { // pour le pointeur si jamais on souhaite l'afficher dans le futur
					g.setColor(Color.YELLOW);
					color ="YELLOW";
				}
				else{ // couleur pour les erreurs
					g.setColor(Color.MAGENTA);
					color ="MAGENTA";
				}
				g.fillRect(j*sizeSquare,i*sizeSquare,sizeSquare,sizeSquare);
			}
		}
}



	public void compteur(){//pour faire des tests
		this.compter += 1;
	}
	public int getCompteur(){//pour faire des tests
		return this.compter;
	}

	public void randomTab() {
		this.compteur();
		Random rand = new Random();
		if (this.getCompteur()<50){
			for (int i = 0 ; i < this.taille ; i++) {
				this.tab[i] = rand.nextInt(2);
			}
			this.repaint();
		}
		else{
			Arrays.fill(this.tab,2);
			this.repaint();
		}

	}

	//met a jour le tableau en fonction de l'instruction actuel
	//il est possible qu'il y ai un problem au niveau de ce qu'est la valeur actuel
	//que l'on va récuperer
	public void tableauUpdate(){
		Ordonnanceur ordo = this.m.getOrdo();
		if (!ordo.getListeInstructionA().isEmpty()) {
			int threadA=ordo.getListeInstructionA().getFirst();
			this.setTabMemoire(threadA,2);
		}
		if (!ordo.getListeInstructionB().isEmpty()) {
			int threadB=ordo.getListeInstructionB().getFirst();
			this.setTabMemoire(threadB,3);
		}
		this.repaint();
		
	}


}
