package ama.test.block1.training.practika2;

public class Point {
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String printPoint() {
        return "("+x+","+y+")";
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistanceTo(Point p) {
        return (int) Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }
}
