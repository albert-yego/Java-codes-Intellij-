import java.awt.geom.Point2D;
import java.util.Arrays;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /** Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points)
    {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //************do nothing**************//
    }

    /** Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair()
    {
        return this.closestPointPair;
    }

    /** Main method for calculate and return closest point pair
     *
     * @param p --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex)
    {
        int n = lastIndex - startIndex;
        if ((n+1) == 3) {
            return getClosestPointPair(p[0], p[1], p[2]);
        } if((n+1) <= 2) {
            return new PointPair(p[0],p[1]);
        }

        int m = n/2;
        Point2D.Double mid = p[m];

        Point2D.Double[] left = Arrays.copyOfRange(p, 0, (n+1) / 2);
        Point2D.Double[] right = Arrays.copyOfRange(p, (n+1) / 2, n+1);

        PointPair leftside = calculateClosestPointPair(left, 0, left.length-1);
        PointPair rightside = calculateClosestPointPair(right, 0, right.length-1);

        PointPair close = getClosestPointPair(leftside, rightside);

        Point2D.Double arr[] = new Point2D.Double[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(p[i].x - mid.x) < close.getDistance()) {
                arr[j] = p[i];
                j++;
            }
        }

        return getClosestPointPair(close, stripClosest(arr, j, close));
    }

    /** calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        PointPair p21 = new PointPair(p2,p1);
        PointPair p32 = new PointPair(p3,p2);
        PointPair p31 = new PointPair(p3,p1);

        return getClosestPointPair(p21,getClosestPointPair(p32,p31));

    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2){
        if(p2.getDistance()> p1.getDistance()){
            return p1;
        } else
            return p2;
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip --> point array
     * @param size --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {
        double shortest = shortestLine.getDistance();
        PointPair min = shortestLine;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j<size && (strip[j].y - strip[i].y) < min.getDistance(); j++) {
                PointPair stp = new PointPair(strip[i], strip[j]);
                if (stp.getDistance() < shortest) {
                    min = stp;
                }
            }
        }
        return min;
    }
}