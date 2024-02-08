package ama.test.block1.training;

import java.util.Arrays;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {
    public static void main(String[] args) {
        sort(new int[]{1, -5, 4000, 84654, 687, 8, 548, 7, 6});
        maxValue(1, -54, 54, 54, 8, 7, 4, 65, 98, 100);
        reverse(new int[]{1, -5, 22, 4, 8, 5, -15, 40, 500, 846, 54, 687, 8, 548, 7, 6});
        fibonacciNumbers(40);
        maxCountSymbol(new int[]{1, -5, 1, 1, 7, 8, 548, 7, 6});
        ;
    }

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public static int[] sort(int[] valuesArray) {
        //TODO: implement it
        System.out.println("sort");
        System.out.println("исходный: " + Arrays.toString(valuesArray));
        for (int i = valuesArray.length - 1; i >= 1; i--) {
            for (int k = 0; k < i; k++) {
                if (valuesArray[k] > valuesArray[k + 1]) {
                    long dummy = valuesArray[k];
                    valuesArray[k] = valuesArray[k + 1];
                    valuesArray[k + 1] = (int) dummy;
                }
            }
        }
        System.out.println("отсортированный: " + Arrays.toString(valuesArray));
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public static int maxValue(int... values) {
        System.out.println("maxValue");
        System.out.println("исходный: " + Arrays.toString(values));
        int max = 0;
        for (int value : values) {
            if (value > max)
                max = value;
        }

        //TODO: implement it
        System.out.println(max);
        return max;
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public static int[] reverse(int[] array) {
        System.out.println("reverse");
        System.out.println("исходный: " + Arrays.toString(array));
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
        System.out.println("отсортированный: " + Arrays.toString(array));
        return array;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public static int[] fibonacciNumbers(int numbersCount) {
        //TODO: implement it
        System.out.println("fibonacciNumbers");
        if (numbersCount < 1) {
            System.out.println(Arrays.toString(new int[]{}));
            return new int[]{};
        } else {
            int[] arr = new int[numbersCount];
            arr[0] = 1;
            arr[1] = 1;
            for (int i = 2; i < arr.length; ++i) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            System.out.println(Arrays.toString(arr));
            return arr;
        }
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public static int maxCountSymbol(int[] array) {
        System.out.println("maxCountSymbol");
        System.out.println("исходный: " + Arrays.toString(array));
        //TODO: implement it
        int[] ar = new int[array.length];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    count ++;
                    if (j== array.length - 1)
                        ar[i] = count;
                }
            }
        }
        System.out.println(Arrays.toString(ar));
        return count;
    }
}
