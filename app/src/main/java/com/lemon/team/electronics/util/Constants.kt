package com.lemon.team.electronics.util

object Constants {

    object OrderStatus{
        const val SUCCESS = "success"
        const val ERROR = "fail"
    }

    const val MAIN_URL = "MAIN_URL"
    const val BASE_URL = "https://www.3d-iraq.com/api/"
    const val URL_PRODUCT_WEBSITE = "https://www.3d-iraq.com/product/"
    const val VIDEO_PATH = "https://www.3d-iraq.com/static/media/vedio-3d.146c63e2.mp4"

    const val VALID_NUMBER_OF_DIGIT_OF_PHONE_NUMBER = 11


    const val SORT_BY_CREATED_DATE="createdAt"
    const val PAGE_NUMBER_ZERO = 0

    const val COMPANY_FILE_NAME ="companies.json"

    const val NO_INTERNET ="No internet connection"

    const val LOG_TAG ="LEMON_TEAM"

    const val SEARCH_KEY ="search"
    const val SHARE_KEY ="share using"


    const val HEADSET_CATEGORY_HEADLINE ="Headset"
    const val LAPTOP_CATEGORY_HEADLINE ="Laptop"
    const val MOUSE_PAD_CATEGORY_HEADLINE ="Mouse Pad"
    const val CASE_CATEGORY_HEADLINE ="Case"


    const val CART = 1
    const val WISH = 2


    val  colors = listOf(
        ColorsRecycler.COLOR_ONE,
        ColorsRecycler.COLOR_TWO,
        ColorsRecycler.COLOR_THREE,
        ColorsRecycler.COLOR_TWO,
        ColorsRecycler.COLOR_THREE,
        ColorsRecycler.COLOR_ONE
    )
}