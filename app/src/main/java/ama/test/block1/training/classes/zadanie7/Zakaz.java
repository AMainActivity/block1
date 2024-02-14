package ama.test.block1.training.classes.zadanie7;


import ama.test.block1.training.classes.zadanie7.Tovar;

import java.util.ArrayList;
import java.util.List;

class Zakaz {
    private String name;
    private List<Tovar> tovarList = new ArrayList<>();


    public Zakaz(String name) {
        this.name = name;
    }


    public void addToTovarList(Tovar tovar) {
        this.tovarList.add(tovar);
        System.out.printf("товар: %s", tovar.getName() + "добавлен в список покупок\n");
    }

    public List<Tovar> getTovarList() {
        return tovarList;
    }

    public void clearTovarList() {
        tovarList.clear();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.printf("Наименование товара: %s\n", getName());
    }
}
