package uz.frodo.a4pic1word

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import uz.frodo.a4pic1word.data.MyPref
import uz.frodo.a4pic1word.mvp.MainActivity


class MenuActivity : AppCompatActivity() {
    private val contin:MaterialButton by lazy { findViewById(R.id.bt_continue) }
    private val play:MaterialButton by lazy { findViewById(R.id.bt_play) }
    private val exit:MaterialButton by lazy { findViewById(R.id.bt_exit) }
    private val info:MaterialButton by lazy { findViewById(R.id.bt_info) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        contin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        play.setOnClickListener {
            MyPref.remove("level")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        exit.setOnClickListener {
            finish()
        }
        info.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        findViewById<TextView>(R.id.tv_coinsMenu).text = MyPref.getInt("coins",0).toString()

        println("MenuActiviy ${MyPref.getInt("level",-1)}")
        if (MyPref.getInt("level",-1) != -1){
            contin.visibility = View.VISIBLE
        }else
            contin.visibility = View.GONE
    }
}