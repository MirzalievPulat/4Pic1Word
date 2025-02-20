package uz.frodo.a4pic1word.repository

import uz.frodo.a4pic1word.R
import uz.frodo.a4pic1word.data.MyPref
import uz.frodo.a4pic1word.data.Question

object RepositoryImpl : Repository {
    private val questions = ArrayList<Question>()

    init {
        loadData()
    }

    private fun loadData() {
        questions.add(
            Question(
                R.drawable.flying1, R.drawable.flying2, R.drawable.flying3, R.drawable.flying4, "flying",
                "gwlankjizgqyfhtv"
            )
        )
        questions.add(
            Question(
                R.drawable.mother1, R.drawable.mother2, R.drawable.mother3, R.drawable.mother4, "mother",
                "ewlamkirzgyfhtvo"
            )
        )
        questions.add(
            Question(
                R.drawable.football1, R.drawable.football2, R.drawable.football3, R.drawable.football4, "football",
                "bwoankailgyfhtol"
            )
        )
        questions.add(
            Question(
                R.drawable.red1, R.drawable.red2, R.drawable.red3, R.drawable.red4, "red",
                "erlankjizgdyfhtv"
            )
        )
        questions.add(
            Question(
                R.drawable.silk1, R.drawable.silk2, R.drawable.silk3, R.drawable.silk4, "silk",
                "gwlsnkjizgqyfhtv"
            )
        )
        questions.add(
            Question(
                R.drawable.three1, R.drawable.three2, R.drawable.three3, R.drawable.three4, "three",
                "deoprkjizgilfhte"
            )
        )
        questions.add(
            Question(
                R.drawable.electric1, R.drawable.electric2, R.drawable.electric3, R.drawable.electric4, "electric",
                "ceoprkjizcilfhte"
            )
        )
        questions.add(
            Question(
                R.drawable.japan1, R.drawable.japan2, R.drawable.japan3, R.drawable.japan4, "japan",
                "cenprkjiacilfhta"
            )
        )
        questions.add(
            Question(
                R.drawable.silicon1, R.drawable.silicon2, R.drawable.silicon3, R.drawable.silicon4, "silicon",
                "ienkrojsacilfhta"
            )
        )
        questions.add(
            Question(
                R.drawable.white1, R.drawable.white2, R.drawable.white3, R.drawable.white4, "white",
                "iewkrojsacilmhta"
            )
        )

    }

    override fun getTests(): ArrayList<Question> {
        return questions
    }

    override fun getCoins(): Int {
        return MyPref.getInt("coins", 0)
    }

    override fun saveCoins(value: Int) {
        MyPref.saveInt("coins", value)
    }

    override fun getLevel(): Int {
        return MyPref.getInt("level", 1)
    }

    override fun saveLevel(value: Int) {
        MyPref.saveInt("level", value)
    }

    override fun removeLevel() {
        MyPref.remove("level")
    }


}