package com.example.dal.impl

import com.example.dal.DatabaseFactory.dbQuery
import com.example.dal.SupplierDAL
import com.example.domain.dto.request.supplier.SupplierRequest
import com.example.domain.model.Supplier
import com.example.domain.model.Suppliers
import com.example.service.impl.so.impl.SupplierSO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class DefaultSupplierDAL : SupplierDAL {
    private fun resultRowToSupplier(row: ResultRow) = SupplierSO(Supplier(row[Suppliers.id]))

    override suspend fun all(): Iterable<SupplierSO> = dbQuery {
        Suppliers.selectAll().map(::resultRowToSupplier)
    }

    override suspend fun find(id: UUID): SupplierSO? = dbQuery {
        Suppliers.select { Suppliers.id eq id }.map(::resultRowToSupplier).singleOrNull()
    }

    override suspend fun save(toSave: SupplierRequest): SupplierSO = dbQuery {
        Supplier.new {
            name = toSave.name
            phone = toSave.phone
            email = toSave.email
        }.toSo()
    }

    override suspend fun save(id: UUID, toSave: SupplierRequest): SupplierSO = dbQuery {
        Supplier.new(id) {
            name = toSave.name
            phone = toSave.phone
            email = toSave.email
        }.toSo()
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

    override suspend fun existsById(id: UUID): Boolean = dbQuery {
        !Supplier.find { Suppliers.id eq id }.empty()
    }
}