import edu.princeton.cs.algs4.StdDraw;

// Goal: simulate the motion of N moving particles that behave according to the laws of elastic collision
// Hard disc model:
//  Moving particles interact via elastic collisions with other and walls
//  Each particle is a disc with known position, velocity, mass and radius
//  No other force
// Significance:
//
public class BouncingBalls {
    public static void main(String[] args) {
        int n = 100;
        Ball[] balls = new Ball[n];
        for (int i = 0; i < n; i++) {
            balls[i] = new Ball();
        }

        while (true) {
            StdDraw.clear();
            for (int i = 0; i < n; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}
