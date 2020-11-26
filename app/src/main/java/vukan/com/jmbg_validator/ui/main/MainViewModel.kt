package vukan.com.jmbg_validator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.jmbg_validator.R
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val _loginForm = MutableLiveData<FormState>()
    val formState: LiveData<FormState> = _loginForm

    fun loginDataChanged(ime: String, prezime: String, jmbg: String) {
        if (!isImeValid(ime)) {
            _loginForm.value = FormState(imeError = R.string.invalid_name)
        } else if (!isPrezimeValid(prezime)) {
            _loginForm.value = FormState(prezimeError = R.string.invalid_surname)
        } else if (isJMBGValid(jmbg)) {
            _loginForm.value = FormState(isDataValid = true)
        }
    }

    private fun isImeValid(ime: String): Boolean {
        return ime.isNotBlank()
    }

    private fun isPrezimeValid(prezime: String): Boolean {
        return prezime.isNotBlank()
    }

    private fun isJMBGValid(jmbg: String): Boolean {
        if (jmbg.isBlank()) {
            _loginForm.value = FormState(jmbgError = R.string.invalid_jmbg)
            return false
        }

        if (jmbg.length != 13) {
            _loginForm.value = FormState(jmbgError = R.string.invalid_jmbg_length)
            return false
        }

        val dd = jmbg.substring(0, 2)
        val mm = jmbg.substring(2, 4)
        var ggg = Integer.parseInt(jmbg.substring(4, 7))

        ggg += if (ggg < 100) 2000
        else 1000

//            const int MAX_VALID_YR = 9999;
//            const int MIN_VALID_YR = 1900;

//            if (sestiBroj == "0") label6.Text = "2" + godinaRodjenja;
//            else label6.Text = "1" + godinaRodjenja;
//
//            if(ggg >= 0 && ggg <= 799) godina = "2" + ggg;
//            else godina = "1" + ggg;

//            if (godina.StartsWith("9")) txtDRR.Text = dan + "." + mesec + "." + "1" + godina + ".";
//            else txtDRR.Text = dan + "." + mesec + "." + "2" + godina + ".";

        try {
            SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).parse("$dd-$mm-$ggg")
        } catch (e: Exception) {
            _loginForm.value = FormState(jmbgError = R.string.invalid_jmbg_date)
            return false
        }

        val rr = Integer.parseInt(jmbg.substring(7, 9))

        if (rr !in 1..19 && rr != 21 && rr != 26 && rr != 29 && rr !in 30..39 && rr !in 41..59 && rr !in 71..89 && rr !in 91..96) {
            _loginForm.value = FormState(jmbgError = R.string.invalid_jmbg_region)
            return false
        }

        val k = jmbg[11].toInt()
        var l =
            11 - (7 * (jmbg[0].toInt() + jmbg[6].toInt()) + 6 * (jmbg[1].toInt() + jmbg[7].toInt()) + 5 * (jmbg[2].toInt() + jmbg[8].toInt()) + 4 * (jmbg[3].toInt() + jmbg[9].toInt()) + 3 * (jmbg[4].toInt() + jmbg[10].toInt()) + 2 * (jmbg[5].toInt() + k)) % 11
        if (l > 9) l = 0

        if (k != l) {
            _loginForm.value = FormState(jmbgError = R.string.invalid_jmbg_control_digit)
            return false
        }

        return true
    }
}