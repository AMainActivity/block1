package ama.test.block1.training.classes.zadanie5;


import java.util.*;
import java.util.stream.Collectors;

/*
   V

   класс Абонент: идентификационный номер, Фамилия, имя, Отчество, Адрес,
   Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
   Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
   вывод информации. Создать массив объектов данного класса.
   Вывести сведения относительно абонентов, у которых время городских переговоров
   превышает заданное.  Сведения относительно абонентов, которые пользовались
   междугородной связью. Список абонентов в алфавитном порядке.
  */
public class AbonentTest {
    public static void main(String[] args) {
        Abonent[] abArray = new Abonent[5];
        abArray[0] = new Abonent("111", "Ivan", "Ivanov",
                "Ivanovich", "Moscow", 1111222233334444L, 1000,
                500, 100, 200);
        abArray[1] = new Abonent("222", "Anton", "Ivanenko",
                "Ivanovich", "S.Peterburg", 2222333344441111L, 5000,
                6000, 25, 300);
        abArray[2] = new Abonent("333", "Ivan", "Iveev",
                "Ivanovich", "Kazan", 3333444411112222L, 10000,
                8788, 1000, 100);
        abArray[3] = new Abonent("444", "Ivan", "Ivanovchenko",
                "Ivanovich", "Ufa", 4444111122223333L, 87964,
                6698, 56, 854);
        abArray[4] = new Abonent("555", "Ivan", "Ivanovenko",
                "Ivanovich", "Tomsk", 1111222233334444L, 2453,
                12, 45, 0);

       /* for (Abonent item : getAbonentFilterByTimeCityCall(40, abArray)) {
            System.out.println(item.getAbonent());
        }*/

        for (Abonent item : getAbonentFilterByHasTimeCityDistanceCall(abArray)) {
            System.out.println(item.getAbonent());
        }
    }

    static List<Abonent> getAbonentFilterByHasTimeCityDistanceCall(Abonent[] abArray) {
        return Arrays.stream(abArray).filter(abonent -> abonent.getTimeOfCityDistanceCall() > 0).sorted(Comparator
                .comparing(Abonent::getSirName)).collect(Collectors.toList());
    }

    static List<Abonent> getAbonentFilterByTimeCityCall(int mTime, Abonent[] abArray) {
        return Arrays.stream(abArray).filter(abonent -> abonent.getTimeOfCityCall() > mTime).sorted(Comparator
                .comparing(Abonent::getSirName)).collect(Collectors.toList());
    }

    public static class Abonent {
        private String identyString;
        private String name;
        private String sirName;
        private String fatherName;
        private String address;
        private long cardNumber;
        private long debet;
        private long credit;
        private int timeOfCityCall;
        private int timeOfCityDistanceCall;

        public Abonent(
                String identyString,
                String name,
                String sirName,
                String fatherName,
                String address,
                long cardNumber,
                long debet,
                long credit,
                int timeOfCityCall,
                int timeOfCityDistanceCall) {
            this.identyString = identyString;
            this.name = name;
            this.sirName = sirName;
            this.fatherName = fatherName;
            this.address = address;
            this.cardNumber = cardNumber;
            this.debet = debet;
            this.credit = credit;
            this.timeOfCityCall = timeOfCityCall;
            this.timeOfCityDistanceCall = timeOfCityDistanceCall;
        }

        public String getAbonent() {
            return identyString + ": " + name + " " + sirName + " " + fatherName + ", Адрес: " +
                    address + ", гор.звонки: " + timeOfCityCall + ", межгород.звонки: " + timeOfCityDistanceCall;
        }

        public String getIdentyString() {
            return this.identyString;
        }

        public void setIdentyString(String identyString) {
            this.identyString = identyString;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSirName() {
            return this.sirName;
        }

        public void setSirName(String sirName) {
            this.sirName = sirName;
        }

        public String getFatherName() {
            return this.fatherName;
        }

        public void setFatherName(String sirName) {
            this.fatherName = fatherName;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCardNumber() {
            return this.cardNumber;
        }

        public void setCardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
        }

        public long getDebet() {
            return this.debet;
        }

        public void setDebet(long debet) {
            this.debet = debet;
        }

        public long getCredit() {
            return this.credit;
        }

        public void setCredit(long credit) {
            this.credit = credit;
        }

        public int getTimeOfCityCall() {
            return this.timeOfCityCall;
        }

        public void setTimeOfCityCall(int timeOfCityCall) {
            this.timeOfCityCall = timeOfCityCall;
        }

        public int getTimeOfCityDistanceCall() {
            return this.timeOfCityDistanceCall;
        }

        public void setTimeOfCityDistanceCall(int timeOfCityDistanceCall) {
            this.timeOfCityDistanceCall = timeOfCityDistanceCall;
        }
    }
}
