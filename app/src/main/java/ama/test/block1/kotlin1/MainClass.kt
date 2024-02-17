package ama.test.block1.kotlin1

class MainClass {
    /*
    * 3. Создать два объекта класса Book и один объект Magazine. Вывести в лог для каждого
    * объекта тип, количество строк и цену в
    * евро в отформатированном виде. У класса Book переопределить метод equals и произвести
    * сравнение сначала по ссылке,
    * затем используя метод equals. Результаты сравнений вывести в лог.
    */
    fun main() {
        val book1 = Book(price = 5.5, wordCount = 9999)
        val book2 = Book(price = 55.0, wordCount = 99999)
        val magazine = Magazine(price = 10.0, wordCount = 500)

        printFormatted(book1.getType(), book1.wordCount, book1.price)
        printFormatted(book2.getType(), book2.wordCount, book2.price)
        printFormatted(magazine.getType(), magazine.wordCount, magazine.price)

        println("сравнение по ссылке: ${book1 === book2}")
        println("сравнение по equals: ${book1 == book2}")
    }

    private fun printFormatted(classname: String, wordCount: Int, price: Double) {
        println("тип: $classname, количество строк: $wordCount, цена в евро: $price")
    }
}