package ama.test.block1

data class MenuItem(
    val name: String,
    val resId: Int,
    val munuId:Int
) {

    companion object {
        fun createMenuItemList(): ArrayList<MenuItem> {
            val list = ArrayList<MenuItem>()
           // list.add(MenuItem("Главная", R.drawable.rv_item_restaurant))
            list.add(MenuItem("Меню", R.drawable.rv_item_menu,R.id.navigation_menu))
            list.add(MenuItem("Акции", R.drawable.rv_item_akcii,R.id.navigation_akcii))
            list.add(MenuItem("Заказы", R.drawable.rv_item_otzyvy,R.id.navigation_orders))
            list.add(MenuItem("Доставка", R.drawable.rv_item_delivery,R.id.navigation_delivery))
            return list
        }
    }
}
