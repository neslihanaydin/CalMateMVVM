package com.example.calmatemvvm.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.calmatemvvm.model.DailyFitnessModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import java.util.Calendar
import java.util.concurrent.TimeUnit

class FitnessRepositoryImpl(): FitnessRepository {

    private val fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
        .build()

    override fun getDailyFitnessData(context: Context): MutableLiveData<DailyFitnessModel> {
        val dailyFitnessLiveData = MutableLiveData<DailyFitnessModel>()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startTime = calendar.timeInMillis

        // Create a request to get daily datas
        val readRequest = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA )
            .aggregate(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED)
            .aggregate(DataType.TYPE_DISTANCE_DELTA, DataType.AGGREGATE_DISTANCE_DELTA)
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(startTime, System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .build()

        Fitness.getHistoryClient(context, getGoogleAccount(context))
            .readData(readRequest)
            .addOnSuccessListener { data ->
                val buckets = data.buckets
                val bucket = if (buckets.isNotEmpty()) buckets[0] else null
                var stepCount = 0
                var calories = 0
                var distance = 0.0f
                bucket?.dataSets?.forEach { dataSet ->
                    dataSet.dataPoints.forEach{ dataPoint ->
                        when (dataPoint.dataType) {
                            DataType.TYPE_STEP_COUNT_DELTA -> {
                                stepCount = dataPoint.getValue(Field.FIELD_STEPS).asInt()
                            }
                            DataType.TYPE_CALORIES_EXPENDED -> {
                                calories = (dataPoint.getValue(Field.FIELD_CALORIES).asFloat()).toInt()
                            }
                            DataType.TYPE_DISTANCE_DELTA -> {
                                distance = dataPoint.getValue(Field.FIELD_DISTANCE).asFloat() / 1000
                            }
                        }
                    }
                }
                val dailyFitness = DailyFitnessModel(stepCount, calories, distance)
                dailyFitnessLiveData.postValue(dailyFitness)
            }
            .addOnFailureListener{ exception ->
                System.out.println("Error on getDailyFitnessData " + exception.localizedMessage);
            }
        return dailyFitnessLiveData
    }

    override fun getGoogleAccount(context: Context): GoogleSignInAccount = GoogleSignIn.getAccountForExtension(context, fitnessOptions)

}