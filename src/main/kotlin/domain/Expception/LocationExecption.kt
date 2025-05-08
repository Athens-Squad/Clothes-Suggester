package org.example.domain.Expception

class LocationExecption {
    open class DomainExceptions(message: String) : Exception(message)
    class NoLocationFoundException : DomainExceptions("No location found")
}