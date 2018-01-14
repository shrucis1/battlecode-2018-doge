import bc.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class RangerSwarm extends Swarm {

	public int swarmMovementHeat = 10; 
	public final int SWARM_MOVEMENT_COOLDOWN = 20;

	public RangerSwarm() {
		super();
	}

	public void doTurn() {
		if(this.swarmIsMoving) {
			if(Utils.compareMapLocation(this.smarmLeader, this.swarmTarget)) {
				this.swarmIsMoving = false;
				swarmAttack(this.swarmTarget);
			} else {
				swarmMovementHeat -= 10;
				if(swarmMovementHeat < 10) {
					swarmMovementHeat += SWARM_MOVEMENT_COOLDOWN;
					//update swarmLeader location
				}
				moveToLeader();
			}
		} else {
			moveToLeader();
		}
	}

	public void moveToLeader() {

	}

	public void moveToTarget(Path path) {

	}

	public void swarmAttack(MapLocation location) {
		System.out.println("Whole swarm attacks this location");
	}
}