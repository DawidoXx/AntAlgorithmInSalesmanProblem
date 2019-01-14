import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> sciezkaMiastWynik;
    private static double minDistance;
    private static int amountOfPoints;
    private static double amountOfPheromoneAtStart;
    private static int startingPoint;
    private static Point2D[] points;
    private static Route AntColonyRoute;
    private static Ant ant;
    private static Scanner scanner;
    private static double ro;
    private static int alfa,beta,iteracje,amountOfAnts;

    public static void main(String[] args) {
        loadData();
        AntColonyRoute=new Route(points,amountOfPheromoneAtStart,ro);
        for (int i=0;i<iteracje;i++){
            for (int j=0;j<amountOfAnts;j++){
                ant= new Ant(amountOfPoints,startingPoint);
                for (int k=0;k<amountOfPoints-1;k++){
                    ant.AntIsMakingASteap(AntColonyRoute.getPheromons(),AntColonyRoute.getDistance(),alfa,beta);
                }
                ant.setDistance(ant.getDistance()+AntColonyRoute.getDistance()[ant.getMiasta().get(ant.getMiasta().size()-1)][ant.getMiasta().get(0)]);
                ant.miasta.add(ant.miasta.get(0));
                ant.leaveAPheromoneTrace(AntColonyRoute.getPheromons());
                //System.out.println(ant.getDistance());
                if (i==0&&j==0) {
                    minDistance = ant.getDistance();
                    sciezkaMiastWynik = ant.miasta;
                }
                else if(minDistance>ant.getDistance()) {
                    minDistance = ant.getDistance();
                    sciezkaMiastWynik = ant.miasta;
                }

            }
            AntColonyRoute.update();
            //AntColonyRoute.writePheromoneMatrix(points);
            //System.out.println(ant.getDistance());
            }
        AntColonyRoute.writePheromoneMatrix(points);
        System.out.println(minDistance);
            //wypisz ścieżkę
        for (Integer punkt: sciezkaMiastWynik) {
            System.out.println(punkt);
        }
    }

    public static void loadData(){
//        amountOfPoints=5;
//        points=new Point2D[amountOfPoints];
//        points[0]=new Point2D(1,3);
//        points[1]=new Point2D(3,2);
//        points[2]=new Point2D(4,1);
//        points[3]=new Point2D(2,3);
//        points[4]=new Point2D(4,4);
//        startingPoint=2;
//        amountOfPheromoneAtStart=1.2;
//        ro=0.2;
//        alfa=1;
//        beta=2;
//        amountOfAnts=20;
//        iteracje=3;

        scanner=new Scanner(System.in);
        System.out.println("Podaj ilość punktów");
        amountOfPoints=scanner.nextInt();
        points=new Point2D[amountOfPoints];
        System.out.println("Podaj punkty, x i y");
        for (int i=0;i<amountOfPoints;i++){
            points[i]=new Point2D(scanner.nextInt(),scanner.nextInt());
        }
        System.out.println("Podaj punkt początkowy");
        startingPoint=scanner.nextInt();
        System.out.println("Podaj początkową wartość feromonu");
        amountOfPheromoneAtStart=scanner.nextDouble();
        System.out.println("Podaj współczynnik odparowywania");
        ro=scanner.nextDouble();
        System.out.println("Podaj alfe");
        alfa=scanner.nextInt();
        System.out.println("Podaj beta");
        beta=scanner.nextInt();
        System.out.println("Podaj ilość mrówek");
        amountOfAnts=scanner.nextInt();
        System.out.println("Podaj ilość iteracji");
        iteracje=scanner.nextInt();
    }
}
