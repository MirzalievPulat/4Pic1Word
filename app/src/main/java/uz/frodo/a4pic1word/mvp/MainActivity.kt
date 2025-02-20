package uz.frodo.a4pic1word.mvp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import uz.frodo.a4pic1word.R

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var tv_level: TextView
    private lateinit var tv_coins: TextView
    private lateinit var image1: ShapeableImageView
    private lateinit var image2: ShapeableImageView
    private lateinit var image3: ShapeableImageView
    private lateinit var image4: ShapeableImageView
    private val answerButtons = ArrayList<MaterialButton>()
    private val variantButtons = ArrayList<MaterialButton>()
    private lateinit var bt_eraiser: CardView
    private lateinit var bt_writer: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadViews()
        val model: MainContract.Model = MainModel()
        presenter = MainPresenter(this, model)

    }

    private fun loadViews() {
        tv_level = findViewById(R.id.tv_level)
        tv_coins = findViewById(R.id.tv_coins)

        bt_eraiser = findViewById(R.id.eraiseCard)
        bt_writer = findViewById(R.id.writeCard)
        bt_eraiser.setOnClickListener {
            presenter.onEraseClick()
        }
        bt_writer.setOnClickListener {
            presenter.onWriteClick()
        }


        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)
        image4 = findViewById(R.id.image4)

        image1.setOnClickListener {
            presenter.onImageClick(1)
        }
        image2.setOnClickListener {
            presenter.onImageClick(2)
        }
        image3.setOnClickListener {
            presenter.onImageClick(3)
        }
        image4.setOnClickListener {
            presenter.onImageClick(4)
        }


        findViewById<ViewGroup>(R.id.answer_group).forEachIndexed { index, view ->
            val button = view as MaterialButton
            button.text = ""
            answerButtons.add(button)
            button.setOnClickListener { presenter.onAnswerClick(index, button.text.toString()) }
        }

        findViewById<ViewGroup>(R.id.variant1_group).forEachIndexed { index, view ->
            val button = view as MaterialButton
            variantButtons.add(button)
            button.setOnClickListener { presenter.onVariantClick(index, button.text.toString()) }
        }

        findViewById<ViewGroup>(R.id.variant2_group)
            .forEach {
            val button = it as MaterialButton
            variantButtons.add(button)
            button.setOnClickListener { presenter.onVariantClick(variantButtons.indexOf(button), button.text.toString()) }
        }
    }

    override fun setVariant(variant: String) {
        variant.forEachIndexed { index, c ->
            variantButtons[index].apply {
                text = c.toString()
                visibility = View.VISIBLE
            }
        }
    }

    override fun setAnswer(answer: String) {
        answerButtons.forEachIndexed { index, materialButton ->
            answerButtons[index].isVisible = index < answer.length
            answerButtons[index].text = ""
            answerButtons[index].isClickable = true
            answerButtons[index].setBackgroundColor(getColor(R.color.answer_color))
        }
    }

    override fun setImages(image1: Int, image2: Int, image3: Int, image4: Int) {
        this.image1.setImageResource(image1)
        this.image2.setImageResource(image2)
        this.image3.setImageResource(image3)
        this.image4.setImageResource(image4)
    }

    override fun showAnswer(index: Int, text: String) {
        answerButtons[index].text = text
    }

    override fun showCoinAnswer(index: Int, text: String) {
        answerButtons[index].text = text
        answerButtons[index].setBackgroundColor(Color.GREEN)
        answerButtons[index].isClickable = false
    }

    override fun hideAnswer(index: Int) {
        answerButtons[index].text = ""
    }

    override fun showVariant(index: Int) {
        variantButtons[index].isVisible = true
    }

    override fun hideVariant(index: Int) {
        variantButtons[index].isInvisible = true
    }

    override fun setCoins(coins: Int) {
        tv_coins.text = coins.toString()
    }

    override fun setLevel(level: Int) {
        tv_level.text = "Level " + level
    }

    override fun showdialog(answer: String) {
        val dialog = AlertDialog.Builder(this).create()

        val view = LayoutInflater.from(this).inflate(R.layout.win_dialog, null, false)
        val bt_next = view.findViewById<CardView>(R.id.dialog_next)
        val bt_quit = view.findViewById<CardView>(R.id.dialog_quit)
        val dialog_answer = view.findViewById<TextView>(R.id.answer_dialog)
        dialog_answer.text = answer.toCharArray().joinToString(" ").uppercase()
        bt_next.setOnClickListener { presenter.onNextClick();dialog.dismiss() }
        bt_quit.setOnClickListener { presenter.onQuitClick() }


        dialog.setView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showFinalDialog() {
        val dialog = AlertDialog.Builder(this).create()

        val view = LayoutInflater.from(this).inflate(R.layout.finish_dialog, null, false)
        val bt_home = view.findViewById<CardView>(R.id.dialog_home)
        val bt_retry = view.findViewById<CardView>(R.id.dialog_retry)

        bt_home.setOnClickListener { presenter.onHomeClick();dialog.dismiss() }
        bt_retry.setOnClickListener { presenter.onRetryClick();dialog.dismiss() }

        dialog.setView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    override fun finishActivity() {
        finish()
    }

    override fun openImageDialog(i: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_image,null,false)
        val image = view.findViewById<ImageView>(R.id.dialog_image)
        image.setImageResource(i)

        image.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setView(view)
        dialog.show()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPauseActivity()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        println("onBackPressed")
        presenter.onPauseActivity()
    }
}