package ama.test.block1

data class MenuItem(
    val name: String,
    val resId: Int
) {

    companion object {
        fun createMenuItemList(): ArrayList<MenuItem> {
            val list = ArrayList<MenuItem>()
            list.add(MenuItem("Главная", R.drawable.rv_item_restaurant))
            list.add(MenuItem("Меню", R.drawable.rv_item_menu))
            list.add(MenuItem("Акции", R.drawable.rv_item_akcii))
            list.add(MenuItem("Заказы", R.drawable.rv_item_otzyvy))
            list.add(MenuItem("Доставка", R.drawable.rv_item_delivery))
            return list
        }
    }
}
