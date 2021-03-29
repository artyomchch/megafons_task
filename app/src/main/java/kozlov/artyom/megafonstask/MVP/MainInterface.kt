package kozlov.artyom.megafonstask.MVP

import android.widget.ImageView
import kozlov.artyom.megafonstask.recycler_view.RecyclerData

interface MainInterface {
    interface View{
        fun showWarning()
        fun closeWarning()
        fun initRecyclerView(list: ArrayList<RecyclerData>)
        fun submitNewElement(list: ArrayList<RecyclerData>)
        fun asyncDeleteLastElement(index: Int)
    }

    interface Presenter{
        fun addNewElement()
        fun getOriginalData():ArrayList<RecyclerData>
        fun getAddNumber():Int


    }

    interface Model{
        fun createOriginalDataSet()
        fun getOriginalData():ArrayList<RecyclerData>
        fun generateRandomElement()
        fun getAddNumber():Int
        fun deleteLastNumber()

    }
}