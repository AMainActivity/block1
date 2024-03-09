package ama.test.block1.training;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(List<T> firstList, List<T> secondList) {
        //TODO: implement it
        if (firstList == null || secondList == null)
            throw new NullPointerException("empty list");
        List<T> result = new ArrayList<T>(firstList.size() + secondList.size());
        result.addAll(firstList);
        result.addAll(secondList);
        result.sort(Collections.reverseOrder());
        return result;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(List<T> inputList) {
        //TODO: implement it
        if (inputList == null) throw new NullPointerException("empty list");
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < inputList.size(); i++) {
            result.add(inputList.get(i));
            result.addAll(inputList.subList(0, i));
        }
        return result;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(List<T> firstList, List<T> secondList) {
        //TODO: implement it
        if (firstList == null || secondList == null) throw new NullPointerException("empty list");
        Set<T> set1 = new TreeSet<>(firstList);
        Set<T> set2 = new TreeSet<>(secondList);
        return set1.equals(set2);
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(List<T> inputList, int n) {
        //TODO: implement it
        if (inputList == null) throw new NullPointerException("empty list");
        Collections.rotate(inputList, n);
        return inputList;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(List<String> inputList, String a,
                                        String b) {
        if (a == null || b == null) throw new NullPointerException("empty list");
        List<String> r = new ArrayList<>();
        for (String s : inputList) {
            if (s.equals(a)) r.add(s.replace(a, b));
            else r.add(s);
        }
        //TODO: implement it
        return r;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
    public static void main(String[] args) {
        getBestStudents();
    }


    static void getBestStudents() {
        List<Student> studList = new ArrayList<>();
        addStudents(studList);

        List<String> courseSorted =
                studList.stream()
                        .sorted(Comparator
                                .comparing(Student::getGroup)
                        )
                        .map(Student::getGroup)
                        .distinct().collect(Collectors.toList());

        for (String st : courseSorted) {
            Student minStud = studList.stream()
                    .filter(student -> student.getGroup() == st)
                    .max(Comparator.comparing(Student::getAverageOcenka))
                    .orElseThrow(NoSuchElementException::new);

            System.out.println(st + ": " + minStud.getStudentCourse());
        }
    }

    static void getYangestStudent() {
        List<Student> studList = new ArrayList<>();
        addStudents(studList);
        Student minStud = studList.stream()
                .max(Comparator.comparing(Student::getYear))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(minStud.getStudentCourse());
    }

    static void getOldestStudent() {
        List<Student> studList = new ArrayList<>();
        addStudents(studList);
        Student minStud = studList.stream()
                .min(Comparator.comparing(Student::getYear))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(minStud.getStudentCourse());
    }

    static void getAverageOcenka() {
        List<Student> studList = new ArrayList<>();
        addStudents(studList);

        List<String> courseSorted =
                studList.stream()
                        .sorted(Comparator
                                .comparing(Student::getGroup)
                        )
                        .map(Student::getGroup)
                        .distinct()
                        .collect(Collectors.toList());

        for (String st : courseSorted) {
            double avr1 = studList.stream()
                    .filter(stud -> stud.getGroup() == st)
                    .mapToDouble(stud -> stud.ocenkaPredmet1)
                    .average().orElse(0);
            double avr2 = studList.stream()
                    .filter(stud -> stud.getGroup() == st)
                    .mapToDouble(stud -> stud.ocenkaPredmet2)
                    .average().orElse(0);
            double avr3 = studList.stream()
                    .filter(stud -> stud.getGroup() == st)
                    .mapToDouble(stud -> stud.ocenkaPredmet3)
                    .average().orElse(0);
            double avr4 = studList.stream()
                    .filter(stud -> stud.getGroup() == st)
                    .mapToDouble(stud -> stud.ocenkaPredmet4)
                    .average().orElse(0);
            double avr5 = studList.stream()
                    .filter(stud -> stud.getGroup() == st)
                    .mapToDouble(stud -> stud.ocenkaPredmet5)
                    .average().orElse(0);
            System.out.println(st + ": " + avr1 + ": " + avr2 + ": " + avr3 + ": " + avr4 + ": " + avr5);
        }
    }

    static void studentExampleSorted() {
        List<Student> studList = new ArrayList<>();
        addStudents(studList);
        // List<Student> courseSorted = //new ArrayList<>(studList);
        studList.sort(Comparator
                .comparingDouble(Student::getCourse)
                .thenComparing(Student::getSurname/*new StudentComparator())*/));
        for (Student st : studList) {
            System.out.println(st.getStudentCourse());
        }
    }

    static void addStudents(List<Student> studList) {
        studList.add(new Student("ivanov", "иван", "иванович",
                2001, 3, "фмф03", 5,
                4, 3, 5, 4));

        studList.add(new Student("Петров", "Петр", "Петрович",
                2002, 2, "фмф02", 4,
                3, 5, 4, 3));

        studList.add(new Student("Сидоров", "Сидор", "Сидорович",
                2003, 4, "фмф04", 3,
                5, 4, 3, 5));

        studList.add(new Student("ivanenko", "Алексей", "Григорьевич",
                2004, 3, "фмф03", 5,
                4, 5, 5, 4));

        studList.add(new Student("alekseev", "Антон", "Петрович",
                2003, 3, "фмф03", 4,
                3, 5, 4, 3));

    }

    static class StudentComparator implements Comparator<Student> {

        public int compare(Student a, Student b) {

            return a.getSurname().compareTo(b.getSurname());
        }
    }

    static class StudentCourseComparator implements Comparator<Student> {

        public int compare(Student a, Student b) {

            return a.getCourse().compareTo(b.getCourse());
        }
    }

    static class Student {
        private String surname = "";
        private String name = "";
        private String fatherName = "";
        private int birthYear = 1970;
        private int courseNumber = 0;
        private String groupNumber = "";
        private int ocenkaPredmet1 = 0;
        private int ocenkaPredmet2 = 0;
        private int ocenkaPredmet3 = 0;
        private int ocenkaPredmet4 = 0;
        private int ocenkaPredmet5 = 0;

        String getStudentCourse() {
            return surname + " " + name + " " + fatherName + ", " + courseNumber
                    + ", др: " + birthYear + ", сред.оценка: " + getAverageOcenka();
        }

        int getYear() {
            return birthYear;
        }

        double getAverageOcenka() {
            return ((double) ocenkaPredmet1 + ocenkaPredmet2 + ocenkaPredmet3 + ocenkaPredmet4 + ocenkaPredmet5) / 5;
        }

        int getOcenkaPredmet1() {
            return ocenkaPredmet1;
        }

        int getOcenkaPredmet2() {
            return ocenkaPredmet2;
        }

        int getOcenkaPredmet3() {
            return ocenkaPredmet3;
        }

        int getOcenkaPredmet4() {
            return ocenkaPredmet4;
        }

        int getOcenkaPredmet5() {
            return ocenkaPredmet5;
        }

        Integer getCourse() {
            return courseNumber;
        }

        String getGroup() {
            return groupNumber;
        }

        String getSurname() {
            return surname;
        }

        Student(String surname,
                String name,
                String fatherName,
                int birthYear,
                int courseNumber,
                String groupNumber,
                int ocenkaPredmet1,
                int ocenkaPredmet2,
                int ocenkaPredmet3,
                int ocenkaPredmet4,
                int ocenkaPredmet5) {
            this.surname = surname;
            this.name = name;
            this.fatherName = fatherName;
            this.birthYear = birthYear;
            this.courseNumber = courseNumber;
            this.groupNumber = groupNumber;
            this.ocenkaPredmet1 = ocenkaPredmet1;
            this.ocenkaPredmet2 = ocenkaPredmet2;
            this.ocenkaPredmet3 = ocenkaPredmet3;
            this.ocenkaPredmet4 = ocenkaPredmet4;
            this.ocenkaPredmet5 = ocenkaPredmet5;
        }
    }
}
