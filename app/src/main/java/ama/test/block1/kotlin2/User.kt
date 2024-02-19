package ama.test.block1.kotlin2

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
* 3. Реализовать класс данных User с полями id, name, age и type. У класса User создать ленивое свойство startTime, в котором
*получаем текущее время.
* */
data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val type: Type
) {
    lateinit var startTime: String

    init {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        startTime = LocalDateTime.now().format(formatter)
    }
}