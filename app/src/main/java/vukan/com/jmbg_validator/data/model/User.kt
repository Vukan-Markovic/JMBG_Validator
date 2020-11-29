package vukan.com.jmbg_validator.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val name: String, val surname: String, val jmbg: String) : Parcelable