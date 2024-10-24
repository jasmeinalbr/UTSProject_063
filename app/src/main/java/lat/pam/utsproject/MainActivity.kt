package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Referensi ke Edittext dan Button
        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        // Ketika klik "Register", pindah ke RegisterActivity
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Logika Login
        loginButton.setOnClickListener {
            val inputUsername = username.text.toString()
            val inputPassword = password.text.toString()

            // Ambil data dari SharedPreferences
            val registeredUsername = sharedPreferences.getString("username", null)
            val registeredPassword = sharedPreferences.getString("password", null)

            // Cek apakah username dan password cocok
            if (inputUsername == registeredUsername && inputPassword == registeredPassword) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman List Food atau halaman lainnya setelah login
                val intent = Intent(this, ListFoodActivity::class.java) // Pindah ke ListFoodActivity
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
