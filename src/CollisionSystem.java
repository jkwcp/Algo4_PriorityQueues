// Fill PQ with all potential particle-wall collisions
// Fill PQ with all potential particle-particle collisions
//
// Main loop.
//  Delete the impending event from PQ(min priority = t)
//  If the event has been invalidated, ignore it
//  Advance all particles to time t, on a straight-line trajectory
//  Update the velocities of the colliding particles(s)
//  Predict future particle-wall and particle-particle collisions involving the collidng particles and insert events
//      onto PQ.

import edu.princeton.cs.algs4.MinPQ;

// Conventions
//  Neither particle null -> p-p collision
//  One p null -> p-w colllision
//  Both p null -? redraw event
public class CollisionSystem {
    private MinPQ<Event> pq;
    private double t = 0.0;
    private Particle[] particles;
    private int n;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
        this.n = particles.length;
    }

    private void predict(Particle a) {
        if (a == null) return;
        for (int i = 0; i < n; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }

        pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), a, null));

    }

    private void redraw() {}

    private void simulate() {
        pq = new MinPQ<Event>();
        for (int i = 0; i < n; i++) predict(particles[i]);
        pq.insert(new Event(0, null, null));

        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) continue;
            Particle a = event.a;
            Particle b = event.b;

            for (int i = 0; i < n; i++) {
                particles[i].move(event.time - t);
            }

            t = event.time;

            if (a != null && b != null)
                a.bounceOff(b);
            else if (a != null && b == null)
                a.bounceOffVerticalWall();
            else if (a == null && b != null)
                b.bounceOffHorizontalWall();
            else if (a == null && b == null)
                redraw();

            predict(a);
            predict(b);
        }
    }
}
