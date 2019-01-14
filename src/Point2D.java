public class Point2D {
    private int X;
    private int Y;

    public Point2D(int X,int Y){
        this.X=X;
        this.Y=Y;
    }

    public double calculateDistanceBetween2Points(Point2D point2D){
        return Math.sqrt(Math.pow(point2D.X-this.X,2)+Math.pow(point2D.Y-this.Y,2));
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}

