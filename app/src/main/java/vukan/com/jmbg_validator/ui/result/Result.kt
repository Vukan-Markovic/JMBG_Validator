package vukan.com.jmbg_validator.ui.result

import vukan.com.jmbg_validator.data.model.JmbgData

data class Result(val success: JmbgData? = null, val error: Int? = null)