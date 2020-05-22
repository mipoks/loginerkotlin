package design.kfu.loginerkotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignIn: Button? = findViewById(R.id.signInButton)
        val emailText: TextView? = findViewById(R.id.emailText)
        val passwordText: TextView? = findViewById(R.id.passwordText)
        var people = mutableListOf(
            Person(
                "Alexander",
                "Vainer",
                "talex@mail.ru",
                "taA2lexmailru".hashCode(), //md5 or sha-1 is better here instead of hashcode()
                R.drawable.prsn1
            ),
            Person(
                "Dima",
                "Valeev",
                "valeev@mail.ru",
                "ValeevDima2".hashCode(),
                R.drawable.prsn2
            ),
            Person(
                "Daniyar",
                "Zakiev",
                "d_zakiev@mail.ru",
                "nonpwdA1".hashCode(),
                R.drawable.prsn3
            ),
            Person(
                "Deiteriy",
                "Mendeliy",
                "dmendiy@mail.ru",
                "teTsws1".hashCode(),
                R.drawable.prsn3
            )
        )

        preferences = getSharedPreferences("Cookie", MODE_PRIVATE)
        val editor = preferences.edit()
        var auth = false

        fun isCorrectData(email: String, pwd: String): Boolean {
            if (pwd.length >= 6 && pwd.matches("(.*)[a-z](.*)".toRegex()) &&
                pwd.matches("(.*)[A-Z](.*)".toRegex()) && pwd.matches("(.*)[0-9](.*)".toRegex()) &&
                email.length >= 6 && email.contains("@")
            ) {
                return true
            } else
                return false
        }

        val intent = Intent(this, ProfileActivity::class.java)

        if (preferences.contains("user")) {
            startActivity(intent)
        }

        btnSignIn?.setOnClickListener { v ->
            val email = emailText!!.getText().toString().toLowerCase()
            val pwd = passwordText!!.getText().toString()
            val hash = pwd.hashCode()

            if (isCorrectData(email, pwd)) {
                for (p in people) {
                    if (p.getlogHash()?.containsKey(email) == true && p.getlogHash()?.get(email)!!.equals(
                            hash
                        )
                    ) {
                        auth = true
                        editor?.putString("email", email)
                        editor?.putString("user", p.getName())
                        editor?.putString("surname", p.getSurname())
                        editor?.putInt("image", p.getDrawable())
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                            editor?.apply()
                        } else
                            editor?.commit()
                        startActivity(intent)
                        break
                    }
                }
            }
            if (!auth) {
                Toast.makeText(
                    applicationContext,
                    "Incorrect email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}