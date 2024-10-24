package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val etFirstname: EditText = findViewById(R.id.etFirstname)
        val etLastname: EditText = findViewById(R.id.etLastname)
        val etRegUsername: EditText = findViewById(R.id.etRegUsername)
        val etRegPassword: EditText = findViewById(R.id.etRegPassword)
        val etConfirmPassword: EditText = findViewById(R.id.etConfirmPassword)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val firstname = etFirstname.text.toString()
            val lastname = etLastname.text.toString()
            val username = etRegUsername.text.toString()
            val password = etRegPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password == confirmPassword) {
                // Simpan data ke SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman login (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
