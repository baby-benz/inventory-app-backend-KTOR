package com.example.dal

import com.example.domain.dto.request.producer.ProducerRequest
import com.example.service.impl.so.impl.ProducerSO

interface ProducerDAL : DAL<ProducerRequest, ProducerSO>