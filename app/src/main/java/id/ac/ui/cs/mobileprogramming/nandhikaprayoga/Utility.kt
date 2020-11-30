package id.ac.ui.cs.mobileprogramming.nandhikaprayoga

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser


/**
 * Collection of common functions
 *
 */
class Utility {
    companion object {
        fun stringifyJSON(data: Map<String, ArrayList<String>>): String {
            return Gson().toJson(data).toString()
        }

        /**
         * Parse JSON into generic object
         *
         * @param json  Serialized JSON string
         * @return      A list of key-value, represented by HashMap
         */
        fun parseJSON(json: String?): HashMap<String, Any> {
            val `object` = JsonParser.parseString(json) as JsonObject
            val set: Set<Map.Entry<String, JsonElement>> =
                `object`.entrySet()
            val iterator: Iterator<Map.Entry<String, JsonElement>> =
                set.iterator()
            val map = HashMap<String, Any>()
            while (iterator.hasNext()) {
                val entry: Map.Entry<String, JsonElement> =
                    iterator.next()
                val key = entry.key
                val value: JsonElement = entry.value
                if (!value.isJsonPrimitive) {
                    if (value.isJsonObject) {
                        map[key] = parseJSON(value.toString())
                    } else if (value.isJsonArray) {
                        val valueIsObject: Boolean = value.toString().contains(":")

                        map[key] = if (valueIsObject) {
                            val list: MutableList<HashMap<String, Any>> = ArrayList()
                            for (element in value.asJsonArray) {
                                list.add(parseJSON(element.toString()))
                            }
                            list
                        } else {
                            val list: MutableList<String> = ArrayList()
                            for (element in value.asJsonArray) {
                                list.add(element.toString())
                            }
                            list
                        }
                    }
                } else {
                    map[key] = value.asString
                }
            }
            return map
        }
    }
}