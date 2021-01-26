import java.lang.Math; 

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        return Math.sqrt(((xxPos - b.xxPos) * (xxPos - b.xxPos) + (yyPos - b.yyPos) * (yyPos - b.yyPos)));
    }

    public double calcForceExertedBy(Body b){
        return (G * mass * b.mass)/Math.pow(calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b){
        double dx = b.xxPos - xxPos;
        double F = calcForceExertedBy(b);
        return (F * dx) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        double dy = b.yyPos - yyPos;
        double F = calcForceExertedBy(b);
        return (F * dy) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allBodies){
        double net = 0;
        for (Body body: allBodies){
            if (this.equals(body))
                continue;
            net += calcForceExertedByX(body);
        }
        return net;
    }

    public double calcNetForceExertedByY(Body[] allBodies){
        double net = 0;
        for (Body body: allBodies){
            if (this.equals(body))
                continue;
            net += calcForceExertedByY(body);
        }
        return net;
    }

    public void update(double dt, double fX, double fY){
        double aNetX = fX / mass;
        double aNetY = fY / mass;
        xxVel += dt * aNetX;
        yyVel += dt * aNetY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, String.format("images/%s",imgFileName));
    }
}