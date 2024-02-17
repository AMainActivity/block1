package ama.test.block1.kotlin1

class Magazine(override var price: Double, override var wordCount: Int) : Publication {
    override fun getType(): String = "Magazine"

}