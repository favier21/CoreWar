package jeuCoreWar.genetique;

public class Selection {

    //selection par tournoi
    public static Individu selectionTournoi(){
        return selectionTournoi(100);
    }
    public static Individu selectionTournoi(int generations) {
        Individu imp = new Individu(new int[]{1,0,0,0,1});
        return selectionTournoi(imp,generations);
    }
    public static Individu selectionTournoi(Individu individu_de_base, int generations) {
        Population pop; 
        for (int i = 0; i < generations; i++) {
            //1. Créer une population à partir de l'individu de base
            pop = new Population(100, individu_de_base);

            //2. On applique une mutation à toute la population
            pop.mutationGenerale();

            //3. Chercher l'individu avec la meilleure efficacité parmi les individus sélectionnés
            Individu meilleurIndividu = null;
            int meilleurEfficacite = Integer.MIN_VALUE;
            for (Individu individu : pop.getIndividus()) {
                int efficacite = individu.getEfficacite();
                if (efficacite > meilleurEfficacite) {
                    meilleurEfficacite = efficacite;
                    meilleurIndividu = individu;
                }
            }
            //4.Définir le meilleur individu comme individu de base pour la prochaine generation
            individu_de_base = meilleurIndividu;
            //retour à la première étape
        }
        // Retourner l'individu avec la meilleure efficacité
        return individu_de_base;
    }
}
