package ama.test.block1.training.classes.zadanie7;

/*
      VII

      Задача на взаимодействие между классами. Разработать систему «интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */
public class MainInternetShop {
    public static void main(String[] args) {
        Tovar tovar1 = new Tovar("Ноутбук Acer");
        Tovar tovar2 = new Tovar("Монитор Acer");
        Tovar tovar3 = new Tovar("мышка Acer");
        Tovaroved admin = new Tovaroved("Администратор");
        admin.setInfoTovar(tovar1, "ssd 256гб, ОЗУ 8гб");
        admin.setInfoTovar(tovar2, "IPS 23'");
        admin.setInfoTovar(tovar3, "беспроводная");

        Client client1 = new Client("иванов иван иванович");
        Client client2 = new Client("Хулиган");
        Zakaz zakaz1 = new Zakaz("Заказ иванова и.и.");
        zakaz1.addToTovarList(tovar1);
        zakaz1.addToTovarList(tovar2);
        zakaz1.addToTovarList(tovar3);
        client1.setZakaz(zakaz1);
        client1.setPayed(true);

        Pradazha prod = new Pradazha("продажа1");
        admin.setRegisterTovar(zakaz1, prod, client1);
        admin.setClientInBlackList(client2, true);
    }
}
