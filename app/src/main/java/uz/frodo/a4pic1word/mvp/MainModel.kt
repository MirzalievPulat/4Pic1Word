package uz.frodo.a4pic1word.mvp

import uz.frodo.a4pic1word.data.Question
import uz.frodo.a4pic1word.repository.RepositoryImpl

class MainModel:MainContract.Model {

    private val questions = RepositoryImpl.getTests()

    override fun getQuestion(index: Int): Question {
        return questions[index]
    }

    override fun getQuestionCount(): Int {
        return questions.size
    }

    override fun getCoins(): Int {
        return RepositoryImpl.getCoins()
    }

    override fun saveCoins(value: Int) {
        RepositoryImpl.saveCoins(value)
    }

    override fun getLevel(): Int {
        return RepositoryImpl.getLevel()
    }

    override fun saveLevel(value: Int) {
        RepositoryImpl.saveLevel(value)
    }

    override fun removeLevel() {
        RepositoryImpl.removeLevel()
    }


}