package ama.test.block1.kotlin2


class MainClass {
    fun main() {
        //4. Создать объект класса User, вывести в лог startTime данного юзера, после вызвать Thread.sleep(1000) и повторно вывести в
        //лог startTime.
        val user = User(
            1,
            "Ivan",
            30,
            Type.FULL
        )
        println(user.startTime)
        Thread.sleep(1000)
        println(user.startTime)

        //5. Создать список пользователей, содержащий в себе один объект класса User. Используя функцию apply, добавить ещё
        //несколько объектов класса User в список пользователей.
        val listUser = mutableListOf(user)
        val user2 = User(
            2,
            "Petr",
            25,
            Type.DEMO
        )
        val user3 = User(
            3,
            "Sidor",
            20,
            Type.FULL
        )
        listUser.apply {
            add(user2)
            add(user3)
        }
    }
}