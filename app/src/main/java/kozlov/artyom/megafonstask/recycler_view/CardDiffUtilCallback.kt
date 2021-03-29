package kozlov.artyom.megafonstask.recycler_view

import androidx.recyclerview.widget.DiffUtil

class CardDiffUtilCallback(_oldList: ArrayList<RecyclerData>, _newList: ArrayList<RecyclerData>) :
    DiffUtil.Callback() {

    private var oldList: ArrayList<RecyclerData>? = _oldList
    private var newList: ArrayList<RecyclerData>? = _newList


    override fun getOldListSize(): Int {
        return oldList!!.size
    }

    override fun getNewListSize(): Int {
       return newList!!.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCard: RecyclerData = oldList!![oldItemPosition]
        val newCard: RecyclerData = newList!![newItemPosition]
        return oldCard.number == newCard.number
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCard: RecyclerData = oldList!![oldItemPosition]
        val newCard: RecyclerData = newList!![newItemPosition]
        return (oldCard.text == newCard.text)
    }
}