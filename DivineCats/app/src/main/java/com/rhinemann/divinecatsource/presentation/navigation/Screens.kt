package com.rhinemann.divinecatsource.presentation.navigation

import androidx.fragment.app.Fragment
import com.rhinemann.divinecatsource.presentation.screen.randomcat.RandcatFragment
import com.rhinemann.divinecatsource.presentation.screen.search.SearchFragment
import com.rhinemann.divinecatsource.presentation.screen.search_result.SearchResultFragment

/**
 * Created by dronpascal on 08.10.2021.
 */
interface Screen {

    fun destination(): Fragment

    val tag: String? get() = null
}

class RandomCatScreen : Screen {

    override fun destination(): Fragment = RandcatFragment()
    override val tag: String get() = TAG

    companion object {
        const val TAG = "RandomCatScreen"
    }
}

class SearchScreen : Screen {

    override fun destination(): Fragment = SearchFragment()
    override val tag: String get() = TAG

    companion object {
        const val TAG = "SearchScreen"
    }
}

class SearchResultScreen : Screen {

    override fun destination(): Fragment = SearchResultFragment()
}

//class FavoriteScreen : Screen {
//
//    override fun destination(): Fragment = FavoriteFragment()
//}
//
//data class CatFullscreenScreen(
//    private val cat: Cat
//) : Screen {
//
//    override fun destination(): Fragment = CatFullscreenFragment()
//}