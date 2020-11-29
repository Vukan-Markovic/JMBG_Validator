package vukan.com.jmbg_validator.data.model

data class JmbgData(
    val country: String,
    val region: String,
    val gender: String,
    val ordinalNumberOfBirth: Int,
    val dateOfBirth: String?,
    val dayOfBirth: String
)