
public abstract class Point {
    public int px; // position in x
    public int py; // position in y
    public int Red;
    public int Green;
    public int Blue;
    /*
     * Return the euclidean distance between this point
     * and another point p
     * @param p another point
     * @return the euclidean distance
     */
    public  double getDistance(Point p) {
        double distance;
        distance = Math.pow(px * px + py * py, 1 / 2);
        return distance;
    }
    /*
     * Return the difference in grayscale between this point
     * and another point p
     * @param p another point
     * @return the grayscale difference
     */
    public  int grayscaleDiff(Point p){
        int diff;
        diff= (Red+Green+Blue)/3;
        return diff;
    }

}
