package lat.pam.utsproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FoodAdapter(private val foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodName.text = food.name
        holder.foodDescription.text = food.description
        holder.foodImage.setImageResource(food.imageResourceId)
        holder.foodPrice.text = "Rp. ${food.price}"

        // Set OnClickListener for the buy button
        holder.buyButton.setOnClickListener {
            // Create an intent to start OrderActivity
            val context = holder.itemView.context
            val intent = Intent(context, OrderActivity::class.java)
            // Pass any extra information if needed
            intent.putExtra("food_name", food.name)
            intent.putExtra("food_price", food.price)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val buyButton: ImageButton = itemView.findViewById(R.id.buyButton)
    }
}