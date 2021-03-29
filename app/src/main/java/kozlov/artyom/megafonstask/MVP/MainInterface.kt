package kozlov.artyom.megafonstask.MVP

import android.widget.ImageView
import kozlov.artyom.megafonstask.recycler_view.RecyclerData

interface MainInterface {
    interface View{
        fun showWarning()
        fun closeWarning()
        fun initRecyclerView()
        fun asyncDeleteLastElement(index: Int)
        fun submitList(list: ArrayList<RecyclerData>)
    }

    interface Presenter{
        fun addNewElement()
        fun getOriginalData():ArrayList<RecyclerData>
        fun getAddNumber():Int
        fun setOriginalData(originalData: ArrayList<RecyclerData>)
        fun stopRepeating()

    }

    interface Model{
        fun createOriginalDataSet()
        fun getOriginalData():ArrayList<RecyclerData>
        fun generateRandomElement()
        fun getAddNumber():Int
        fun deleteLastNumber()
        fun setOriginalData(originalData: ArrayList<RecyclerData>)

    }
}