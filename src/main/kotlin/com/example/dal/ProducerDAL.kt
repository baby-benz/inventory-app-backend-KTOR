package com.example.dal

import com.example.domain.dto.request.producer.ProducerRequest
import com.example.domain.dto.response.producer.ProducerResponse

interface ProducerDAL : DAL<ProducerRequest, ProducerResponse>