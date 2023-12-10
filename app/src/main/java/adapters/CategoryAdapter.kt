package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.zd_2_2_shaidullin.R
import types.CategoryType

class CategoryAdapter(private val categoriesList: ArrayList<CategoryType>):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.category_item,
            parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val currentItem = categoriesList[position]
        holder.image.setImageURI(currentItem.image.toUri())
        holder.title.text = currentItem.title

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }


    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.category_image)
        val title: TextView = itemView.findViewById(R.id.category_title)
    }
}