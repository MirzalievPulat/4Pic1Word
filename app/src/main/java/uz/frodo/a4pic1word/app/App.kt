package uz.frodo.a4pic1word.app

import android.app.Application
import uz.frodo.a4pic1word.data.MyPref

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        MyPref.initialize(getSharedPreferences("MyPreferences", MODE_PRIVATE))
    }
}