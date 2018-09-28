public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G_CONSTANT = 6.67e-11;

    public Planet(double xPos, double yPos, double xVel, double yVel, double mass, String img) {
        this.xxPos = xPos;
        this.yyPos = yPos;
        this.xxVel = xVel;
        this.yyVel = yVel;
        this.mass = mass;
        this.imgFileName = img;
    }

    public Planet(Planet planet) {
        this.xxPos = planet.xxPos;
        this.yyPos = planet.yyPos;
        this.xxVel = planet.xxVel;
        this.yyVel = planet.yyVel;
        this.mass = planet.mass;
        this.imgFileName = planet.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = Math.abs(xxPos - p.xxPos);
        double dy = Math.abs(yyPos - p.yyPos);

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return Planet.G_CONSTANT * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double distance = calcDistance(p);
        double force = calcForceExertedBy(p);

        return force * (p.xxPos - xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double distance = calcDistance(p);
        double force = calcForceExertedBy(p);

        return force * (p.yyPos - yyPos) / distance;
    }

    public void update(double seconds, double fx, double fy) {
        xxVel += seconds * fx / mass;
        yyVel += seconds * fy / mass;

        xxPos += xxVel * seconds;
        yyPos += yyVel * seconds;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        // StdDraw.picture(0.5, 0.5, "images/" + imgFileName);
    }
}