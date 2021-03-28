package kozlov.artyom.megafonstask.MVP

import android.widget.ImageView
import kozlov.artyom.megafonstask.recycler_view.RecyclerData

interface MainInterface {
    interface View{
        fun showWarning()
        fun closeWarning()
        fun initRecyclerView(list: ArrayList<RecyclerData>)
        fun submitNewElement(list: ArrayList<RecyclerData>)
    }

    interface Presenter{
        fun addNewElement()
        fun deleteLastElement()
        fun getOriginalData():ArrayList<RecyclerData>
        fun getAddNumber():Int

    }

    interface Model{
        fun createOriginalDataSet()
        fun getOriginalData():ArrayList<RecyclerData>
        fun generateRandomElement()
        fun getAddNumber():Int

    }
}