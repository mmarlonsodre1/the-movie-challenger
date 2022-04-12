package com.example.domain.exception

open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))