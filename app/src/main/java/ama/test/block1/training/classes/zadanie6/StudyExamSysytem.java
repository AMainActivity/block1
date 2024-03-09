package ama.test.block1.training.classes.zadanie6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
     VI

     Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
     Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
     Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
    */
public class StudyExamSysytem {
    public static void main(String[] args) {
        Abiturient student1 = new Abiturient("Ivanov Ivan Ivanovich");
        Prepadavatel prepod1 = new Prepadavatel("Антонов Антон Антонович");
        Abiturient student2 = new Abiturient("Ivanenko Ivan Ivanovich");
        Abiturient student3 = new Abiturient("Antonov Ivan Ivanovich");
        Prepadavatel prepod2 = new Prepadavatel("Степаненко Антон Антонович");
        Fakultet fak1 = new Fakultet("ФМФ");
        fak1.setStudentFakultet(student1);
        Ocenka oc1 = new Ocenka(5);
        Ocenka oc2 = new Ocenka(4);
        Ocenka oc3 = new Ocenka(5);
        zachislenieStudenta(student1, prepod1, oc1, oc2, oc3);
        zachislenieStudenta(student2, prepod2, oc1, oc1, oc3);
        zachislenieStudenta(student3, prepod1, oc2, oc2, oc2);

        List<Abiturient> abList = new ArrayList<>();
        abList.add(student1);
        abList.add(student2);
        abList.add(student3);
        isPostupil(abList, 4);
    }

    static void isPostupil(List<Abiturient> abList, int succesOcenca) {
        List<Abiturient> res = abList.stream().filter(stud -> stud.getAverOcenka() > succesOcenca).sorted(Comparator.comparing(Abiturient::getName)).collect(Collectors.toList());
        for (Abiturient ab : res) {
            System.out.println("успешно поступил: " + ab.getName());
        }
    }

    static void zachislenieStudenta(Abiturient student1, Prepadavatel prepod1, Ocenka ocAlgebra, Ocenka ocPhisics, Ocenka ocHistaory) {
        Exam ex1 = new Exam();
        ex1.setStudentExam(student1);
        ex1.setOcenka(student1, ocAlgebra, Predmet.ALGEBRA, prepod1);
        ex1.setOcenka(student1, ocPhisics, Predmet.PHISICS, prepod1);
        ex1.setOcenka(student1, ocHistaory, Predmet.HISTORY, prepod1);
        int ocenka1 = ex1.getOcenkaByPredmet(Predmet.ALGEBRA);
        int ocenka2 = ex1.getOcenkaByPredmet(Predmet.PHISICS);
        int ocenka3 = ex1.getOcenkaByPredmet(Predmet.HISTORY);
        student1.setAverOcenka(getAverOcenka(ocenka1, ocenka2, ocenka3));
        System.out.println(getAverOcenka(ocenka1, ocenka2, ocenka3));
    }

    static double getAverOcenka(int a, int b, int c) {
        return (double) (a + b + c) / 3;
    }
}
