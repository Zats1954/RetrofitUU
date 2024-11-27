package ru.zatsoft.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class OnViewPagerModel(val name: String, val imageView: Int, val author: String, val check: Boolean): Parcelable {

    companion object{
    val viewPagerList = mutableListOf(
        OnViewPagerModel("ПРОГРАММА \"ПОГОДА В ГОРОДАХ\"", R.drawable.ciklon,
            "Пессимист жалуется на ветер. Оптимист надеется на перемену погоды. Реалист ставит паруса.", true),
        OnViewPagerModel("ПРОИЗВОДСТВО \"URBI-UNI\" Ltd.", R.drawable.desert, "Неплохо узнать прогноз погоды, прежде чем начинать молиться о дожде.", true),
        OnViewPagerModel("ВСЕ ПРАВА ЗАЩИЩЕНЫ",R.drawable.rainy_days, "Некоторые люди чувствуют дождь. Остальные лишь промокают!", true),
        OnViewPagerModel("ВВЕДИТЕ НАЗВАНИЕ ГОРОДА",R.drawable.winter, " ",false),
    )
}
}