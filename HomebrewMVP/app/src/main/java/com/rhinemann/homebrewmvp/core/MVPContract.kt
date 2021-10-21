package com.rhinemann.homebrewmvp.core

/**
 * Created by dronpascal on 21.10.2021.
 */
interface MVPContract {

    interface Presenter<V: View>{
        fun attachView(view: V)
        fun detachView(view: V)
    }

    interface View

}