// 1. Subsystem
class PointCartesian {
    private double x, y;
    public PointCartesian(double x, double y ) {
        this.x = x;
        this.y = y;
    }

    public void  move( int x, int y ) {
        this.x += x;
        this.y += y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

// 1. Subsystem
class PointPolar {
    private double radius, angle;

    public PointPolar(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    public void  rotate(int angle) {
        this.angle += angle % 360;
    }

    public String toString() {
        return "[" + radius + "@" + angle + "]";
    }
}
	//The Point class borrows from both classes' methods to make one class and have them work with each other
// 1. Desired interface: move(), rotate()
class Point {
    // 2. Design a "wrapper" class
    private PointCartesian pointCartesian;	

    public Point(double x, double y) {
        pointCartesian = new PointCartesian(x, y);
    }

    public String toString() {
        return pointCartesian.toString();	//Borrows method from PointCartesian class
    }

    // 4. Wrapper maps
    public void move(int x, int y) {
        pointCartesian.move(x, y);	//Borrows method from PointCartesian class
    }

    public void rotate(int angle, Point o) {
        double x = pointCartesian.getX() - o.pointCartesian.getX();	//Borrows method from PointCartesian class
        double y = pointCartesian.getY() - o.pointCartesian.getY();	//Borrows method from PointCartesian class
        PointPolar pointPolar = new PointPolar(Math.sqrt(x * x + y * y),Math.atan2(y, x) * 180 / Math.PI);
        // 4. Wrapper maps
        pointPolar.rotate(angle);	//Borrows method from PointPolar class
        System.out.println("  PointPolar is " + pointPolar);
        String str = pointPolar.toString();	//Borrows method from PointPolar class
        int i = str.indexOf('@');
        double r = Double.parseDouble(str.substring(1, i));
        double a = Double.parseDouble(str.substring(i + 1, str.length() - 1));
        pointCartesian = new PointCartesian(r*Math.cos(a*Math.PI/180) + o.pointCartesian.getX(),
                r*Math.sin(a * Math.PI / 180) + o.pointCartesian.getY());
    }
}

class Line {
    private Point o, e;	//Uses the Facade Point class
    public Line(Point ori, Point end) {
        o = ori;
        e = end;
    }

    public void  move(int x, int y) {
        o.move(x, y);	//Uses the Facade Point class methods
        e.move(x, y);	//Uses the Facade Point class methods
    }

    public void  rotate(int angle) {
        e.rotate(angle, o);	//Uses the Facade Point class methods
    }

    public String toString() {
        return "origin is " + o + ", end is " + e;
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        // 3. Client uses the Facade
        Line lineA = new Line(new Point(5, 5), new Point(5.5, 8.7));
        lineA.move(-1, -2);
        System.out.println( "after move:  " + lineA );
        lineA.rotate(90);
        System.out.println( "after rotate: " + lineA );
        Line lineB = new Line( new Point(2, 1), new Point(2.866, 1.5));
        lineB.rotate(45);
        System.out.println("30 degrees to 60 degrees: " + lineB);
    }
}