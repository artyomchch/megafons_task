package kozlov.artyom.megafonstask.MVP

import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.*
import kozlov.artyom.megafonstask.recycler_view.CardDiffUtilCallback
import kozlov.artyom.megafonstask.recycler_view.NumberRecyclerAdapter
import kozlov.artyom.megafonstask.recycler_view.RecyclerData
import java.lang.Runnable


class MainPresenter(_view: MainInterface.View): MainInterface.Presenter {
    private var view: MainInterface.View = _view
    private var model: MainInterface.Model = MainModel()
    private var timeCheck = false

    private val mHandler = Handler() // для задержки времени
    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            if (!timeCheck){
                mHandler.postDelayed(this, 1000)
                timeCheck = true
            }
            else{
                mHandler.postDelayed(this, 1000)
                asyncDeleteLastNumber()

            }

        }

    }

    init {

        model.createOriginalDataSet()
        view.initRecyclerView()
        view.submitList(model.getOriginalData())
        view.closeWarning()
        startRepeating()

    }





    override fun addNewElement() {
        if (model.getOriginalData().isNotEmpty()){
            model.generateRandomElement()

        }
        else{
            timeCheck = false
            view.closeWarning()
            model.generateRandomElement()
            startRepeating()
        }


    }



    override fun getOriginalData(): ArrayList<RecyclerData> = model.getOriginalData()
    override fun getRemakeData(): ArrayList<RecyclerData> = model.getRemakeData()



    override fun getAddNumber(): Int = model.getAddNumber()
    override fun setOriginalData(originalData: ArrayList<RecyclerData>) {
        model.setOriginalData(originalData)
        view.submitList(model.getOriginalData())
        if (model.getOriginalData().isEmpty()){
            view.showWarning()
            stopRepeating()
        }

    }

    override fun stopRepeating() {
        mHandler.removeCallbacks(mToastRunnable)
    }

    override fun diffUtilAdd() {

    }


    private fun startRepeating(){
        mToastRunnable.run()

    }

    private fun asyncDeleteLastNumber() = runBlocking{  // асинхронная работа

        GlobalScope.launch {
            if (model.getOriginalData().isNotEmpty()){
                returnDataOnMainThread(model.getOriginalData().size)// забираем посл. число
                model.deleteLastNumber()
                if (model.getOriginalData().size == 0){
                    returnWarningView()

                }
            }
        }

    }

    suspend fun returnDataOnMainThread(index: Int){ // переход в главный поток
        return withContext(Dispatchers.Main){
            Log.d("Thread", "from thread ${Thread.currentThread().name}")
            Log.d("Thread", "1 sec")
            view.asyncDeleteLastElement(index)
        }
    }

    suspend fun returnWarningView(){ // переход в главный поток
        return withContext(Dispatchers.Main){
            stopRepeating()
            view.showWarning()
        }
    }


}