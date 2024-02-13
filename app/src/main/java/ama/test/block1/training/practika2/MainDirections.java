package ama.test.block1.training.practika2;


import static ama.test.block1.training.practika2.Directions.*;

public class MainDirections {
    public static void main(String[] args) {
        multiMove();
    }

    static Point moveTo(Directions napravlenie, Point p) {
        int newX = p.getX();
        int newY = p.getY();
        switch (napravlenie) {
            case UP -> newY = newY + 1;

            case DOWN -> newY = newY - 1;
            case LEFT -> newX = newX - 1;
            case RIGHT -> newX = newX + 1;
        }
        return new Point(newX, newY);
    }

    static void multiMove() {
        Point location = new Point(0, 0);
        Directions[] moveArray = new Directions[]{UP, UP, LEFT, DOWN,
                LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};
        for (int i = 0; i < moveArray.length; i++) {
            location = moveTo(moveArray[i], location);
            System.out.println(location.printPoint());
        }
    }

}


enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT
}
