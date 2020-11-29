package vukan.com.jmbg_validator.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vukan.com.jmbg_validator.R
import vukan.com.jmbg_validator.data.JmbgParser
import vukan.com.jmbg_validator.data.Result
import vukan.com.jmbg_validator.data.model.JmbgData

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private val _result = MutableLiveData<vukan.com.jmbg_validator.ui.result.Result>()
    val result: LiveData<vukan.com.jmbg_validator.ui.result.Result> = _result

    fun parseJMBG(jmbg: String) {
        val result = JmbgParser.parseJMBG(jmbg, getApplication<Application>().applicationContext)

        if (result is Result.Success)
            _result.value = Result(
                success = JmbgData(
                    country = result.data.country,
                    region = result.data.region,
                    gender = result.data.gender,
                    ordinalNumberOfBirth = result.data.ordinalNumberOfBirth,
                    dateOfBirth = result.data.dateOfBirth,
                    dayOfBirth = result.data.dayOfBirth
                )
            )
        else _result.value = Result(error = R.string.error)
    }
}