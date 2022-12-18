package com.example.service.impl.exceptions

class NotFoundException : RuntimeException {
    constructor() : super("Entity  with id provided doesn't exist")
    constructor(id: String) : super("Entity with id $id doesn't exist")
}