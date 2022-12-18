package com.example.plugins

import com.example.domain.dto.request.SupplierRequest
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validate<SupplierRequest> { supplier ->
            val phoneUtil = PhoneNumberUtil.getInstance()

            try {
                val phoneNumber: Phonenumber.PhoneNumber = phoneUtil.parse(supplier.phone, "RU")

                if (!phoneUtil.isValidNumberForRegion(phoneNumber, "RU")) {
                    ValidationResult.Invalid("Wrong number was passed")
                } else {
                    ValidationResult.Valid
                }
            } catch (e: NumberParseException) {
                ValidationResult.Invalid("Wrong number was passed")
            }
        }
    }
}