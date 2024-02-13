package ama.test.block1.training.practika2;

public class MainClassLambda {
    static Runnable myClosure = () -> System.out.println("I love Java");

    public static void main(String[] args) {
        new Thread(myClosure).start();
        repeatTask(10, myClosure);
    }

    public static void repeatTask(int times, Runnable task) {
        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }
}
