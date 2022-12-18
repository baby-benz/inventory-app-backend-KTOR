package com.example.service.impl.exceptions

class BadReferenceException : RuntimeException {
    constructor() : super("Referenced entity doesn't exist")
    constructor(id: String) : super("Referenced entity with id $id doesn't exist")
}