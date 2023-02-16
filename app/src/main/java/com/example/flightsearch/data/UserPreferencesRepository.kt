package com.example.flightsearch.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val SEARCH_PREFERENCE_NAME = "search_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SEARCH_PREFERENCE_NAME)

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun getSavedUserInput() = dataStore.data
        .catch {
            if(it is IOException){
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            }else {
                throw it
            }
        }.map {preferences ->
            preferences[USER_INPUT] ?: ""
        }
    suspend fun saveUserInputPreferences(userInput: String) {
        try{
            dataStore.edit{ preferences ->
                preferences[USER_INPUT] = userInput
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
    private companion object {
        val USER_INPUT = stringPreferencesKey("user_input")
        const val TAG = "UserPreferenceRepo"
    }
}