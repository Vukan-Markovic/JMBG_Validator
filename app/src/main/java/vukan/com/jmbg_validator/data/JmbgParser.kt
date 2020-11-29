package vukan.com.jmbg_validator.data

import android.content.Context
import vukan.com.jmbg_validator.R
import vukan.com.jmbg_validator.data.model.JmbgData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class JmbgParser {

    companion object {

        fun isJMBGValid(jmbg: String): Int? {
            if (jmbg.isBlank()) return R.string.invalid_jmbg
            if (jmbg.length != 13) return R.string.invalid_jmbg_length

            val day = jmbg.substring(0, 2)
            val month = jmbg.substring(2, 4)
            var year = Integer.parseInt(jmbg.substring(4, 7))

            year += if (year in 850..999) 1000
            else 2000

            try {
                val df: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                df.isLenient = false
                df.parse("$day-$month-$year")
            } catch (e: Exception) {
                return R.string.invalid_jmbg_date
            }

            val region = Integer.parseInt(jmbg.substring(7, 9))

            if (region !in 1..5 && region !in 7..19 && region != 21 && region != 26 && region != 29 && region !in 30..39 && region !in 41..50 && region !in 71..82 && region !in 85..89 && region !in 91..96) return R.string.invalid_jmbg_region

            // if (region == 0 || region == 6 || region == 20 || region in 22..25 || region in 27..28 || region == 40 || region in 51..70 || region in 83..84 || region == 90 || region in 97..99) return R.string.invalid_jmbg_region

            var l = 11 - (7 * (jmbg.substring(0, 1).toInt() + jmbg.substring(6, 7).toInt()) +
                    6 * (jmbg.substring(1, 2).toInt() + jmbg.substring(7, 8).toInt()) +
                    5 * (jmbg.substring(2, 3).toInt() + jmbg.substring(8, 9).toInt()) +
                    4 * (jmbg.substring(3, 4).toInt() + jmbg.substring(9, 10).toInt()) +
                    3 * (jmbg.substring(4, 5).toInt() + jmbg.substring(10, 11).toInt()) +
                    2 * (jmbg.substring(5, 6).toInt() + jmbg.substring(11, 12).toInt())) % 11

            if (l > 9) l = 0

            if (jmbg.substring(12, 13).toInt() != l) return R.string.invalid_jmbg_control_digit
            return null
        }

        fun parseJMBG(jmbg: String, context: Context): Result<JmbgData> {
            return try {
                val dayOfBirth = jmbg.substring(0, 2)
                val monthOfBirth = jmbg.substring(2, 4)
                var yearOfBirth = Integer.parseInt(jmbg.substring(4, 7))
                val region = Integer.parseInt(jmbg.substring(7, 9))
                val uniqueNumber = Integer.parseInt(jmbg.substring(9, 12))
                var ordinalNumberOfBirth = 0
                var gender = ""
                var placeOfBirth = ""
                var countryOfBirth = ""
                var dayOfWeekBirth = ""
                val c = Calendar.getInstance()

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
                        countryOfBirth = context.getString(R.string.bih)
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
                            96 -> context.getString(R.string.kosovsko_pomoravski_okrug)
                            else -> context.getString(R.string.invalid_region)
                        }
                    }
                }

                if (region == 50) countryOfBirth = context.getString(R.string.slovenija)

                if (uniqueNumber in 0..499) {
                    gender = context.getString(R.string.male)
                    ordinalNumberOfBirth = uniqueNumber + 1
                } else if (uniqueNumber in 501..999) {
                    gender = context.getString(R.string.female)
                    ordinalNumberOfBirth = uniqueNumber - 499
                }

                yearOfBirth += if (yearOfBirth in 850..999) 1000
                else 2000

                val date = SimpleDateFormat(
                    "dd-MM-yyyy",
                    Locale.getDefault()
                ).parse("$dayOfBirth-$monthOfBirth-$yearOfBirth")

                if (date != null) c.time = date

                when (c[Calendar.DAY_OF_WEEK]) {
                    1 -> dayOfWeekBirth = context.getString(R.string.sunday)
                    2 -> dayOfWeekBirth = context.getString(R.string.monday)
                    3 -> dayOfWeekBirth = context.getString(R.string.tuesday)
                    4 -> dayOfWeekBirth = context.getString(R.string.wednesday)
                    5 -> dayOfWeekBirth = context.getString(R.string.thursday)
                    6 -> dayOfWeekBirth = context.getString(R.string.friday)
                    7 -> dayOfWeekBirth = context.getString(R.string.saturday)
                }

                Result.Success(
                    JmbgData(
                        countryOfBirth,
                        placeOfBirth,
                        gender,
                        ordinalNumberOfBirth,
                        "$dayOfBirth.$monthOfBirth.$yearOfBirth.",
                        dayOfWeekBirth
                    )
                )
            } catch (e: Throwable) {
                Result.Error(Exception(e.message, e))
            }
        }
    }
}