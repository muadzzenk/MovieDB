package com.example.assesmentmitra.base

abstract class BaseResponse {
    var status_code: Int = 200
    var status_message: String = ""
    var success: Boolean = true
}