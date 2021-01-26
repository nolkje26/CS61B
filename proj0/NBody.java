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
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering(); // enables double buffer
        StdDraw.clear(); // clears drawing window
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Body body : bodies){
            body.draw();
        }
        StdDraw.show();
        StdDraw.pause(2000);
    }
}