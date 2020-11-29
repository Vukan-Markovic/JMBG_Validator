package vukan.com.jmbg_validator.ui.main

data class FormState(
    val nameError: Int? = null,
    val surnameError: Int? = null,
    val jmbgError: Int? = null,
    val isDataValid: Boolean = false
)