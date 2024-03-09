package ama.test.block1.training.classes.zadanie6;

public class Exam {
    private Predmet predmet;
    private String predmetString;
    private Ocenka ocenkaAlgebra;
    private Ocenka ocenkaPhisics;
    private Ocenka ocenkaHistory;
    private Abiturient student;
    private Prepadavatel prepod;

    public void setOcenka(Abiturient name, Ocenka ocenka, Predmet predmet, Prepadavatel prepod) {
        this.student = name;
        this.prepod = prepod;
        switch (predmet) {
            case ALGEBRA -> {
                this.ocenkaAlgebra = ocenka;
                this.predmetString = "алгебра";
            }
            case PHISICS -> {
                this.ocenkaPhisics = ocenka;
                this.predmetString = "физика";
            }
            case HISTORY -> {
                this.ocenkaHistory = ocenka;
                this.predmetString = "история";
            }
        }
        System.out.printf("Преподаватель: %s \t поствил оценку %d \t \n" +
                "студенту %s \t по предмету  %s\n", prepod.getName(), ocenka.getOcenka(), student.getName(), predmetString);
    }

    public void setStudentExam(Abiturient student) {
        this.student = student;
        System.out.printf("Студент: %s \t сдал экзамены\n", student.getName());
    }

    public int getOcenkaByPredmet(Predmet predmet) {
        int res = 0;
        switch (predmet) {
            case ALGEBRA -> {
                if (ocenkaAlgebra != null) res = ocenkaAlgebra.getOcenka();
            }
            case PHISICS -> {
                if (ocenkaPhisics != null) res = ocenkaPhisics.getOcenka();
            }
            case HISTORY -> {
                if (ocenkaHistory != null) res = ocenkaHistory.getOcenka();
            }
        }
        ;
        return res;
    }
}
