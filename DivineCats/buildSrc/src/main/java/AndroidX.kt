@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 11.11.2021.
 */
object AndroidX {
    private const val coreKtxVersion = "1.7.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.3.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val fragmentKtxVersion = "1.3.6"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"
}

object AndroidXView {
    private const val constraintLayoutVersion = "2.1.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    private const val recyclerViewVersion = "1.2.1"
    const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"

    private const val cardViewVersion = "1.0.0"
    const val cardView = "androidx.cardview:cardview:$cardViewVersion"
}

object AndroidXLifecycle {
    private const val lifecycleVmKtxVersion = "2.4.0"
    const val lifecycleVmKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVmKtxVersion"

    private const val lifecycleLdKtxVersion = "2.4.0"
    const val lifecycleLdKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleLdKtxVersion"
}

object AndroidXRoom {
    private const val roomVersion = "2.3.0"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
}

object AndroidXTest {
    private const val extJunitVersion = "1.1.3"
    const val extJunit = "androidx.test.ext:junit:$extJunitVersion"

    private const val espressoCoreVersion = "3.4.0"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
}