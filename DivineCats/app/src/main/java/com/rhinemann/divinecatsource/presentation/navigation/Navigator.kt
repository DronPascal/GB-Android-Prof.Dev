package com.rhinemann.divinecatsource.presentation.navigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.rhinemann.divinecatsource.R

/**
 * Created by dronpascal on 09.10.2021.
 */
interface INavigator {

    val topFragment: Fragment? get() = null

    fun navigateTo(
        screen: Screen,
        addToBackStack: Boolean = true,
    )

    fun backTo(tag: String)

    fun back()
}

class Navigator(
    private val context: Context,
) : INavigator {

    override val topFragment: Fragment?
        get() = getFragmentManager().fragments.lastOrNull()

    override fun navigateTo(screen: Screen, addToBackStack: Boolean) {
        getFragmentManager().commit {
            replace(R.id.container, screen.destination(), screen.tag)
            if (addToBackStack) {
                addToBackStack(screen.tag)
            }
        }
    }

    override fun backTo(tag: String) {
        getFragmentManager().popBackStack(tag, 0)
    }

    override fun back() {
        getFragmentManager().popBackStackImmediate()
    }

    private fun getFragmentManager() = (context as AppCompatActivity).supportFragmentManager
}
