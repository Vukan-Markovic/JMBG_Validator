package vukan.com.jmbg_validator.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class User(val name: String, val surname: String, val jmbg: String) : Parcelable