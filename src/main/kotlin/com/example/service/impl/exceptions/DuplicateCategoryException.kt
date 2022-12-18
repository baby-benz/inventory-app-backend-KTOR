package com.example.service.impl.exceptions

class DuplicateCategoryException(category: String) :
    RuntimeException("Product category with name $category already exists")