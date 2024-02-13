package ama.test.block1.training.classes.zadanie6;

public class Fakultet {
    private String name;
    private Abiturient student;

    public String getName() {
        return name;
    }

    public Fakultet(String name) {
        this.name = name;
    }

    public void setStudentFakultet(Abiturient student) {
        this.student = student;
        System.out.printf("Студент: %s \t записался в факультет: %s \n", student.getName(), name);
    }
}
