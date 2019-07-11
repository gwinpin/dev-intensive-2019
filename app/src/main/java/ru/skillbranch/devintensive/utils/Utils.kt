@file:Suppress("LiftReturnOrAssignment")

package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")

        return when {
            parts?.count() == 2 -> Pair(parts[0], parts[1])
            parts?.count() == 1 && parts[0] != "" -> Pair(parts[0], null)
            else -> Pair(null, null)
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fn = firstName?.trim()?.getOrNull(0)
        val ln = lastName?.trim()?.getOrNull(0)

        return when {
            fn != null && ln != null -> "${fn.toUpperCase()}${ln.toUpperCase()}"
            fn != null -> "${fn.toUpperCase()}"
            ln != null -> "${ln.toUpperCase()}"
            else -> null
        }
    }


    fun transliteration(payload: String?, divider: String = " "): String {
        val parts: List<String>? = payload?.trim()?.split(" ")

        var txt = ""
        parts?.forEach { word: String ->
            txt += if (txt === "") this.translit(word) else divider + this.translit(word)
        }
//        val(firstName,lastName) = parseFullName(payload)
//        return "${this.translit(firstName)}$divider${this.translit(lastName)}"
        return txt
    }

    private fun translit(string: String?): String {

        var tmpString = ""

        val key = mapOf(
            "а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z",
            "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r",
            "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh",
            "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya"
        )

        string?.forEach { c: Char ->
            //            tmpString += key[c.toString().toLowerCase()] ?: c
            tmpString += when {
                key[c.toString()] != null -> key[c.toString()]
                key[c.toString().toLowerCase()] != null -> key[c.toString().toLowerCase()]?.toUpperCase()
                else -> c
            }

        }

        return tmpString
//        return tmpString.capitalize()
    }
}