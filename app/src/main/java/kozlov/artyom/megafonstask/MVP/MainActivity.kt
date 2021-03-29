package kozlov.artyom.megafonstask.MVP

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kozlov.artyom.megafonstask.R
import kozlov.artyom.megafonstask.recycler_view.NumberRecyclerAdapter
import kozlov.artyom.megafonstask.recycler_view.RecyclerData
import kozlov.artyom.megafonstask.recycler_view.TopSpacingItemDecoration

class MainActivity : AppCompatActivity(), MainInterface.View {
    private var presenter: MainPresenter? = null
    private lateinit var numberAdapter: NumberRecyclerAdapter
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

    override fun showWarning() {
        imageViewWarning.visibility = View.VISIBLE
        text_warning.visibility = View.VISIBLE
    }

    override fun closeWarning() {
        imageViewWarning.visibility = View.GONE
        text_warning.visibility = View.GONE
    }
    override fun initRecyclerView(list: ArrayList<RecyclerData>) {
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

            numberAdapter = NumberRecyclerAdapter()
            adapter = numberAdapter
        }

    }

    override fun submitNewElement(list: ArrayList<RecyclerData>) {
        numberAdapter.submitList(list)
    }

    override fun asyncDeleteLastElement(index: Int) {
        numberAdapter.notifyDataSetChanged()
        numberAdapter.notifyItemInserted(index)
    }


}