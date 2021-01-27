public class NBody{

    public static double readRadius(String filename){
        In in = new In(filename);
        in.readLine(); // skips over number of planets
        return in.readDouble();
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        double numberPlanets = in.readDouble();
        in.readDouble(); //skips over Radius
        Body[] arrBodies = new Body[(int)numberPlanets];
        for (int i = 0; i < arrBodies.length; i++){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String name = in.readString();
            arrBodies[i] = new Body(xPos, yPos, xV, yV, m, name);
        }
        return arrBodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        double time = 0;
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering(); // enables double buffer
        StdDraw.clear(); // clears drawing window

        while (time <= T){
            double[] arrXforces = new double[bodies.length];
            double[] arrYforces = new double[bodies.length];

            for (int i = 0; i < bodies.length; i++){
                arrXforces[i] = bodies[i].calcNetForceExertedByX(bodies);
                arrYforces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < bodies.length; i++){
                bodies[i].update(dT, arrXforces[i], arrYforces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body body : bodies){
                body.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dT;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}