package org.example.domain.exception


open class LocationExceptions(message: String) : Exception(message)

class NoLocationFoundException(message: String = "") : LocationExceptions("No location found")

