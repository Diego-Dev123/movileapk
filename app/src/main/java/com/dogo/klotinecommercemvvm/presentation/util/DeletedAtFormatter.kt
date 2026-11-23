package com.dogo.klotinecommercemvvm.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatDeletedAt(raw: String?): String {
    if (raw.isNullOrBlank()) return "Fecha desconocida"

    val output = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val normalized = raw.trim()
        .replace(' ', 'T')
        .replace(Regex("\\.\\d+"), "")
        .replace(Regex("Z$"), "+00:00")

    val date: Date? = parse(normalized, "yyyy-MM-dd'T'HH:mm:ssXXX")
        ?: parse(normalized, "yyyy-MM-dd'T'HH:mm:ss")
        ?: parse(normalized, "yyyy-MM-dd")

    return date?.let { output.format(it) } ?: raw
}

private fun parse(value: String, pattern: String): Date? = try {
    SimpleDateFormat(pattern, Locale.US).apply { isLenient = false }.parse(value)
} catch (e: Exception) {
    null
}
