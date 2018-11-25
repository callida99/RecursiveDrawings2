
public class recursiveTree {

    public static void main(String[] args) {

        System.out.print("Drawing figure ... ");

        // filledSquares3(new double[]{0.4,0.1}, 0.1, 0);
       // squareTree(new double[]{0.4, 0.1}, 0.1, 0, 0.5, 0.7);
        squareTree(new double[]{0.6,0.1}, 0.1, 0, 0.7,0.2);

        System.out.println("done.");
    }

    private static void squareTree(double[] center, double radius, double angle, double leftFactor, double rightFactor) {
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        if (radius * Math.sqrt(2) < 0.001) {
            filledSquares3(center, radius, angle);
        }
        else{
            if(radius<0.01){
                StdDraw.setPenColor(StdDraw.GREEN);
            }
            filledSquareRotated(center,radius,angle);
            double[]delta = {radius,radius+rightFactor*radius*Math.sqrt(2)};
            delta = rotatePoint(delta,angle);
            squareTree(new double[]{center[0]+delta[0],center[1]+delta[1]},radius*rightFactor,angle-Math.PI/4,leftFactor,rightFactor);

            delta = new double[]{-radius,radius+leftFactor*radius*Math.sqrt(2)};
            delta = rotatePoint(delta,angle);
            squareTree(new double[]{center[0]+delta[0],center[1]+delta[1]},radius*leftFactor,angle-Math.PI/4,leftFactor,rightFactor);

            StdDraw.setPenColor(StdDraw.BOOK_RED);
        }

    }


    // Vorgegebene Hilfsmethode zum Rotieren eines Punktes in 2-D. Diese Methode wird von
    // 'filledSquareRotated' benutzt.
    private static double[] rotatePoint(double[] point, double angle) {
        double[] result = new double[2];
        result[0] = point[0] * Math.cos(angle) - point[1] * Math.sin(angle);
        result[1] = point[0] * Math.sin(angle) + point[1] * Math.cos(angle);
        return result;
    }

    // Vorgegebene Methode zum Zeichnen eines rotierten gefuellten Quadrates
    private static void filledSquareRotated(double[] center, double radius, double angle) {

        // Bestimmung der 4 Eckpunkte (dabei den Mittelpunkt nicht beruecksichtigen):
        double[] upperLeft = {-radius, radius};
        double[] upperRight = {radius, radius};
        double[] lowerLeft = {-radius, -radius};
        double[] lowerRight = {radius, -radius};

        // Jeden Eckpunkt jeweils mittels Rotationsformel drehen (um den Ursprung).
        double[] rotUpperLeft = rotatePoint(upperLeft, angle);
        double[] rotUpperRight = rotatePoint(upperRight, angle);
        double[] rotLowerLeft = rotatePoint(lowerLeft, angle);
        double[] rotLowerRight = rotatePoint(lowerRight, angle);

        // Gedrehtes Quadrat um seinen Mittelpunkt (center[0],center[1]) zeichnen.
        StdDraw.filledPolygon(new double[]{rotUpperLeft[0] + center[0], rotUpperRight[0] + center[0],
                        rotLowerRight[0] + center[0], rotLowerLeft[0] + center[0]},
                new double[]{rotUpperLeft[1] + center[1], rotUpperRight[1] + center[1],
                        rotLowerRight[1] + center[1], rotLowerLeft[1] + center[1]});
    }

    // Vorgegeben: Zeichne die gegebene Figur mit drei Quadraten
    public static void filledSquares3(double[] center, double radius, double angle) {

        filledSquareRotated(center, radius, angle);

        double[] delta = {radius, radius + 0.5 * radius * Math.sqrt(2)};
        delta = rotatePoint(delta, angle);

        filledSquareRotated(new double[]{center[0] + delta[0], center[1] + delta[1]},
                0.5 * radius, angle - Math.PI / 4);

        delta = new double[]{-radius, radius + 0.2 * radius * Math.sqrt(2)};
        delta = rotatePoint(delta, angle);

        filledSquareRotated(new double[]{center[0] + delta[0], center[1] + delta[1]},
                0.2 * radius, angle + Math.PI / 4);


    }

}


