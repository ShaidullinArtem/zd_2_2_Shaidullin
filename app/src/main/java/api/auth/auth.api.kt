package api.auth

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import types.AuthType
import java.io.IOException


class AuthAPI constructor(val context: Context) {
    private val url = "http://192.168.31.220:8000/api/v1/auth/token/login/"
    private var token = ""

    fun login(username: String, password: String): AuthType {
        val params = JSONObject()
        params.put("username", username)
        params.put("password", password)

        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.POST, url, params, {response ->
            try {
                token = response.getString("auth_token")
            } catch (e: IOException) {
                Log.d("Response", e.toString())
            }
        }, { error ->
            Log.d("Response", error.message.toString())
        })
        queue.add(request)

        return AuthType(token)
    }
}