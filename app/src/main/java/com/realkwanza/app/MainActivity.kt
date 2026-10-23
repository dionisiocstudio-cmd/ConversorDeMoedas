
package com.realkwanza.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RealKwanzaScreen()
            }
        }
    }
}

@Composable
fun RealKwanzaScreen() {
    var amount by remember { mutableStateOf("1") }
    var fromCurrency by remember { mutableStateOf("AOA") }
    var toCurrency by remember { mutableStateOf("BRL") }

    val currencies = listOf(
        "AOA", "BRL", "USD", "EUR",
        "GBP", "NAD", "ZAR", "CNY"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "RealKwanza",
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "Conversor de moedas para Angola",
                style = MaterialTheme.typography.bodyLarge
            )

            HorizontalDivider()

            Text(
                text = "Valor a converter",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Montante") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            CurrencySelector(
                label = "Converter de",
                selected = fromCurrency,
                currencies = currencies,
                onSelected = { fromCurrency = it }
            )

            OutlinedButton(
                onClick = {
                    val temp = fromCurrency
                    fromCurrency = toCurrency
                    toCurrency = temp
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("⇅ Trocar moedas")
            }

            CurrencySelector(
                label = "Converter para",
                selected = toCurrency,
                currencies = currencies,
                onSelected = { toCurrency = it }
            )

            HorizontalDivider()

            Text(
                text = "Cotação",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "A integração das taxas está em preparação.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "RUB — Rublo russo: indisponível até existir uma cotação válida.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            Text(
                text = "As conversões só serão apresentadas quando existirem taxas verificadas.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Fontes previstas: BNA e bancos comerciais angolanos.",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun CurrencySelector(
    label: String,
    selected: String,
    currencies: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selected)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        text = { Text(currency) },
                        onClick = {
                            onSelected(currency)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
