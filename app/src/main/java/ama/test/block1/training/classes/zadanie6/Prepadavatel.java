package ama.test.block1.training.classes.zadanie6;

class Prepadavatel extends Person {

    public Prepadavatel(String name) {

        super(name);
    }

    public void display() {

        System.out.printf("имя преподавателя: %s\n", super.getName());
    }
}
