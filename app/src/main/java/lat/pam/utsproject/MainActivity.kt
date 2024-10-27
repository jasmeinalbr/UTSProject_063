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

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Check if user is already registered
        val isLoggedIn = sharedPreferences.getBoolean("loggedIn", false)
        val registeredUsername = sharedPreferences.getString("username", null)
        val registeredPassword = sharedPreferences.getString("password", null)

        if (isLoggedIn && registeredUsername != null && registeredPassword != null) {
            navigateToHome() // Go directly to HomeActivity if already logged in
            return
        }

        // Referencing UI elements
        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        // Redirect to RegisterActivity if user clicks "Register"
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Login logic
        loginButton.setOnClickListener {
            val inputUsername = username.text.toString()
            val inputPassword = password.text.toString()

            // Check if username and password match with saved data
            if (inputUsername == registeredUsername && inputPassword == registeredPassword) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Set loggedIn flag to true
                sharedPreferences.edit().putBoolean("loggedIn", true).apply()

                navigateToHome() // Go to Home
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Set padding to account for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
