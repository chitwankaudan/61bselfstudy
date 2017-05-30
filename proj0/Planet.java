public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

     /* Planet object requires the following 5 variables*/
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /* Creates a copy of a given Planet object*/
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

     /* Calculates the distance between the given planet and any other planet*/

    public double calcDistance(Planet q){
        double dx = this.xxPos - q.xxPos;
        double dy = this.yyPos - q.yyPos;
        double r = Math.sqrt( (dx*dx) + (dy*dy) );
        return r;
    }

     /* Calcuates the total force exertd on given planet by another planet*/
    public double calcForceExertedBy(Planet q){
        double G = 6.67 * Math.pow(10, -11);
        double F = (G * this.mass * q.mass)/ Math.pow(calcDistance(q), 2);
        return F;
    }

     /* Calculates the force exerted on given planet by another planet along the x-axis*/
    public double calcForceExertedByX(Planet q){
        double dx =  q.xxPos - this.xxPos;
        double F_x = (calcForceExertedBy(q)* dx) / calcDistance(q);
        return F_x;
    }

     /* Calculates the force exerted on given planet by another planet along the y-axis*/
    public double calcForceExertedByY(Planet q){
        double dy =  q.yyPos - this.yyPos;
        double F_y = (calcForceExertedBy(q)* dy) / calcDistance(q);
        return F_y;
    }

    /* Calculates the net force exert on given planet by several another planets along the x-axis*/
    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet p : planets) {
            if (p == this) {
                continue;
            }
            else {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

     /* Calculates the net force exert on given planet by several another planets along the y-axis*/
    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet p : planets) {
            if (p == this) {
                continue;
            }
            else {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

     /* Updates the instance variables of the given planet according to the forces that was exerted upon it*/
    public void update(double dt, double fx, double fy){
        double a_x = fx / this.mass;
        double a_y = fy / this.mass;
        this.xxVel = this.xxVel + (a_x * dt);
        this.yyVel = this.yyVel + (a_y * dt);
        this.xxPos = this.xxPos + (this.xxVel * dt);
        this.yyPos = this.yyPos + (this.yyVel * dt);
    }
    
     /* Draws a given planet in its current position*/
    public void draw(){
        String filename = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, yyPos, filename);
    }

}
