package vukan.com.jmbg_validator.data

import android.content.Context
import vukan.com.jmbg_validator.R
import vukan.com.jmbg_validator.data.model.JmbgData

class JmbgValidator {

    fun parseJMBG(jmbg: String, context: Context): Result<JmbgData> {
        return try {
            val region = Integer.parseInt(jmbg.substring(7, 9))
            val uniqueNumber = Integer.parseInt(jmbg.substring(9, 12))
            var gender = ""
            var ordinalNumberOfBirth = 0
            var placeOfBirth = ""
            var countryOfBirth = ""

            if (region in 1..9) {
                countryOfBirth = context.getString(R.string.foreigners)
                placeOfBirth = when (region) {
                    1 -> context.getString(R.string.foreigners_BIH)
                    2 -> context.getString(R.string.foreigners_Montenegro)
                    3 -> context.getString(R.string.foreigners_Croatia)
                    4 -> context.getString(R.string.foreigners_Macedonia)
                    5 -> context.getString(R.string.foreigners_Slovenia)
                    7 -> context.getString(R.string.foreigners_Serbia)
                    8 -> context.getString(R.string.foreigners_Vojvodina)
                    9 -> context.getString(R.string.foreigners_KIM)
                    else -> context.getString(R.string.invalid_region)
                }
            }

            when (region) {
                in 10..19 -> {
                    countryOfBirth = context.getString(R.string.BIH)
                    placeOfBirth = when (region) {
                        10 -> context.getString(R.string.banja_luka)
                        11 -> context.getString(R.string.bihac)
                        12 -> context.getString(R.string.doboj)
                        13 -> context.getString(R.string.gorazde)
                        14 -> context.getString(R.string.livno)
                        15 -> context.getString(R.string.mostar)
                        16 -> context.getString(R.string.prijedor)
                        17 -> context.getString(R.string.sarajevo)
                        18 -> context.getString(R.string.tuzla)
                        19 -> context.getString(R.string.zenica)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
                in 21..29 -> {
                    countryOfBirth = context.getString(R.string.montenegro)
                    placeOfBirth = when (region) {
                        21 -> context.getString(R.string.podgorica)
                        26 -> context.getString(R.string.niksic)
                        29 -> context.getString(R.string.pljevlja)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
                in 30..39 -> {
                    countryOfBirth = context.getString(R.string.croatia)
                    placeOfBirth = when (region) {
                        30 -> context.getString(R.string.croatia_1)
                        31 -> context.getString(R.string.croatia_2)
                        32 -> context.getString(R.string.croatia_3)
                        33 -> context.getString(R.string.croatia_4)
                        34 -> context.getString(R.string.croatia_5)
                        35 -> context.getString(R.string.croatia_6)
                        36 -> context.getString(R.string.croatia_7)
                        37 -> context.getString(R.string.croatia_8)
                        38 -> context.getString(R.string.croatia_9)
                        39 -> context.getString(R.string.ostalo)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
                in 41..49 -> {
                    countryOfBirth = context.getString(R.string.macedonia)
                    placeOfBirth = when (region) {
                        41 -> context.getString(R.string.bitolj)
                        42 -> context.getString(R.string.kumanovo)
                        43 -> context.getString(R.string.ohrid)
                        44 -> context.getString(R.string.prilep)
                        45 -> context.getString(R.string.skoplje)
                        46 -> context.getString(R.string.strumica)
                        47 -> context.getString(R.string.tetovo)
                        48 -> context.getString(R.string.veles)
                        49 -> context.getString(R.string.stip)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
                in 50..59 -> {
                    countryOfBirth = context.getString(R.string.slovenija)
                    placeOfBirth = context.getString(R.string.slovenija)
                }
                in 71..79 -> {
                    countryOfBirth = context.getString(R.string.serbia)
                    placeOfBirth = when (region) {
                        71 -> context.getString(R.string.beograd)
                        72 -> context.getString(R.string.sumadija)
                        73 -> context.getString(R.string.nis)
                        74 -> context.getString(R.string.juzna_morava)
                        75 -> context.getString(R.string.zajecar)
                        76 -> context.getString(R.string.podunavlje)
                        77 -> context.getString(R.string.podrinje_kolubara)
                        78 -> context.getString(R.string.kraljevo)
                        79 -> context.getString(R.string.uzice)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
                in 80..89 -> {
                    countryOfBirth = context.getString(R.string.serbia_vojvodina)
                    placeOfBirth = when (region) {
                        80 -> context.getString(R.string.novi_sad)
                        81 -> context.getString(R.string.sombor)
                        82 -> context.getString(R.string.subotica)
                        85 -> context.getString(R.string.zrenjanin)
                        86 -> context.getString(R.string.pancevo)
                        87 -> context.getString(R.string.kikinda)
                        88 -> context.getString(R.string.ruma)
                        89 -> context.getString(R.string.sremska_mitrovica)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
                in 91..96 -> {
                    countryOfBirth = context.getString(R.string.serbia_kim)
                    placeOfBirth = when (region) {
                        91 -> context.getString(R.string.pristina)
                        92 -> context.getString(R.string.kosovska_mitrovica)
                        93 -> context.getString(R.string.pec)
                        94 -> context.getString(R.string.djakovica)
                        95 -> context.getString(R.string.prizren)
                        96 -> context.getString(R.string.gnjilane)
                        else -> context.getString(R.string.invalid_region)
                    }
                }
            }

            if (uniqueNumber in 0..499) {
                gender = context.getString(R.string.male)
                ordinalNumberOfBirth = uniqueNumber + 1
            } else if (uniqueNumber in 501..999) {
                gender = context.getString(R.string.female)
                ordinalNumberOfBirth = uniqueNumber - 499
            }

            var yearOfBirth = Integer.parseInt(jmbg.substring(4, 7))
            yearOfBirth += if (yearOfBirth < 100) 2000
            else 1000

            Result.Success(
                JmbgData(
                    placeOfBirth,
                    countryOfBirth,
                    gender,
                    ordinalNumberOfBirth,
                    jmbg.substring(0, 2) + "." + jmbg.substring(2, 4) + "." + yearOfBirth + "."
                )
            )
        } catch (e: Throwable) {
            Result.Error(Exception("Error", e))
        }
    }
}