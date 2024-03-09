package ama.test.block1.training.classes.zadanie6;

class Abiturient extends Person {
    private double averOcenka;

    public Abiturient(String name) {

        super(name);
    }

    public void setAverOcenka(double oc) {
        this.averOcenka = oc;
    }

    public double getAverOcenka() {
        return averOcenka;
    }

    public void display() {

        System.out.printf("имя студента: %s\n", super.getName());
    }
}
