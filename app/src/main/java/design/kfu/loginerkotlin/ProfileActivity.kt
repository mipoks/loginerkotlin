package design.kfu.loginerkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private var btnLogout: Button? = null
    private var imageView: ImageView? = null
    private var emailText: TextView? = null
    private var nsText: TextView? = null
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val intent = Intent(this, MainActivity::class.java)

        preferences = getSharedPreferences("Cookie", Context.MODE_PRIVATE)
        editor = preferences!!.edit()

        btnLogout = findViewById(R.id.button)
        imageView = findViewById(R.id.imageView)
        emailText = findViewById(R.id.textView)
        nsText = findViewById(R.id.textView2)

        btnLogout!!.setOnClickListener { v ->
            editor!!.clear()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor!!.apply()
            } else
                editor?.commit()
            startActivity(intent)
        }

        //Toast.makeText(applicationContext, preferences!!.getInt("image", 0).toString(), Toast.LENGTH_SHORT).show()
        imageView!!.setImageDrawable(getDrawable(preferences!!.getInt("image", 0)))
        emailText!!.text = preferences!!.getString("email", "")
        val info =
            preferences!!.getString("user", "") + " " + preferences!!.getString("surname", "")
        nsText!!.text = info
    }
}
