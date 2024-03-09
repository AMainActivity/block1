package ama.test.block1.training.classes.zadanie4;

/*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */
public class ClassTime {
    private int hour;
    private int minute;
    private int second;

    public ClassTime(int hour, int minute, int second) {
        if (hour > 0 && minute > 0 && second > 0 &&
                hour < 60 &&
                minute < 60 &&
                second < 60) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else throw new IllegalStateException("ошибка, не могу задать время с указанными параметрами");
    }

    public ClassTime(int hour, int minute) {
        if (hour > 0 && minute > 0 &&
                hour < 60 &&
                minute < 60) {
            this.hour = hour;
            this.minute = minute;
            this.second = 0;
        } else throw new IllegalStateException("ошибка, не могу задать время с указанными параметрами");
    }

    public void setTime(int hour, int minute, int second) {
        if (hour > 0 && minute > 0 && second > 0 &&
                hour < 60 &&
                minute < 60 &&
                second < 60) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else throw new IllegalStateException("ошибка, не могу задать время с указанными параметрами");
    }


    public void setHoir(int hour) {
        if (hour > 0 && hour < 60) {
            this.hour = hour;
        } else throw new IllegalStateException("ошибка, не могу задать час:" + hour);
    }

    public void setMinute(int minute) {
        if (minute > 0 && minute < 60) {
            this.minute = minute;
        } else throw new IllegalStateException("ошибка, не могу задать минуты:" + minute);
    }

    public void setSecond(int second) {
        if (hour > 0 && second < 60) {
            this.second = second;
        } else throw new IllegalStateException("ошибка, не могу задать секунды:" + second);
    }
}
