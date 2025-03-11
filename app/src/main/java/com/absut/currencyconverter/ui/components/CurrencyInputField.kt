package com.absut.currencyconverter.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.absut.currencyconverter.R
import com.absut.currencyconverter.util.ThousandsSeparatorVisualTransformation

@Composable
fun CurrencyInputField(
	selectedCurrency: String,
	onCurrencyClick: () -> Unit,
	//onCurrencyChange: (String) -> Unit,
	amount: String = "",
	onAmountChange: (String) -> Unit,
	placeholder: String,
	modifier: Modifier = Modifier
) {
	//var expanded by remember { mutableStateOf(false) }
	val currencyVisualTransformation = remember { ThousandsSeparatorVisualTransformation() }
	var textFieldValueState by remember {
		mutableStateOf(TextFieldValue(text = amount, selection = TextRange(amount.length)))
	}
	var textFieldFocused by remember { mutableStateOf(false) }

	Box(
		modifier = modifier
			.border(
				width = animateDpAsState(if (textFieldFocused) 2.dp else 1.dp).value,
				color = animateColorAsState(
					if (textFieldFocused) MaterialTheme.colorScheme.primary
					else MaterialTheme.colorScheme.outline
				).value,
				shape = RoundedCornerShape(8.dp)
			)
			.padding(top = 4.dp, bottom = 4.dp, start = 16.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			// Currency Circle and Dropdown
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clickable { onCurrencyClick() }
					.weight(0.5f)
			) {
				Image(
					painter = painterResource(id = R.drawable.ic_launcher_background),
					modifier = Modifier
						.size(24.dp)
						.clip(CircleShape)
						.background(Color.LightGray),
					contentDescription = "Currency Icon"
				)

				Spacer(modifier = Modifier.width(8.dp))

				Text(
					text = selectedCurrency.uppercase().take(3),
					fontWeight = FontWeight.Bold,
					fontSize = 20.sp,
					color = MaterialTheme.colorScheme.primary
				)
				//Spacer(modifier = Modifier.width(8.dp))
				Spacer(modifier = Modifier.weight(1f))
				Icon(
					imageVector = Icons.Default.ArrowDropDown,
					contentDescription = "Select Currency"
				)
			}
			// Vertical divider
			Spacer(modifier = Modifier.width(8.dp))
			VerticalDivider(
				modifier = Modifier
					.height(32.dp)
					.width(1.dp),
				//.padding(start = 16.dp),
				color = Color.LightGray
			)
			//Spacer(modifier = Modifier.width(8.dp))

			// Amount field
			TextField(
				value = textFieldValueState,
				onValueChange = { newValue ->
					if (newValue.text.isEmpty() || newValue.text.matches(Regex("^\\d*\\.?\\d{0,4}$"))) {
						textFieldValueState = TextFieldValue(
							text = newValue.text,
							selection = TextRange(newValue.text.length)
						)
						onAmountChange(newValue.text)
					}
				},
				visualTransformation = currencyVisualTransformation,
				modifier = Modifier
					.weight(1f)
					.onFocusChanged { focusState ->
						textFieldFocused = focusState.isFocused
						if (focusState.isFocused) {
							//force cursor position to the end
							textFieldValueState = textFieldValueState.copy(
								selection = TextRange(textFieldValueState.text.length)
							)
						}
					},
				textStyle = LocalTextStyle.current.copy(
					textAlign = TextAlign.End,
					fontSize = 20.sp,
					fontWeight = FontWeight.SemiBold
				),
				singleLine = true,
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Number
				),
				placeholder = {
					Text(
						modifier = Modifier.fillMaxWidth(),
						text = placeholder,
						color = LocalContentColor.current.copy(alpha = 0.7f),
						textAlign = TextAlign.End,
						fontSize = 20.sp,
						fontWeight = FontWeight.SemiBold
					)
				},
				colors = TextFieldDefaults.colors(
					focusedContainerColor = Color.Transparent,
					unfocusedContainerColor = Color.Transparent,
					disabledContainerColor = Color.Transparent,
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent
				)
			)
		}

		// Dropdown menu for currency selection
		/*DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false }
		) {
			val currencies = listOf("USD", "EUR", "GBP", "JPY", "AED", "INR", "CAD", "AUD")
			currencies.forEach { currency ->
				DropdownMenuItem(
					text = { Text(text = currency) },
					onClick = {
						onCurrencyChange(currency)
						expanded = false
					}
				)
			}
		}*/
	}
}

@Preview
@Composable
private fun CurrencyInputFieldPreview() {
	var fromCurrency by remember { mutableStateOf("usd") }
	var fromAmount by remember { mutableStateOf("") }

	Surface {
		CurrencyInputField(
			selectedCurrency = fromCurrency,
			onCurrencyClick = {},
			//onCurrencyChange = { fromCurrency = it },
			amount = fromAmount,
			onAmountChange = { fromAmount = it },
			placeholder = "1",
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp)
		)
	}
}