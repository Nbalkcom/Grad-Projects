
interface Shape {
    void draw(int x, int y, int z, int j);
}

class Line {
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Line from point A(" + x1 + ";" + y1 + "), to point B(" + x2 + ";" + y2 + ")");
    }
}

class Rectangle {
    public void draw(int x, int y, int width, int height) {
        System.out.println("Rectangle with coordinate left-down point (" + x + ";" + y + "), width: " + width
                + ", height: " + height);
    }
}

class LineAdapter implements Shape {
    private Line adaptee;
    //Accepts a Line object
    public LineAdapter(Line line) {
        this.adaptee = line;
    }
    //Overrides the Line class's draw method however it will still use the same output
    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        adaptee.draw(x1, y1, x2, y2);
    }
}

class RectangleAdapter implements Shape {
    private Rectangle adaptee;
    //Accepts a Rectangle object
    public RectangleAdapter(Rectangle rectangle) {
        this.adaptee = rectangle;
    }
    //Overrides the Rectangle class's draw method by conducting Math functions to convert the variables that were meant for the Line object 
    //into variables that are necessary for the Rectangle object
    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);	//Picks the smallest out of the two variables
        int y = Math.min(y1, y2);	//Picks the smallest out of the two variables
        int width = Math.abs(x2 - x1);	//Takes the absolute value from subtracting the two variables
        int height = Math.abs(y2 - y1);	//Takes the absolute value from subtracting the two variables
        adaptee.draw(x, y, width, height);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        Shape[] shapes = {new RectangleAdapter(new Rectangle()),
                          new LineAdapter(new Line())};
        int x1 = 40, y1 = 20;
        int x2 = 30, y2 = 60;
        for (Shape shape : shapes) {
            shape.draw(x1, y1, x2, y2);
        }
    }
}