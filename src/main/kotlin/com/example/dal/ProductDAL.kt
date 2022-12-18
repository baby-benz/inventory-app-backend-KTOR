package com.example.dal

import com.example.domain.dto.request.ProductRequest
import com.example.domain.dto.response.ProductResponse

interface ProductDAL : DAL<ProductRequest, ProductResponse>