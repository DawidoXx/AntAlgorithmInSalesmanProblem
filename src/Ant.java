import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
    private double distance;
    public ArrayList<Integer> miasta;
    private ArrayList<Boolean> odwiedzone;
    private double[] tabOfPropability;
    private int startPoint;
    private double suma;
    private double choice;
    private Random random;
    private double toHelp;

    public Ant(int amountOfCitys,int startPoint){
        this.miasta= new ArrayList<>();
        this.odwiedzone=new ArrayList<>();
        tabOfPropability=new double[amountOfCitys];
        this.startPoint=startPoint;
        miasta.add(startPoint);
        random=new Random();
        for (int i=0;i<amountOfCitys;i++) {
            odwiedzone.add(false);
        }
        odwiedzone.set(startPoint,true);
    }

    public void AntIsMakingASteap(double[][] pheromons,double[][] distances,int alfa,int beta) {
            suma=0;
                for (int j = 0; j < odwiedzone.size(); j++) {
                    if (odwiedzone.get(j) == false) {
                        tabOfPropability[j] = Math.pow(pheromons[startPoint][j], alfa) * Math.pow(1 / distances[startPoint][j], beta);
                        suma += tabOfPropability[j];
                    } else
                        tabOfPropability[j] = 0;
                }
                for (int j = 0; j < odwiedzone.size(); j++) {
                    tabOfPropability[j] = tabOfPropability[j] / suma;
                }
                //for (int k=0;k<odwiedzone.size();k++)
                //    System.out.println(tabOfPropability[k]);
                choice = random.nextDouble();

                toHelp = 0;
                for (int i = 0; i < odwiedzone.size(); i++) {
                    if (odwiedzone.get(i) == false) {
                        if (toHelp < choice && choice <= (toHelp + tabOfPropability[i])) {
                            distance += distances[startPoint][i];
                            startPoint = i;
                            miasta.add(i);
                            odwiedzone.set(i, true);
                        }
                        toHelp += tabOfPropability[i];
                    }
                }
    }

    public void leaveAPheromoneTrace(double[][] pheromones){
        for (int i=0;i<miasta.size()-1;i++){
            pheromones[miasta.get(i)][miasta.get(i+1)]+=amountOfPheromoneAntsLeaves();
            pheromones[miasta.get(i+1)][miasta.get(i)]+=amountOfPheromoneAntsLeaves();
        }
    }

    public double amountOfPheromoneAntsLeaves(){
        return 1/distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setMiasta(ArrayList<Integer> miasta) {
        this.miasta = miasta;
    }

    public double getDistance() {
        return distance;
    }

    public ArrayList<Integer> getMiasta() {
        return miasta;
    }

}
