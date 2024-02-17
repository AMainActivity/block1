package ama.test.block1.kotlin1

class Book(override var price: Double, override var wordCount: Int) : Publication {
    override fun getType() = when (wordCount) {
        in 0..1000 -> "Flash Fiction"
        in 1000..7500 -> "Short Story"
        else -> "Novel"
    }
}