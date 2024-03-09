package ama.test.block1.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {
    public static void main(String[] args) {
        System.out.println(capitalReverse("Здравствуй, Мир!"));
    }

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public static String getOddCharacterString(String text) {
        //TODO: implement it
        StringBuilder sb = new StringBuilder();
        char[] mas = text.toCharArray();
        for (int i = 0; i < mas.length; i++) {
            if (i % 2 != 0)
                sb.append(mas[i]);
        }
        return sb.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public static int[] getArrayLastSymbol(String text) {
        //TODO: implement it
        char[] mas = text.toCharArray();
        List<Integer> res = new ArrayList<>();
        if (mas.length > 0) {
            char lastChar = mas[mas.length - 1];
            for (int i = 0; i < mas.length - 1; i++) {
                if (mas[i] == lastChar)
                    res.add(i);
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public static int getNumbersCount(String text) {
        //TODO: implement it
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public static String replaceAllNumbers(String text) {
        //TODO: implement it
        Map<String, String> map = new HashMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        map.put("6", "six");
        map.put("7", "seven");
        map.put("8", "eight");
        map.put("9", "nine");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String k = text.substring(i, i + 1);
            if (map.containsKey(k)) {
                String v = map.get(k);
                res.append(v);
            } else
                res.append(k);
        }
        return res.toString();
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public static String capitalReverse(String text) {
        //TODO: implement it
        StringBuilder newStr = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLowerCase(text.charAt(i))) {
                newStr.setCharAt(i, Character.toUpperCase(text.charAt(i)));
            } else if (Character.isUpperCase(text.charAt(i))) {
                newStr.setCharAt(i, Character.toLowerCase(text.charAt(i)));
            }
        }
        return newStr.toString();
    }

}
