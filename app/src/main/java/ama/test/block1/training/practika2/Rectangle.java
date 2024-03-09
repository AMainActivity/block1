package ama.test.block1.training.practika2;

public class Rectangle implements Shape {
    private int a;
    private int b;

    @Override
    public double perimeter() {
        return (double) a + b;
    }

    @Override
    public double square() {
        return (double) a * b;
    }
}
