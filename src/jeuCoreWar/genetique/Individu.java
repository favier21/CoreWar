package jeuCoreWar.genetique;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class Individu {

    //liste de int car chaque warrior correspond a un int car on a fait un switch dans l'interpreteur
    private List<int[]> code;
    public Individu(){
        this.code = new ArrayList<>();
    }
    public Individu(int[] ligneCode) {
        this();
        this.code.add(ligneCode);
    }

    public List<int[]> getCode() {
        return this.code;
    }
    public int getTaille(){
        return this.code.size();
    }
    public void addCode(int[] code) {
        this.code.add(code);
    }
    public void removeCode(int index) {
        this.code.remove(index);
    }
    public void modifierCode(int index,int nCase,int valeur){
        int[] ligne = this.getCode().get(index);
        ligne[nCase] = valeur;
    }

    public Individu copy(){
        Individu copie = new Individu();
        for (int[] ligne : this.code) {
            copie.addCode(Arrays.copyOf(ligne,5));
        }
        return copie;
    }

    public int getEfficacite() {
        EvaluationIndividu eval = new EvaluationIndividu(this);
        return eval.evaluer();
    }
    public void mutationAleatoire(){
        Random rand = new Random();
        int choix = 0;
        if (!this.code.isEmpty()) {
            choix = rand.nextInt(6);
        }
        
        int ligne = 0;
        switch (choix) {
            case 0: //ajouter une ligne
                this.addCode(new int[]{rand.nextInt(11),rand.nextInt(3),rand.nextInt(50),rand.nextInt(3),rand.nextInt(50)});
                break;
            case 1: //retirer une ligne
                this.removeCode(rand.nextInt(this.getTaille()));
                break;
            case 2: //modifier Moda
                ligne = rand.nextInt(this.getTaille());
                this.modifierCode(ligne, 1, rand.nextInt(3));
                break;
            case 3: //modifier a
                ligne = rand.nextInt(this.getTaille());
                this.modifierCode(ligne, 2, rand.nextInt(50));
                break;
            case 4: //modifier Modb
                ligne = rand.nextInt(this.getTaille());
                this.modifierCode(ligne, 3, rand.nextInt(3));
                break;
            case 5: //modifier b
                ligne = rand.nextInt(this.getTaille());
                this.modifierCode(ligne, 4, rand.nextInt(50));
                break;
            default:
                break;
        }
    }
}
