// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri ("https://plugins.gradle.org/m2/")
        }

    }


    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.38.1")

        classpath ("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }




}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}