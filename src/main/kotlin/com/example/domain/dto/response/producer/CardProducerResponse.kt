package com.example.domain.dto.response.producer

import com.example.domain.dto.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CardProducerResponse(
    @Serializable(with = UUIDSerializer::class) override val id: UUID,
    override val name: String
) : ProducerResponse()