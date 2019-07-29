// Event driven simulation
// Change state only when something happen
//  Between collisions, particles move in straight-line
//  Focus only on times when collisions occur
//  Maintain PQ of collision events, prioritized by time
//  Remove the min = get next collision

// Prediction ( at time t )
//  Given position, velocity, and radius of a particle, when will it collide
// Resolution ( at time t + dt)
//  when collision, update colliding particles
public class Particle extends Ball{
    private final double mass;
    private int count;

    public Particle() {
        super();
        this.count = 0;
        this.mass = 0.01;
    }

    public double timeToHit(Particle that) {
        return 0.0;
    }

    public double timeToHitVerticalWall() {
        return 0.0;
    }

    public double timeToHitHorizontalWall() {
        return 0.0;
    }

    public void bounceOff(Particle that) {

    }

    public void bounceOffVerticalWall() {

    }

    public void bounceOffHorizontalWall() {

    }
}
