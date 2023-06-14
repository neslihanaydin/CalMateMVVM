package com.example.calmatemvvm.app.impl

import android.content.res.Resources
import com.example.calmatemvvm.app.unit.ResourcesUnit
import javax.inject.Inject

class ResourcesUnitImpl @Inject constructor(
    private val resources: Resources
) : ResourcesUnit {

    override fun getStringValue(id: Int): String {
        return resources.getString(id)
    }
}