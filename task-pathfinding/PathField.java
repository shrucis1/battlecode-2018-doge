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
		this.field = new PathPoint[basemap.getWidth()][basemap.getHeight()]
	}

	public boolean isPointSet(int x, int y) {
		return field[x][y] != null;
	}

	public void setPoint(int x, int y, Direction dir, int dist) {
		field[x][y] = new PathPoint(dir, dist);
	}

	// uses internal class
	public PathPoint getPoint(int x, int y) {
		return field[x][y]
	}

	public Direction getDirectionAtPoint(int x, int y) {
		return getPoint(x,y).dir;
	}

	public int getDistanceAtPoint(int x, int y) {
		return getPoint(x,y).dist;
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