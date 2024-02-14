package ama.test.block1.training.classes.zadanie7;


class Pradazha {
    private String name;
    private Zakaz zakaz;


    public Pradazha(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRegister(Zakaz zakaz,Client client) {
        if (client.getIsPayed())
        System.out.printf("Заказ оплачен и готов к выдаче: %s\n", zakaz.getName());
        else
            System.out.printf("Заказ не оплачен клиентом: %s\n", client.getFio());
    }
}
