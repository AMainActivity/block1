package ama.test.block1.kotlin2

//9. Создать интерфейс AuthCallback с методами authSuccess, authFailed и реализовать анонимный
// объект данного интерфейса. В методах необходимо вывести в лог информацию о статусе авторизации.
interface AuthCallBack {
    fun authSuccess()
    fun authFailed()
}