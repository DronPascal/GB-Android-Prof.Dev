@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 11.11.2021.
 */
object Build {
    private const val androidBuildToolsVersion = "7.0.3"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
}