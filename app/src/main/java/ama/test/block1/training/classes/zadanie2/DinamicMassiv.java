package ama.test.block1.training.classes.zadanie2;

import java.util.*;
import java.util.stream.Collectors;

/*
    II

    Создать класс, содержащий динамический массив и количество элементов в нем.
    Добавить конструктор, который выделяет память под заданное количество элементов.
    Добавить методы, позволяющие заполнять массив случайными числами,
    переставлять в данном массиве элементы в случайном порядке, находить количество
    различных элементов в массиве, выводить массив на экран.
   */
public class DinamicMassiv {

    public static void main(String[] args) {
        DinamicMassiv dm = new DinamicMassiv(10);
        dm.setRandomElements();
        dm.printArray();
        dm.setShuffleElements();
        dm.printArray();
        dm.getUniqueCountOfElements();

    }

    private int[] array;
    private int sizeArray;

    public DinamicMassiv(int[] array) {
        this.array = array;
        this.sizeArray = array.length;
    }

    public DinamicMassiv(int sizeArray) {
        this.array = new int[sizeArray];
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }


    public void setRandomElements() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }

    public void getUniqueCountOfElements() {
        Set<Integer> uniqueElements = Arrays.stream(array)
                .boxed().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(uniqueElements.size());
    }

    public void setShuffleElements() {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
