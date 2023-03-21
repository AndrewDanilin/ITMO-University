package com.andrewdanilin.homework6.services

open class ServiceRequestResult(
    val result: Result,
    val first: Int? = null,
    val second: Int? = null,
    val text: String? = null
)

enum class Result {
    SUCCESS,
    FAILURE
}

