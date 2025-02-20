package uz.frodo.a4pic1word.mvp

import uz.frodo.a4pic1word.data.Question

class MainPresenter(val view:MainContract.View,val model: MainContract.Model):MainContract.Presenter {
    private var coins = model.getCoins()
    private var level = model.getLevel()
    private var currentIndex = level-1
    private lateinit var question:Question
    private lateinit var answer:Array<String>
    private lateinit var variant :Array<String>
    private var finish = false

    init {
        loadData()
    }

    private fun loadData(){
        question = model.getQuestion(currentIndex++)
        view.setImages(question.question1,question.question2,question.question3,question.question4)
        view.setAnswer(question.answer)
        view.setVariant(question.variant)

        view.setCoins(coins)
        view.setLevel(level)

        answer = Array(question.answer.length) { "" }
        variant = Array(16) { "" }
    }

    override fun onAnswerClick(index: Int, text: String) {
        view.hideAnswer(index)
        answer[index] = ""

        val hiddenIndex = findHiddenIndex(text)
        view.showVariant(hiddenIndex)
        variant[hiddenIndex] = ""

    }

    private fun findHiddenIndex(text: String): Int {
        variant.forEachIndexed { index, s ->
            if(text == s) return index
        }
        return -1
    }

    override fun onVariantClick(index: Int, text: String) {
        val answerIndex = findEmptyAnswerIndex()
        if (answerIndex == -1) return

        view.hideVariant(index)
        variant[index] = text

        view.showAnswer(answerIndex,text)
        answer[answerIndex] = text

        if(isWin()){
            coins += 5
            level++
            if(currentIndex == model.getQuestionCount()){
                view.showFinalDialog()
            }else
                view.showdialog(question.answer)
        }
    }

    private fun isWin(): Boolean {
        if (answer[answer.size-1].isEmpty()) return false
        return answer.joinToString("") == question.answer
    }
    private fun findEmptyAnswerIndex():Int{
        answer.forEachIndexed { index, s ->
            if(answer[index].isEmpty()){
                return index
            }
        }
        return -1
    }

    override fun onEraseClick() {
        if (coins >= 5){
            val arr = question.variant.toCharArray()
            arr.forEachIndexed { index, c ->
                if(!question.answer.contains(c) && !variant.contains(c.toString())){
                    view.hideVariant(index)
                    variant[index] = c.toString()
                    coins -= 5
                    view.setCoins(coins)
                    return
                }
            }
        }else
            view.showToast("Not enough coins")
    }

    override fun onWriteClick() {
        if (answer[answer.size-1].isNotEmpty()) return
        if (coins >= 10){
            val answerIndex = findEmptyAnswerIndex()
            val letter = question.answer[answerIndex].toString()
            view.showCoinAnswer(answerIndex,letter)
            answer[answerIndex] = letter

            view.hideVariant(question.variant.indexOf(letter))

            coins -= 10
            view.setCoins(coins)

            if(isWin()){
                coins += 5
                level++
                if(currentIndex == model.getQuestionCount()){
                    view.showFinalDialog()
                }else
                    view.showdialog(question.answer)
            }

        }else
            view.showToast("Not enough coins")
    }


    override fun onNextClick() {
        loadData()
    }

    override fun onQuitClick() {
        view.finishActivity()
    }

    override fun onHomeClick() {
        model.saveCoins(coins)
        println("removing level on Home Pressed")
        model.removeLevel()
        view.finishActivity()
        finish = true
    }

    override fun onRetryClick() {
        currentIndex = 0
        level = 1
        loadData()
    }

    override fun onPauseActivity() {
        println("onPauseActivity finish:$finish")
        if(!finish){
            model.saveLevel(level)
        }
        model.saveCoins(coins)
    }

    override fun onImageClick(number: Int) {
        when(number){
            1-> view.openImageDialog(question.question1)
            2-> view.openImageDialog(question.question2)
            3-> view.openImageDialog(question.question3)
            4-> view.openImageDialog(question.question4)
        }
    }
}