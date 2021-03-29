package kozlov.artyom.megafonstask.MVP

import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
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

            Log.d("test", "run: 1 sec")
        }

    }
    init {

        model.createOriginalDataSet()
        view.initRecyclerView(model.getOriginalData())
        view.submitNewElement(model.getOriginalData())
        view.closeWarning()
        startRepeating()


    }



    override fun addNewElement() {
        if (model.getOriginalData().isNotEmpty()){
            model.generateRandomElement()
            view.submitNewElement(model.getOriginalData())
        }
        else{
            timeCheck = false
            view.closeWarning()
            model.generateRandomElement()
            view.submitNewElement(model.getOriginalData())
            startRepeating()
        }


    }



    override fun getOriginalData(): ArrayList<RecyclerData> = model.getOriginalData()
    override fun getAddNumber(): Int = model.getAddNumber()





    private fun stopRepeating(){
        mHandler.removeCallbacks(mToastRunnable)
    }

    private fun startRepeating(){
        mToastRunnable.run()

    }

    private fun asyncDeleteLastNumber() = runBlocking{  // асинхронная работа

        GlobalScope.launch {
            if (model.getOriginalData().isNotEmpty()){

                returnDataOnMainThread(model.getOriginalData().size)// забираем посл. число
                model.deleteLastNumber()

            }
            else{
                stopRepeating()
                returnWarningView()
            }

        }

    }

    suspend fun returnDataOnMainThread(index: Int){ // переход в главный поток
        return withContext(Dispatchers.Main){
            Log.d("Thread", "from thread ${Thread.currentThread().name}")
            view.asyncDeleteLastElement(index)
        }
    }

    suspend fun returnWarningView(){ // переход в главный поток
        return withContext(Dispatchers.Main){
            Log.d("Thread", "from thread ${Thread.currentThread().name}")
            view.showWarning()
        }
    }


}