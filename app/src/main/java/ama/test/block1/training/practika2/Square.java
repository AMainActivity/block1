package ama.test.block1.training.practika2;

public class Square implements Shape {
    private int a;


    @Override
    public double perimeter() {
        return (double) 2 * a;
    }

    @Override
    public double square() {
        return (double) a * a;
    }
}
