package com.example.service

import com.example.dal.DAL
import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.dto.response.producer.DefaultProducerResponse

interface ProducerService : Service<ProducerRequest, DefaultProducerResponse>