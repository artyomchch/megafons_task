package kozlov.artyom.megafonstask.MVP


import io.github.serpro69.kfaker.Faker

import io.github.serpro69.kfaker.FakerConfig
import io.github.serpro69.kfaker.create
import kozlov.artyom.megafonstask.recycler_view.RecyclerData


class MainModel: MainInterface.Model {

    private var originalDataSet: ArrayList<RecyclerData> = arrayListOf()
    private var remakeDataSet: ArrayList<RecyclerData> = arrayListOf()
    private var addNumber: Int = 0
    private val faker = Faker()





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



    private fun generateString():String{
        return faker.company.name()
    }







}