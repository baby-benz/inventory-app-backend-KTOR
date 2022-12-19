package com.example.service.impl.exceptions

class ReferenceViolationException : RuntimeException {
    constructor() : super("Couldn't be deleted. The dependent entity has a reference to this resource")
    constructor(
        id: String,
        entityName: String,
        referencedEntityName: String
    ) : super("Couldn't be deleted. $entityName has a reference to $referencedEntityName with id $id")
}