package ama.test.block1.training.classes.zadanie7;


class Client {
    private String fio;
    private Zakaz zakaz;
    private boolean isPayed = false;
    private boolean isInBlackList = false;

    public Client(String fio) {
        this.fio = fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setZakaz(Zakaz zakaz) {
        this.zakaz = zakaz;
    }

    public Zakaz getZakaz() {
        return this.zakaz;
    }

    public void setPayed(boolean isPayed) {
        this.isPayed = isPayed;
        System.out.printf("заказ: %s", zakaz.getName() + "оплачен\n");
    }

    public boolean getIsPayed() {
        return isPayed;
    }

    public String getFio() {
        return fio;
    }

    public void setInBlackList(boolean isInBlackList) {
        this.isInBlackList = isInBlackList;
        System.out.printf("клиент: %s", getFio() + " помещен в черный список\n");
    }

    public boolean getIsInBlackList() {
        return isInBlackList;
    }

    public void display() {
        System.out.printf("имя клиента: %s\n", getFio());
    }
}
