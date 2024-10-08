package br.edu.utfpr.trabalhofinal.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.edu.utfpr.trabalhofinal.data.Conta
import br.edu.utfpr.trabalhofinal.data.TipoContaEnum
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun List<Conta>.calcularSaldo(): BigDecimal = map {
    if (it.paga) {
        if (it.tipo == TipoContaEnum.DESPESA) {
            it.valor.negate()
        } else {
            it.valor
        }
    } else {
        BigDecimal.ZERO
    }
}.sumOf { it }

fun List<Conta>.calcularProjecao(): BigDecimal = map {
    if (it.tipo == TipoContaEnum.DESPESA) it.valor.negate() else it.valor
}.sumOf { it }

fun BigDecimal.formatar(): String {
    val formatter = DecimalFormat("R$#,##0.00")
    return formatter.format(this)
}

fun LocalDate.formatar(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return format(formatter)
}

@Composable
fun BigDecimal.colorir(): Color {
    if (this < BigDecimal.ZERO) {
        return Color(0xFFCF5355)
    } else if (this == BigDecimal.ZERO)  {
        return MaterialTheme.colorScheme.secondary
    } else {
        return Color(0xFF00984E)
    }
}