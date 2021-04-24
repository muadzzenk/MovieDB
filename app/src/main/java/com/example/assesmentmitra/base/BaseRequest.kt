package com.example.assesmentmitra.base

import com.example.assesmentmitra.BuildConfig

abstract class BaseRequest : HashMap<String, Any>()  {
    init {
        put("api_key", BuildConfig.API_KEY)
    }
}