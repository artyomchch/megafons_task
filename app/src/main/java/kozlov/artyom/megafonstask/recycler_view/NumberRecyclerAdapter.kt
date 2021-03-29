package kozlov.artyom.megafonstask.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_number_list_item.view.*
import kozlov.artyom.megafonstask.R


class NumberRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecyclerData>() {


        override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return oldItem.number == newItem.number

        }

        override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK) // добавление элементов асинхронно




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NumberViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_number_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { // отображение
        when(holder){
            is NumberViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    fun submitList(numberList: List<RecyclerData>){ // подтверждения списка
        differ.submitList(numberList)
    }







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

