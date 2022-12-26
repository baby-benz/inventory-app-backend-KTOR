package com.example.domain.dto.response.producer

import com.example.domain.dto.response.Response

abstract class ProducerResponse : Response() {
    abstract val name: String
}