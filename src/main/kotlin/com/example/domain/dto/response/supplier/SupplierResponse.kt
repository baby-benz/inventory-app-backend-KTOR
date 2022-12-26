package com.example.domain.dto.response.supplier

import com.example.domain.dto.response.Response

abstract class SupplierResponse : Response() {
    abstract val name: String
    abstract val phone: String
    abstract val email: String
}