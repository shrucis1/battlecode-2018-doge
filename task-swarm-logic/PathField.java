import bc.*;

/**
 * Contains a "vector field" of directions of sorts; following arrows
 * will yield a path towards the target. Is not generated by instantiating
 * a new PathField, you must instead use PathMaster to generate one.
 */
public class PathField
{
    protected PlanetMap basemap;
    protected MapLocation target;
    protected PathPoint[][] field;

    public PathField(PlanetMap basemap, MapLocation target) {
        this.basemap = basemap;
        this.target = target;
        this.field = new PathPoint[(int)basemap.getWidth()][(int)basemap.getHeight()];
    }

    /*
     *   START PUBLIC-FACING METHODS THAT YOU SHOULD BE USING
     */


    /**
     * Checks if (x,y) is on the map, essentially (x,y positive, less than width,height)
     */
    public boolean isPointValid(int x, int y) {
        return (x>=0)&&(y>=0)&&(x<basemap.getWidth())&&(y<basemap.getHeight());
    }

    public boolean isPointValid(MapLocation ml) {
        return isPointValid(ml.getX(), ml.getY());
    }

    /**
     * Checks if this PathField has an entry for (x,y). You should try this before calling
     * getDirectionAtPoint and getDistanceAtPoint
     *
     * Cases where it does:
     *  - x,y is valid (on the map) AND is passable terrain AND is connected via passable
     *    terrain to the target location
     *
     * Cases where it doesn't
     *  - x,y is not valid (not on the map)
     *  - x,y does not contain passable terrain
     *  - x,y is not connected via passable terrain to target (more common on mars, where
     *     we tend to have disjointed "islands", because of reverse floodfilling)
     */
    public boolean isPointSet(int x, int y) {
        return field[x][y] != null;
    }

    public boolean isPointSet(MapLocation ml) {
        return isPointSet(ml.getX(), ml.getY());
    }

    /**
     * Returns the direction to move at this point to follow the shortest path to this
     * PathField's target
     */
    public Direction getDirectionAtPoint(int x, int y) {
        return getPoint(x,y).dir;
    }

    public Direction getDirectionAtPoint(MapLocation ml) {
        return getDirectionAtPoint(ml.getX(), ml.getY());
    }

    /**
     * Assuming you follow the directions provided by this pathfield, the number of steps
     * you must take to reach this PathField's target
     */
    public int getDistanceAtPoint(int x, int y) {
        return getPoint(x,y).dist;
    }

    public int getDistanceAtPoint(MapLocation ml) {
        return getDistanceAtPoint(ml.getX(), ml.getY());
    }


    // uses internal class
    public PathPoint getPoint(int x, int y) {
        return field[x][y];
    }

    public PathPoint getPoint(MapLocation ml) {
        return getPoint(ml.getX(), ml.getY());
    }

    public MapLocation getTargetLocation() {
        return this.target;
    }

    /**
     *  END PUBLIC FACING METHODS
     */

    public void setPoint(int x, int y, Direction dir, int dist) {
        field[x][y] = new PathPoint(dir, dist);
    }

    public class PathPoint
    {
        public final Direction dir;
        public final int dist;

        public PathPoint(Direction dir, int dist) {
            this.dir = dir;
            this.dist = dist;
        }

        public Direction getDirection() { return this.dir; }
        public int getDistance() { return this.dist; }
    }
}