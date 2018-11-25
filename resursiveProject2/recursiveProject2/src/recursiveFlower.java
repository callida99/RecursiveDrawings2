
public class recursiveFlower {

    static double angle = 0;

    public static void main(String[] args) {
        StdDraw.setXscale(0,512.0);
        StdDraw.setYscale(0,512.0);
        System.out.print("Drawing figure ... ");

        boxed(new double[]{0.5*512,0.5*512},0.3*512);

        System.out.println("done.");

    }



    private static void boxed(double[]center,double radius){

        if(radius>1){
            squareRotated(center,radius,angle);
            if(angle==0) {
                angle += Math.PI/4;
            }
            else {
                angle = 0;
            }
            boxed(center,radius/Math.sqrt((2)));

        }
    }
    private static void rotatePointMutatig(double[] point,double angle){
        point[0] =point[0]*Math.cos(angle) - point[1]*Math.sin(angle);
        point[1] = point[0]*Math.sin(angle) + point[1]*Math.cos(angle);
    }

    // Vorgegebene Methode zum Zeichnen eines rotierten Quadrates. Das Zentrum des Quadrates
    // wird mit einem Array angegeben, wobei die x-Koordinate dem Wert center[0] entspricht und
    // die y-Koordinate dem Wert center[1].
    private static void squareRotated(double[] center, double radius, double angle) {

        // Bestimmung der 4 Eckpunkte (dabei den Mittelpunkt nicht beruecksichtigen):

        double[] upperLeft = {-radius, radius};
        double[] upperRight = {radius, radius};
        double[] lowerLeft = {-radius, -radius};
        double[] lowerRight = {radius, -radius};

        // Jeden Eckpunkt jeweils mittels Rotationsformel drehen (um den Ursprung).
        double[] rotUpperLeft = rotatePoint(upperLeft,angle);
        double[] rotUpperRight = rotatePoint(upperRight,angle);
        double[] rotLowerLeft = rotatePoint(lowerLeft,angle);
        double[] rotLowerRight = rotatePoint(lowerRight,angle);

        // Gedrehtes Quadrat um seinen Mittelpunkt (center[0],center[1]) zeichnen.
        StdDraw.polygon(new double[]{rotUpperLeft[0]+center[0],rotUpperRight[0]+center[0],
                        rotLowerRight[0]+center[0],rotLowerLeft[0]+center[0]},
                        new double[]{rotUpperLeft[1]+center[1],rotUpperRight[1]+center[1],
                        rotLowerRight[1]+center[1],rotLowerLeft[1]+center[1]});
    }

    // Vorgegebene Hilfsmethode zum Rotieren eines Punktes in 2-D. Diese Methode wird von
    // 'squareRotated' benutzt.
    // 'rotatePoint' fuehrt eine Rotation des angegebenen Punktes 'point'
    // um den Ursprung aus. Der Rotationswinkel ist 'angle'.
    // 'point' muss ein Array mit zwei Eintraegen sein. point[0] ist die
    // x-Koordinate. point[1] ist die y-Koordinate.
    // Die Methode laesst 'point' unveraendert. Der rotierte Punkt wird als
    // Array mit zwei Eintraegen (x- und y-Koordinate) zurueckgeliefert.
    private static double[] rotatePoint(double[] point, double angle) {
        double[] result = new double[2];
        result[0] = point[0]*Math.cos(angle) - point[1]*Math.sin(angle);
        result[1] = point[0]*Math.sin(angle) + point[1]*Math.cos(angle);
        return result;
    }
}


