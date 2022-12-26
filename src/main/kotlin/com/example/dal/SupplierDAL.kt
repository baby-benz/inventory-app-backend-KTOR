package com.example.dal

import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.service.impl.so.impl.SupplierSO

interface SupplierDAL : DAL<SupplierRequest, SupplierSO>