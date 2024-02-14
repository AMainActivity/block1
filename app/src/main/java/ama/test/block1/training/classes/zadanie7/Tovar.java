package ama.test.block1.training.classes.zadanie7;


class Tovar {
    private String name;
    private String infoTovar;

    public Tovar(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getinfoTovar() {
        return infoTovar;
    }

    public void setinfoTovar(String infoTovar) {
        this.infoTovar = infoTovar;
        System.out.printf("информация о товаре: %s\n", infoTovar);
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.printf("Наименование товара: %s\n", getName());
    }
}
