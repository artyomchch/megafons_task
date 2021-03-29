package kozlov.artyom.megafonstask.MVP

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kozlov.artyom.megafonstask.R
import kozlov.artyom.megafonstask.recycler_view.CardDiffUtilCallback
import kozlov.artyom.megafonstask.recycler_view.NumberRecyclerAdapter
import kozlov.artyom.megafonstask.recycler_view.RecyclerData
import kozlov.artyom.megafonstask.recycler_view.TopSpacingItemDecoration

class MainActivity : AppCompatActivity(), MainInterface.View {
    private var presenter: MainPresenter? = null
    private lateinit var numberAdapter: NumberRecyclerAdapter
    private var savedRecyclerLayoutState: Parcelable? = null  // переход к элементу после поврота
    private val BUNDLE_RECYCLER_LAYOUT = "recycler_layout"  // ключ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab : FloatingActionButton = findViewById(R.id.fab_add_item)
        presenter = MainPresenter(this)

        fab.setOnClickListener {
            presenter!!.addNewElement()
            numberAdapter.notifyItemInserted(presenter!!.getAddNumber())
        }

    }




    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val gson = Gson()
        val json = gson.toJson(presenter!!.getOriginalData())
        outState.putString("original_data", json)


        outState.putParcelable(
            BUNDLE_RECYCLER_LAYOUT,
            recycler_view.layoutManager?.onSaveInstanceState() // запоминание местоположение
        )




        Log.d("check", "onSaveInstanceState: ")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("check", "onRestoreInstanceState: ")
        savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT) // вотсановление местоположения

        val json = savedInstanceState.getString("original_data")
        val typeToken = object : TypeToken<ArrayList<RecyclerData>>() {}.type

        presenter!!.setOriginalData(Gson().fromJson(json, typeToken))




        super.onRestoreInstanceState(savedInstanceState)

    }

    override fun onDestroy() {
        presenter!!.stopRepeating()
        super.onDestroy()
    }

    override fun showWarning() {
        imageViewWarning.visibility = View.VISIBLE
        text_warning.visibility = View.VISIBLE
    }

    override fun closeWarning() {
        imageViewWarning.visibility = View.GONE
        text_warning.visibility = View.GONE
    }
    override fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            //расположение элементов в зависимоти от поворота экрана
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ){
                recycler_view.layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
            else{
                recycler_view.layoutManager = GridLayoutManager(this@MainActivity, 4)
            }
            // оступы от элементов
            val topSpacingItemDecoration = TopSpacingItemDecoration(15)
            addItemDecoration(topSpacingItemDecoration)
            Log.d("test", "initRecyclerView: ")

            numberAdapter = NumberRecyclerAdapter()
            adapter = numberAdapter
        }

    }



    override fun asyncDeleteLastElement(index: Int) {
        numberAdapter.notifyItemRemoved(index)
    }

    override fun submitList(list: ArrayList<RecyclerData>) {
        numberAdapter.submitList(list)
    }


}