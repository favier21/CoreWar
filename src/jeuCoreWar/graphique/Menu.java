package jeuCoreWar.graphique;
import javax.swing.*;

import jeuCoreWar.mars.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Menu extends JFrame implements MouseListener{

	private MenuDesign md;

	private InterfaceGraphique graphique;

	private CardLayout card;
	private Container contain; // utiliser pour le cardLayout
	private JPanel panelParty;
	private Ordonnanceur ordo;
	private Arbitre arbitre;

	public Menu(Ordonnanceur ordo){
		this.ordo = ordo;
		this.defFrame();
	}

	public Ordonnanceur getOrdo(){
		return this.ordo;
	}

	public int getTaille(){
		return this.md.getTaille();
	}

	//personaliser la frame
	public void defFrame(){
		this.setTitle("CoreWar");
		this.setPreferredSize(new Dimension(800, 837));
		this.setMinimumSize(new Dimension(800, 837));
		this.setMaximumSize(new Dimension(800, 837));
		this.contain = this.getContentPane();
		this.card=new CardLayout();
		this.setLayout(this.card);

		this.md = new MenuDesign(this);
		this.add(this.md);
		this.md.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.GRAY);
		this.pack();
		this.setVisible(true);

	}


	public boolean fin(){ // renvoie true si c'est finis
		return this.getOrdo().getFin();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			if (e.getSource() instanceof JButton ){
				JButton button = (JButton) e.getSource();
				if (button.getText().equals(MenuDesign.QUITTER)){
					this.dispose(); //c'est dispose() pour quitter la fenetre
				}
				else if(button.getText().equals(MenuDesign.PLAY)){
					this.card.next(this.contain);
					this.md.setVisible(false);
					this.graphique = new InterfaceGraphique(this,this.md.getTaille());
					this.add(this.graphique);
					this.graphique.setVisible(true);
					this.ordo.setDebut(true);
					//int[] tab1 = new int[this.md.getTaille()];
					//int[] tab2 = new int[this.md.getTaille()];
					//Arrays.fill(tab1,2);
					//Arrays.fill(tab2,3);

					//peut-etre faire une system qui compte le nombre d'instruction avec genre une intruction = 1seconde
					//ou faire un MVC mais pas sur que ça fonctionne mais peut-etre mieux si c'est le cas mais perte de temps sinon

					Timer timer = new Timer(10,null);
					timer.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//if(Arrays.equals(Menu.this.graphique.getTabMemoire(), tab1)==true|| Arrays.equals(Menu.this.graphique.getTabMemoire(), tab2)==true){
							if(Menu.this.fin()==true){
								
								timer.stop();
								Menu.this.graphique.setVisible(false);
								if (Menu.this.ordo.getWinner() == 1) {
									Menu.this.arbitre = new Arbitre(Menu.this.ordo.getJoueur1());
								}
								if (Menu.this.ordo.getWinner() == 2) {
									Menu.this.arbitre = new Arbitre(Menu.this.ordo.getJoueur2());
								}
								if (Menu.this.ordo.getWinner() == -1) {
									Menu.this.arbitre = new Arbitre("égalité");
								}
								

								Menu.this.add(Menu.this.arbitre);
								Menu.this.arbitre.setVisible(true);

							}
							else{
								Menu.this.graphique.tableauUpdate();
								Menu.this.graphique.repaint();
							}

						}
					});
					timer.start();

				}
			}
			//pour tester si ça update bien le tableau
			else if (e.getSource() instanceof InterfaceGraphique) {
				InterfaceGraphique graphique = (InterfaceGraphique) e.getSource();
				if (graphique.isVisible() && !this.fin()) {
					this.graphique.tableauUpdate();

				}
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
