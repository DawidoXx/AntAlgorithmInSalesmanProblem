public class Route {
    private double[][] pheromons;
    private double[][] distance;
    private double ro;

    public double[][] getPheromons() {
        return pheromons;
    }

    public double[][] getDistance() {
        return distance;
    }

    public Route(Point2D[] points, double startingAmountOfPheromon, double ro) {
        distance = new double[points.length][points.length];
        pheromons = new double[points.length][points.length];
        this.ro = ro;
        calculateDistance(points);
        fillOutPheromoneMatrix(startingAmountOfPheromon, points);
        System.out.println("\n");
        writeDistanceMatrix(points);
        //System.out.println("\n");
        // writePheromoneMatrix(points);
    }

    //LOWER PHEROMON LEVEL BY TIME
    public void update() {
        for (int i = 0; i < pheromons.length; i++) {
            for (int j = 0; j < pheromons.length; j++) {
                pheromons[i][j] = (1 - ro) * pheromons[i][j];
            }
        }
    }

    //CALCULATE AND FILL OUT DISTANCE MATRIX
    public void calculateDistance(Point2D[] points) {
        for (int i = 0; i < distance.length ; i++) {
            for (int j = 0; j < distance.length; j++) {
                if (i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = points[i].calculateDistanceBetween2Points(points[j]);
            }
        }
    }

    //FILL OUT PHEROMONE MATRIX WITH STARTING AMOUNT OF PHEROMONE
    public void fillOutPheromoneMatrix(double startingAmountOfPheromone, Point2D[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                pheromons[i][j] = startingAmountOfPheromone;
            }
        }
    }

    //GIVES BACK DISTANCE MATRIX
    public void writeDistanceMatrix(Point2D[] points) {
        for (int i = 0; i < points.length ; i++) {
            for (int j = 0; j < points.length; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public void writePheromoneMatrix(Point2D[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                System.out.print(pheromons[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
