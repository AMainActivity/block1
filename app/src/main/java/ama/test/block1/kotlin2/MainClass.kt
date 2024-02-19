package ama.test.block1.kotlin2


class MainClass {
    fun main() {
        val user = createUser()
        val listUser = createListOfUsers(user)
        filterUser(listUser)
    }

    //4. Создать объект класса User, вывести в лог startTime данного юзера, после вызвать Thread.sleep(1000) и повторно вывести в
    //лог startTime.
    private fun createUser(): User {

        val user = User(
            1,
            "Ivan",
            30,
            Type.FULL
        )
        println(user.startTime)
        Thread.sleep(1000)
        println(user.startTime)
        return user
    }

    //5. Создать список пользователей, содержащий в себе один объект класса User. Используя функцию apply, добавить ещё
    //несколько объектов класса User в список пользователей.
    private fun createListOfUsers(user: User): List<User> {
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
        return listUser
    }

    //6. Получить список пользователей, у которых имеется полный доступ (поле type имеет значение FULL).
    private fun filterUser(listUser: List<User>) =
        listUser.filter { it.type == Type.FULL }

}