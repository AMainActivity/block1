package ama.test.block1.kotlin2

//12. Реализовать изолированный класс Action и его наследников – Registration, Login и Logout. Login должен принимать в
//качестве параметра экземпляр класса User.
sealed class Action {
    class Registration() : Action()
    class Logout() : Action()
    class Login(val user: User) : Action()
}