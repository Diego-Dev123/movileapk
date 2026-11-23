package com.dogo.klotinecommercemvvm.domain.util


fun <T> Resource<T>.asTrashResult(conflictMessage: String? = null): Resource<T> {
    if (this !is Resource.Failure) return this
    val friendly = when (code) {
        401 -> "Tu sesión expiró. Inicia sesión nuevamente."
        403 -> "Solo el administrador puede realizar esta acción."
        404, 405, 501 -> "El servidor aún no soporta esta operación de la papelera."
        409 -> conflictMessage ?: message.ifBlank { "La operación no se pudo completar por un conflicto." }
        else -> message.ifBlank { "Error desconocido" }
    }
    return copy(message = friendly)
}
