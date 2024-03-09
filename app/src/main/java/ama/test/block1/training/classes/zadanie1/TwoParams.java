package ama.test.block1.training.classes.zadanie1;
/*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */


public class TwoParams {
    private int param1;
    private int param2;

    public void getParam1() {
        System.out.println("param1: " + param1);
    }

    public void getParam2() {
        System.out.println("param2: " + param2);
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public int sumParams() {
        return param1 + param2;
    }

    public int maxParam() {
        int max = param1;
        if (param2 > max) max = param2;
        return max;
    }


    public TwoParams(int param1, int param2) {
        this.param1 = param2;
        this.param2 = param1;
    }
}
