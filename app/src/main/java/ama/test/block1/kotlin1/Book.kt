package ama.test.block1.kotlin1

class Book(override var price: Double, override var wordCount: Int) : Publication {
    override fun getType() = when (wordCount) {
        in 0..1000 -> "Flash Fiction"
        in 1000..7500 -> "Short Story"
        else -> "Novel"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Book) return this.getType() == other.getType()
                && this.price == other.price
                && this.wordCount == other.wordCount
        return false
    }
}