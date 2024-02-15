package jeuCoreWar.graphique;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuDesign extends JPanel implements ItemListener{

	public static final String QUITTER = "quitter";
	public static final String PLAY = "play";

	private int taille=100;
    private JButton play,quitter;
	private JComboBox<Integer> combobox;
	private Label label,label2;

	private JPanel panel;
    private Menu m ;

    public MenuDesign(Menu m) {
		this.m = m;
		this.panel = new JPanel();

        this.defPanel();
    }

	public int getTaille(){
		return this.taille;
	}

	public void setTaille(int t){
		this.taille = t;
	}
/*
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}
*/
	public void defPanel(){
		this.setButtons();
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints contrain = new GridBagConstraints();
		contrain.insets =new Insets(8,8,8,8);
		contrain.gridx = 1;
		contrain.gridy = 0;
		contrain.ipadx = 10;
		contrain.ipady = 200;
		this.panel.add(this.defCompoBox(),contrain);
		contrain.gridx = 1;
		contrain.gridy = 6;
		contrain.ipadx = 185;
		contrain.ipady = 10;
		this.panel.add(this.play,contrain);
		contrain.gridx = 1;
		contrain.gridy = 8;
		contrain.ipadx = 155;
		contrain.ipady = 10;
		this.panel.add(this.quitter,contrain);
		this.panel.setBackground(Color.GRAY);
		this.add(this.panel,BorderLayout.NORTH);
		this.setBackground(Color.GRAY);
	}

	//Définir les bouttons
	private void setButtons(){ 
		Font f = new Font(Font.SANS_SERIF ,  Font.BOLD, 20);

		this.play = new JButton(PLAY);
		this.play.addMouseListener(this.m);
		this.play.setFont(f);
		this.quitter = new JButton(QUITTER);
		this.quitter.addMouseListener(this.m);
		this.quitter.setFont(f);
	}

    public JPanel defCompoBox(){
		Font f = new Font(Font.SANS_SERIF ,  Font.BOLD, 20);
		this.label=new Label();
		this.label2=new Label();
		this.label2.setText("100");
		this.label2.setFont(f);
		this.label.setText("Choisi la taille de la mémoire:");
		this.label.setFont(f);
		Integer i[] = { 100,400};
		JPanel panelBox = new JPanel();
		this.combobox = new JComboBox<>(i);
		this.combobox.addItemListener(this);
		panelBox.add(this.label);
		panelBox.add(this.combobox);
		panelBox.add(this.label2);
		panelBox.setBackground(Color.GRAY);
		return panelBox;
	}


	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == this.combobox) {
			this.setTaille((Integer)this.combobox.getSelectedItem());
			this.label2.setText((Integer)this.combobox.getSelectedItem()+"");
			System.out.println(this.getTaille());
		}
	}
}
