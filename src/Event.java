
// Conventions
//  Neither particle null -> p-p collision
//  One p null -> p-w colllision
//  Both p null -? redraw event
public class Event implements Comparable<Event> {
    public double time;
    public Particle a, b;
    private int countA, countB;

    public Event(double t, Particle a, Particle b) {
        this.time = t;
        this.a = a;
        this.b = b;
        this.countA = 0;
        this.countB = 0;
    }

    public int compareTo(Event that) {
        return (int)(this.time - that.time);
    }

    public boolean isValid() {
        return false;
    }
}
