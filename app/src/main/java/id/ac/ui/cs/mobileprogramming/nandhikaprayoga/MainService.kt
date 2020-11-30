package id.ac.ui.cs.mobileprogramming.nandhikaprayoga

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class MainService {
    enum class HttpMethod(val value: String) {
        GET("GET"),
        POST("POST"),
    }

    companion object {
        private const val API_DOMAIN = "https://postman-echo.com"

        fun getAPIUri(endpoint: String): String {
            return "${API_DOMAIN}${endpoint}"
        }

        fun sendRequest(
            callback: Callback,
            uri: String,
            method: HttpMethod = HttpMethod.GET,
            body: RequestBody? = null,
        ) {
            val client: OkHttpClient = OkHttpClient().newBuilder().build()
            val rawRequest: Request.Builder = Request.Builder()
                .url(uri)
                .method(method.value, body)

            val request: Request = rawRequest.build()
            client.newCall(request).enqueue(callback)
        }

//        fun createUser(
//            context: Context,
//            callback: Callback,
//            name: String,
//            username: String,
//            password: String,
//            instanceName: String = "",
//            degreeName: String = "",
//            workplaceCountry: String = "",
//            workplaceProvince: String = "",
//            workplaceCity: String = "",
//            workplaceStreetName: String = "",
//            workplacePostalCode: String = ""
//        ) {
//            sendRequest(
//                context,
//                callback = callback,
//                uri = getAPIUri("/api/v1/users/"),
//                method = HttpMethod.POST,
//                body = RequestBody.create(
//                    MediaType.parse("application/json"),
//                    Utility.stringifyJSON(
//                        mapOf(
//                            "name" to name,
//                            "account" to mapOf(
//                                "username" to username,
//                                "password" to password
//                            ),
//                            "degree" to mapOf(
//                                "instance_name" to instanceName,
//                                "name" to degreeName
//                            ),
//                            "workplace" to mapOf(
//                                "country" to workplaceCountry,
//                                "province" to workplaceProvince,
//                                "city" to workplaceCity,
//                                "street_name" to workplaceStreetName,
//                                "postal_code" to workplacePostalCode
//                            )
//                        ) as HashMap<String, Any>
//                    )
//                )
//            )
//        }
    }
}