package kozlov.artyom.megafonstask.MVP

import kozlov.artyom.megafonstask.recycler_view.RecyclerData
import kotlin.random.Random

class MainModel: MainInterface.Model {

    private var originalDataSet: ArrayList<RecyclerData> = arrayListOf()
    private var remakeDataSet: ArrayList<RecyclerData> = arrayListOf()
    private var addNumber: Int = 0

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
    }

    override fun getAddNumber(): Int = addNumber
    override fun deleteLastNumber() {
        originalDataSet.removeLast()
    }

    override fun setOriginalData(originalData: ArrayList<RecyclerData>) {
        originalDataSet = originalData
    }

    override fun getRemakeData(): ArrayList<RecyclerData> = remakeDataSet



    private fun generateString():String{ // Произовальная строка
        return (1..75)
        .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }




}