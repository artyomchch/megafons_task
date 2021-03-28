package kozlov.artyom.megafonstask.MVP

import kozlov.artyom.megafonstask.MVP.MainInterface
import kozlov.artyom.megafonstask.recycler_view.NumberRecyclerAdapter
import kozlov.artyom.megafonstask.recycler_view.RecyclerData
import kotlin.random.Random

class MainModel: MainInterface.Model {

    private var originalDataSet: ArrayList<RecyclerData> = arrayListOf()
    private var remakeDataSet: ArrayList<RecyclerData> = arrayListOf()
    private var addNumber: Int = 0
    private lateinit var numberAdapter: NumberRecyclerAdapter

    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')


    override fun createOriginalDataSet() {
        var i = 0
        while (i != 10) {
            originalDataSet.add(RecyclerData(i, generateString()))
            i++
        }
    }

    override fun getOriginalData(): ArrayList<RecyclerData> = originalDataSet

    override fun generateRandomElement() {
        addNumber = (0..originalDataSet.size).random()
        remakeDataSet = originalDataSet
        originalDataSet.add(addNumber ,RecyclerData(addNumber, generateString()))

      //  numberAdapter.submitList(remakeDataSet)
    }

    override fun getAddNumber(): Int = addNumber




    private fun generateString():String{ // Произовальная строка
        return (1..75)
        .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }




}