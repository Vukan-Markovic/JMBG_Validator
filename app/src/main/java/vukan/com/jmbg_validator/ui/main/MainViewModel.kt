package vukan.com.jmbg_validator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.jmbg_validator.R
import vukan.com.jmbg_validator.data.JmbgParser


class MainViewModel : ViewModel() {

    private val _form = MutableLiveData<FormState>()
    val formState: LiveData<FormState> = _form
    private var nameError: Int? = 0
    private var surnameError: Int? = 0
    private var jmbgError: Int? = 0

    fun checkName(name: String) {
        if (name.isBlank()) {
            nameError = R.string.invalid_name
            setFormState()
        } else {
            nameError = null
            setFormState()
            isDataValid()
        }
    }

    fun checkSurname(surname: String) {
        if (surname.isBlank()) {
            surnameError = R.string.invalid_surname
            setFormState()
        } else {
            surnameError = null
            setFormState()
            isDataValid()
        }
    }

    fun checkJMBG(jmbg: String) {
        jmbgError = JmbgParser.isJMBGValid(jmbg)
        setFormState()
        if (jmbgError == null) isDataValid()
    }

    private fun setFormState() {
        _form.value =
            FormState(nameError = nameError, surnameError = surnameError, jmbgError = jmbgError)
    }

    private fun isDataValid() {
        if (nameError == null && surnameError == null && jmbgError == null)
            _form.value = FormState(isDataValid = true)
    }
}