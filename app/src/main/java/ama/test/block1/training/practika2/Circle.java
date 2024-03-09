package ama.test.block1.training.practika2;

public class Circle implements Shape {
    private int diameter;


    @Override
    public double perimeter() {
        return (double) diameter * Math.PI;
    }

    @Override
    public double square() {
        return (double) Math.PI * diameter * diameter / 4;
    }
}
