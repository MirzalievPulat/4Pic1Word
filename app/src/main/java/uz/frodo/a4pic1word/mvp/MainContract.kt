package uz.frodo.a4pic1word.mvp

import uz.frodo.a4pic1word.data.Question

interface MainContract {
    interface View{
        fun setVariant(variant: String)
        fun setAnswer(answer:String)
        fun setImages(image1:Int,image2:Int,image3:Int,image4:Int)

        fun showAnswer(index:Int,text:String)
        fun showCoinAnswer(index: Int,text: String)
        fun hideAnswer(index:Int)
        fun showVariant(index: Int)
        fun hideVariant(index: Int)

        fun setCoins(coins:Int)
        fun setLevel(level:Int)

        fun showdialog(answer: String)
        fun showToast(message:String)
        fun showFinalDialog()

        fun finishActivity()
        fun openImageDialog(image:Int)

    }
    interface Model{
        fun getQuestion(index: Int):Question
        fun getQuestionCount():Int
        fun getCoins():Int
        fun saveCoins(value:Int)
        fun getLevel():Int
        fun saveLevel(value: Int)
        fun removeLevel()
    }
    interface Presenter{
        fun onAnswerClick(index:Int, text: String)
        fun onVariantClick(index:Int, text: String)

        fun onEraseClick()
        fun onWriteClick()

        fun onNextClick()
        fun onQuitClick()

        fun onHomeClick()
        fun onRetryClick()

        fun onPauseActivity()
        fun onImageClick(number:Int)
    }
}