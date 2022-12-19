package com.example.domain.dto.request.supplier

import kotlinx.serialization.Serializable

@Serializable
data class SupplierRequest(val name: String, val phone: String, val email: String)