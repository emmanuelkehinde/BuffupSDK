package com.emmanuelkehinde.buffup.remote

import com.emmanuelkehinde.buffup.remote.response.BuffApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Emmanuel Kehinde on 2020-03-28.
 */
class BuffApiService {

    companion object {

        private const val BASE_URL = "https://buffup.proxy.beeceptor.com/"
        private const val GET_BUFF_WITH_ID_ENDPOINT = "buffs/%s"

        /**
         * Fetches Buffs from the Buff API using their buff ids
         *
         * @param buffId The ID of the buff to be fetched
         * @return BuffResult
         */
        internal suspend fun getBuff(buffId: String): BuffResult {
            var response: BuffApiResponse? = null
            withContext(Dispatchers.IO) {
                try {
                    val url = URL(String.format(BASE_URL + GET_BUFF_WITH_ID_ENDPOINT, buffId))

                    val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    urlConnection.readTimeout = 30000
                    urlConnection.connectTimeout = 30000
                    urlConnection.setRequestProperty("Content-Type", "application/json")
                    urlConnection.requestMethod = "GET"

                    val statusCode = urlConnection.responseCode

                    if (statusCode == 200) {

                        val mIn = BufferedReader(
                            InputStreamReader(
                                urlConnection.inputStream
                            )
                        )

                        val sb = StringBuffer("")
                        var line = mIn.readLine()

                        while (line != null) {
                            sb.append(line)
                            line = mIn.readLine()
                        }

                        mIn.close()
                        response = Gson().fromJson<BuffApiResponse>(sb.toString(), BuffApiResponse::class.java)
                    } else {
                        return@withContext BuffResult.Error(
                            Throwable(BuffApiError.UNABLE_TO_FETCH_BUFF)
                        )
                    }
                } catch (e: Exception) {
                    return@withContext BuffResult.Error(
                        Throwable(BuffApiError.UNABLE_TO_FETCH_BUFF)
                    )
                }
            }

            val buff = response?.result
            return if (buff != null) {
                BuffResult.Success(buff)
            } else {
                BuffResult.Error(
                    Throwable(BuffApiError.NO_BUFF)
                )
            }
        }
    }
}