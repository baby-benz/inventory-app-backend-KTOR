package com.example.domain.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SupplierRequest(val name: String, val phone: String, val email: String)