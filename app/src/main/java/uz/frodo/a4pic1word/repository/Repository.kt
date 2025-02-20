package uz.frodo.a4pic1word.repository

import uz.frodo.a4pic1word.data.Question

interface Repository {
    fun getTests():ArrayList<Question>
    fun getCoins():Int
    fun saveCoins(value: Int)
    fun getLevel():Int
    fun saveLevel(value:Int)
    fun removeLevel()
}