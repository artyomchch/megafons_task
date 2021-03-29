package kozlov.artyom.megafonstask.recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_number_list_item.view.*
import kozlov.artyom.megafonstask.R


class NumberRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<RecyclerData> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NumberViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_number_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { // отобраджения
        when(holder){
            is NumberViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(numberList: List<RecyclerData>){ // подтверждения списка
        items = numberList
        differ.submitList(numberList)

    }


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecyclerData>() {


        override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return true

        }

        override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return true
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK) // добавление элементов асинхронно





   inner class NumberViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView)

    {
        val numberText = itemView.description
        val position = itemView.position
        fun bind(recyclerData: RecyclerData){
            numberText.text = recyclerData.text
            position.text = recyclerData.number.toString()

        }




    }




}

