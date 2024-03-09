package ama.test.block1.kotlin2


class MainClass {
    fun main() {
        val user = createUser()
        val listUser = createListOfUsers(user)
        filterUser(listUser)
        mapOfUser(listUser)
        user.checkAge()
        val authCallBack = object : AuthCallBack {
            override fun authSuccess() {
                println("authSuccess")
            }

            override fun authFailed() {
                println("authFailed")
            }

        }
        auth(user, authCallBack, ::updateCache)
        doAction(Action.Logout(), user, authCallBack, ::updateCache)
        doAction(Action.Registration(), user, authCallBack, ::updateCache)
        doAction(Action.Login(user), user, authCallBack, ::updateCache)
    }

    //4. Создать объект класса User, вывести в лог startTime данного юзера, после вызвать
    // Thread.sleep(1000) и повторно вывести в лог startTime.
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

    //5. Создать список пользователей, содержащий в себе один объект класса User.
    // Используя функцию apply, добавить ещё несколько объектов класса User в список пользователей.
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

    //6. Получить список пользователей, у которых имеется полный доступ
    // (поле type имеет значение FULL).
    private fun filterUser(listUser: List<User>) =
        listUser.filter { it.type == Type.FULL }

    //7. Преобразовать список User в список имен пользователей. Получить первый и
    // последний элементы списка и вывести их в лог.
    private fun mapOfUser(listUser: List<User>) {
        val listName = listUser.map { user -> user.name }
        println(listName.first())
        println(listName.last())
    }

    //8. Создать функцию-расширение класса User, которая проверяет, что юзер старше 18 лет,
    // и в случае успеха выводит в лог, а в случае неуспеха возвращает ошибку.
    private fun User.checkAge() {
        when (this.age) {
            in 18..100 -> println(this.age)
            else -> throw IllegalStateException("Возраст меньше 18: ${this.age}")
        }
    }

    //10. Реализовать inline функцию auth, принимающую в качестве параметра функцию updateCache.
    // Функция updateCache должна выводить в лог информацию об обновлении кэша.

    //11. Внутри функции auth вызвать метод коллбека authSuccess и переданный updateCache,
    // если проверка возраста пользователя произошла без ошибки. В случае получения ошибки
    // вызвать authFailed.
    private inline fun auth(user: User, authCallBack: AuthCallBack, noinline upd: () -> Unit) {
        try {
            authCallBack.authSuccess()
            upd
        } catch (e: Exception) {
            authCallBack.authFailed()
        }
    }

    private fun updateCache() {
        println("кэш обновлён")
    }

    //13. Реализовать метод doAction, принимающий экземпляр класса Action. В зависимости от
    // переданного действия выводить в лог текст, к примеру “Auth started”. Для действия Login
    // вызывать метод auth.
    private fun doAction(action: Action, user: User, authCallBack: AuthCallBack, upd: () -> Unit) {
        when (action) {
            is Action.Login -> {
                println("login")
                auth(user, authCallBack, upd)
            }

            is Action.Logout -> println("logout")
            is Action.Registration -> println("Auth started")
        }
    }
}