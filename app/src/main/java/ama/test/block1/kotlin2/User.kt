package ama.test.block1.kotlin2

import java.util.Date

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val type: Type
) {
    private lateinit var startTime: String
}