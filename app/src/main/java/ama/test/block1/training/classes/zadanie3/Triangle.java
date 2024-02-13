package ama.test.block1.training.classes.zadanie3;

/*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */
public class Triangle {
    private int a;
    private Point a_point;
    private int b;
    private Point b_point;
    private int c;
    private Point c_point;

    public Triangle(int a, int b, int c) {
        if (a > 0 && b > 0 && c > 0 &&
                a + b > c &&
                a + c > b &&
                c + b > a) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else throw new IllegalStateException("ошибка, с такими сторонами трегольник не существет");
    }

    public Triangle(Point a_point, Point b_point, Point c_point) {
        this(a_point.getDistanceTo(b_point), a_point.getDistanceTo(c_point), b_point.getDistanceTo(c_point));
        this.a_point = a_point;
        this.b_point = b_point;
        this.c_point = c_point;
    }

    public Triangle() {
    }

    public double getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Point getA_point() {
        return a_point;
    }

    public void setA_point(Point a_p) {
        this.a_point = a_p;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Point getB_point() {
        return b_point;
    }

    public void setB_point(Point b_p) {
        this.b_point = b_p;
    }

    public double getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Point getC_point() {
        return c_point;
    }

    public void setC_point(Point c_p) {
        this.c_point = c_p;
    }

    public double squareOfTriangle() {
        double p = 0.5 * perimetrOfTriangle();
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double perimetrOfTriangle() {
        return (a + b + c);
    }

    public Point getPointPerececeniaMedian() {
        int xA1 = (b_point.x + c_point.x) / 2;
        int yA1 = (b_point.y + c_point.y) / 2;
        int xM = (a_point.x + 2 * xA1) / 3;
        int yM = (a_point.y + 2 * yA1) / 3;
        return new Point(xM, yM);
    }

    @Override
    public String toString() {
        return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
    }


    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistanceTo(Point p) {
            return (int) Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
        }
    }
}
