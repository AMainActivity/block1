package ama.test.block1.training;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {
    public static void main(String[] args) {
        averageValue(5, 4);
        complicatedAmount(5, 4, 2);
        changeValue(3);
        swapNumbers(93435);
        zeroEvenNumber(45488);
    }

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public static double averageValue(int firstValue, int secondValue) {
        //TODO: implement it
        System.out.println("averageValue: " + firstValue + " and " + secondValue);
        double avgValue = ((double) firstValue + secondValue) / 2;
        System.out.println(avgValue);
        return avgValue;
    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public static double complicatedAmount(int firstValue, int secondValue, int thirdValue) {
        //TODO: implement it
        System.out.println("complicatedAmount: " + firstValue + " and " + secondValue + " and " + thirdValue);
        double result = 0;
        double first = firstValue * 2;
        double second = secondValue - 3;
        double third = thirdValue * thirdValue;
        result = first + second + third;
        System.out.println(result);
        return result;
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public static int changeValue(int value) {
        //TODO: implement it
        int res = 0;
        System.out.println("changeValue: " + value);
        if (value > 3) {
            res = value + 10;
        } else {
            res = value - 10;
        }
        System.out.println(res);
        return res;
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public static int swapNumbers(int value) {
        //TODO: implement it
        int res = value;
        System.out.println("swapNumbers: " + value);
        String numberString = Integer.toString(value);
        if (numberString.length() > 5) {
            return res;
        }
        char[] mas = numberString.toCharArray();
        //int[] intArray = new int[mas.length];
        char temp = mas[mas.length - 1];
        mas[mas.length - 1] = mas[0];
        mas[0] = temp;
        StringBuilder r = new StringBuilder();
        // int res = 0;
        for (char ma : mas) {
            r.append(Character.getNumericValue(ma));
        }

        System.out.println(Integer.parseInt(r.toString()));
        if (value > 10)
            res = Integer.parseInt(r.toString());
        return res;
    }

    /**
     * изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public static int zeroEvenNumber(int value) {
        //TODO: implement it
        int res = value;
        System.out.println("zeroEvenNumber: " + value);
        String numberString = Integer.toString(value);
        if (numberString.length() > 5) {
            return res;
        }
        StringBuilder r = new StringBuilder();
        char[] mas = numberString.toCharArray();
        for (int i = 0; i < mas.length; i++) {
            int t = Character.getNumericValue(mas[i]);
            if (t % 2 == 0) {
                t = 0;
            }
            r.append(t);
        }
        System.out.println(Integer.parseInt(r.toString()));
        if (value > 10)
            res = Integer.parseInt(r.toString());
        return res;
    }
}
