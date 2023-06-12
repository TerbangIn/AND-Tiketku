package com.ketarketir.tiketkuioflight.datastoreprefs

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(private val context: Context) {

    private val preferenceName = "prefs"
    private val Context.datastore by preferencesDataStore(preferenceName)

    private val EMAIL = stringPreferencesKey("email")
    private val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
    private val TOKEN = stringPreferencesKey("token")

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var instance: UserManager? = null

        fun getInstance(context: Context): UserManager {
            return instance ?: synchronized(this) {
                instance ?: UserManager(context).also { instance = it }
            }
        }
    }

    suspend fun saveData (email:String, is_login_key:Boolean,token:String){
        context.datastore.edit {
            it [EMAIL] = email
            it [IS_LOGIN_KEY] = is_login_key
            it [TOKEN] = token
        }
    }

    suspend fun clearData(){
        context.datastore.edit {
            it.clear()
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return context.datastore.data
            .map { preferences ->
                preferences[IS_LOGIN_KEY] ?: false
            }
    }
}