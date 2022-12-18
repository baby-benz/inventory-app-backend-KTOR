package com.example.dal.impl

import com.example.dal.DAL
import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.SupplierDAL
import com.example.domain.dto.request.SupplierRequest
import com.example.domain.dto.response.SupplierResponse
import com.example.domain.models.Supplier
import com.example.domain.models.Suppliers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class DefaultSupplierDAL : SupplierDAL {
    private fun resultRowToSupplier(row: ResultRow) = SupplierResponse(
        id = row[Suppliers.id].value,
        name = row[Suppliers.name],
        phone = row[Suppliers.phone],
        email = row[Suppliers.email],
    )

    override suspend fun all(): List<SupplierResponse> = dbQuery {
        Suppliers.selectAll().map(::resultRowToSupplier)
    }

    override suspend fun find(id: UUID): SupplierResponse? = dbQuery {
        Suppliers.select { Suppliers.id eq id }.map(::resultRowToSupplier).singleOrNull()
    }

    override suspend fun existsById(id: UUID): Boolean = dbQuery {
        !Suppliers.select { Suppliers.id eq id }.empty()
    }

    override suspend fun save(toSave: SupplierRequest): SupplierResponse = dbQuery {
        Supplier.new {
            name = toSave.name
            phone = toSave.phone
            email = toSave.email
        }.toResponse()
    }

    override suspend fun save(id: UUID, toSave: SupplierRequest): SupplierResponse = dbQuery {
        Supplier.new(id) {
            name = toSave.name
            phone = toSave.phone
            email = toSave.email
        }.toResponse()
    }

    override suspend fun update(id: UUID, toUpdate: SupplierRequest): Boolean = dbQuery {
        Suppliers.update({ Suppliers.id eq id }) {
            it[name] = toUpdate.name
            it[phone] = toUpdate.phone
            it[email] = toUpdate.email
        } > 0
    }

    override suspend fun delete(id: UUID): Boolean = dbQuery {
        Suppliers.deleteWhere { Suppliers.id eq id } > 0
    }
}