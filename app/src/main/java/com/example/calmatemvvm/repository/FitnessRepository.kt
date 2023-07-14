package com.example.calmatemvvm.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.calmatemvvm.model.DailyFitnessModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface FitnessRepository {
    fun getDailyFitnessData(context: Context): MutableLiveData<DailyFitnessModel>
    fun getGoogleAccount(context: Context): GoogleSignInAccount
}