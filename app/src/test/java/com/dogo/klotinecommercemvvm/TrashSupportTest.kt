package com.dogo.klotinecommercemvvm

import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.domain.util.asTrashResult
import com.dogo.klotinecommercemvvm.presentation.util.formatDeletedAt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TrashSupportTest {

    private fun failure(code: Int?, message: String = "") = Resource.Failure(message, code)

    @Test
    fun `success no se modifica`() {
        val ok = Resource.Success(Unit)
        assertEquals(ok, ok.asTrashResult())
    }

    @Test
    fun `409 usa el mensaje de conflicto indicado`() {
        val result = failure(409, "conflict").asTrashResult(conflictMessage = "Restaura primero la categoría")
        assertEquals("Restaura primero la categoría", (result as Resource.Failure).message)
        assertEquals(409, result.code)
    }

    @Test
    fun `401 y 403 devuelven mensajes de sesion y permisos`() {
        assertTrue((failure(401).asTrashResult() as Resource.Failure).message.contains("sesión"))
        assertTrue((failure(403).asTrashResult() as Resource.Failure).message.contains("administrador"))
    }

    @Test
    fun `404 y 405 indican que el servidor no soporta la operacion`() {
        assertTrue((failure(404).asTrashResult() as Resource.Failure).message.contains("no soporta"))
        assertTrue((failure(405).asTrashResult() as Resource.Failure).message.contains("no soporta"))
    }

    @Test
    fun `otros errores conservan el mensaje del servidor o usan uno por defecto`() {
        assertEquals("boom", (failure(500, "boom").asTrashResult() as Resource.Failure).message)
        assertEquals("Error desconocido", (failure(500, "").asTrashResult() as Resource.Failure).message)
    }

    @Test
    fun `formatDeletedAt maneja nulos y textos no interpretables`() {
        assertEquals("Fecha desconocida", formatDeletedAt(null))
        assertEquals("no-es-fecha", formatDeletedAt("no-es-fecha"))
    }

    @Test
    fun `formatDeletedAt formatea fecha ISO sin zona`() {
        assertTrue(formatDeletedAt("2026-11-23T10:15:30").contains("23/11/2026 10:15"))
    }
}
