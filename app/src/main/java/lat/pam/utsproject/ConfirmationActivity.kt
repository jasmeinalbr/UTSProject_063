package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Retrieve data from Intent
        val foodName = intent.getStringExtra("food_name")
        val foodPrice = intent.getIntExtra("food_price", 0)
        val orderingName = intent.getStringExtra("ordering_name")
        val servings = intent.getStringExtra("servings")
        val notes = intent.getStringExtra("notes")
        val totalPrice = intent.getStringExtra("total_price")

        // Get references to UI elements and set data
        findViewById<TextView>(R.id.tvFoodName).text = "Food Name: $foodName"
        findViewById<TextView>(R.id.tvFoodPrice).text = "Price: Rp. $foodPrice"
        findViewById<TextView>(R.id.tvOrderingName).text = "Ordering Name: $orderingName"
        findViewById<TextView>(R.id.tvServings).text = "Number of Servings: $servings"
        findViewById<TextView>(R.id.tvNotes).text = "Additional Notes: $notes"
        findViewById<TextView>(R.id.tvTotalPrice).text = totalPrice

        val btnBackToMenu: Button = findViewById(R.id.backtoMenu)

        btnBackToMenu.setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)

            startActivity(intent)
        }
    }
}
