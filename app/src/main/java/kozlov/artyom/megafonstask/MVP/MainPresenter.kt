package kozlov.artyom.megafonstask.MVP

import kozlov.artyom.megafonstask.recycler_view.RecyclerData


class MainPresenter(_view: MainInterface.View): MainInterface.Presenter {
    private var view: MainInterface.View = _view
    private var model: MainInterface.Model = MainModel()

    init {
        model.createOriginalDataSet()
        view.initRecyclerView(model.getOriginalData())
        view.submitNewElement(model.getOriginalData())
        view.closeWarning()

    }



    override fun addNewElement() {
        model.generateRandomElement()
        view.submitNewElement(model.getOriginalData())
    }

    override fun deleteLastElement() {

    }

    override fun getOriginalData(): ArrayList<RecyclerData> = model.getOriginalData()
    override fun getAddNumber(): Int = model.getAddNumber()




}