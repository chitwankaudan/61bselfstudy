public class NBody {

    /* readRadius takes in a file name and returns the radius in which 
    all planets within that file can be contained in.**/
    public static double readRadius(String filename){
        In dat = new In(filename);
        int N = dat.readInt();
        double rrad = dat.readDouble();
        return rrad;
    }
    
    /* readPlanets takes in a file name and returns an 
    array of all planets listed within the file
    **/
    public static Planet[] readPlanets(String filename){
        In dat = new In(filename);
        int N = dat.readInt();
        double rrad = dat.readDouble();

        Planet[] arr = new Planet[N];
        int i = 0;

        while (i != N){
            double xxPos = dat.readDouble();
            double yyPos = dat.readDouble();
            double xxVel = dat.readDouble();
            double yyVel = dat.readDouble();
            double mass = dat.readDouble();
            String img = dat.readString();
            arr[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
            i += 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        // Sets given terminal inputs to variables
        double T = Double.parseDouble(args[0]); 
        double dt = Double.parseDouble(args[1]);
        String file = args[2];
        
        double radius = readRadius(file);
        Planet[] planets = readPlanets(file);

        // Pastes the static background
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        // Pastes the initial positions of all the planets
        for (Planet i : planets) {
            i.draw();
        }

        // Animates the planets in increments of dt until time T.
        double time = 0;

        while (time <= T){

            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            int i = 0;

            for (int i = 0; i < planets.length; i++) {

                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show(10); 
            time  += dt;
        }
        // Prints out the ending positions of all the planets in a text file format
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }       


    }

}
