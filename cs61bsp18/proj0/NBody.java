public class NBody {
    public static final String BACKGROUND = "images/starfield.jpg";

    public static double readRadius(String filename) {
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int number = in.readInt();
        Planet[] planets = new Planet[number];
        double radius = in.readDouble();

        for (int i = 0; i < number; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String planetImage = in.readString();

            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, planetImage);
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        StdAudio.loop("audio/2001.mid");
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, NBody.BACKGROUND);
        for (Planet planet : planets) {
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();
        for (int t = 0; t < T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                for (int j = 0; j < planets.length; j++) {
                    if (i != j) {
                        xForces[i] += planets[i].calcForceExertedByX(planets[j]);
                        yForces[i] += planets[i].calcForceExertedByY(planets[j]);
                    }
                }

                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, NBody.BACKGROUND);
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();

            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %s\n", planets[i].xxPos, planets[i].yyPos,
                    planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

        StdAudio.close();
    }
}