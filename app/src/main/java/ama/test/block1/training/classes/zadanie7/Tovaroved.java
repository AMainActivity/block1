package ama.test.block1.training.classes.zadanie7;


import javax.swing.text.TabableView;

class Tovaroved {
    private String fio;

    public Tovaroved(String fio) {
        this.fio = fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setInfoTovar(Tovar tovar, String info) {
        tovar.setinfoTovar(info);
    }

    public void setRegisterTovar(Zakaz zakaz, Pradazha prodazha, Client client) {
        prodazha.setRegister(zakaz, client);
    }

    public void setClientInBlackList(Client client, boolean b) {
        client.setInBlackList(b);
    }

    public String getFio() {
        return fio;
    }

    public void display() {
        System.out.printf("имя товароведа: %s\n", getFio());
    }
}
