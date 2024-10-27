package lat.pam.utsproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // "Let's Order Some Foods!" button
        findViewById<Button>(R.id.btnOrderFoods).setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivity(intent)
        }

        // Logout button
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            // Clear only the loggedIn flag
            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("loggedIn", false).apply()

            // Navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // End HomeActivity
        }
    }
}
