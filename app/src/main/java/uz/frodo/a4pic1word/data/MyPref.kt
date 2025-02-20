package uz.frodo.a4pic1word.data

import android.content.SharedPreferences

object MyPref {
    private lateinit var sharedPreferences:SharedPreferences
    fun initialize(sharedPreferences: SharedPreferences){
        this.sharedPreferences = sharedPreferences
    }

    fun saveString(key:String, value:String){
        sharedPreferences.edit().putString(key, value).apply()
    }
    fun getString(key: String, def:String):String{
        return sharedPreferences.getString(key,def)!!;
    }

    fun saveInt(key:String, value:Int){
        sharedPreferences.edit().putInt(key, value).apply()
    }
    fun getInt(key: String, def:Int):Int{
        return sharedPreferences.getInt(key,def);
    }
    fun remove(key: String){
        println("MyPref: remove")
         sharedPreferences.edit().remove(key).apply()
    }

}