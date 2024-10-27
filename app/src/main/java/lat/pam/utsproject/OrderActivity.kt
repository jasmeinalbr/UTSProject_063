package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Guest") // Default to "Guest" if not found

        // Retrieve data from Intent
        val foodName = intent.getStringExtra("food_name") ?: "N/A"
        val foodPrice = intent.getIntExtra("food_price", 0)

        // Get references to UI elements
        val foodNameTextView: TextView = findViewById(R.id.foodNameTextView)
        val foodPriceTextView: TextView = findViewById(R.id.foodPriceTextView)
        val orderingNameTextView: TextView = findViewById(R.id.orderingNameTextView)
        val etServings: EditText = findViewById(R.id.etServings)
        val etNotes: EditText = findViewById(R.id.etNotes)
        val totalPriceTextView: TextView = findViewById(R.id.totalPriceTextView)
        val btnOrder: Button = findViewById(R.id.btnOrder)

        // Set food name, price, and ordering name
        foodNameTextView.text = foodName
        foodPriceTextView.text = "Price: Rp. $foodPrice"
        orderingNameTextView.text = "Ordering Name: $username" // Display the ordering name

        // Calculate total price based on number of servings
        etServings.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val servings = s.toString().toIntOrNull() ?: 0
                val totalPrice = foodPrice * servings
                totalPriceTextView.text = "Total Price: Rp. $totalPrice"
            }
        })

        // On Order button click, pass data to ConfirmationActivity
        btnOrder.setOnClickListener {
            val servings = etServings.text.toString()
            val notes = etNotes.text.toString()
            val totalPrice = totalPriceTextView.text.toString()

            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("food_name", foodName)
                putExtra("food_price", foodPrice)
                putExtra("ordering_name", username) // Pass ordering name
                putExtra("servings", servings)
                putExtra("notes", notes)
                putExtra("total_price", totalPrice)
            }
            startActivity(intent)
        }

        // Set padding to account for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
