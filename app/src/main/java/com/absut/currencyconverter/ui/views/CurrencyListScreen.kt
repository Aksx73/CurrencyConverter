package com.absut.currencyconverter.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.absut.currencyconverter.R
import com.absut.currencyconverter.domain.model.Currency

@Composable
fun CurrencyListItem(
    modifier: Modifier = Modifier,
    currency: Currency, onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Black, CircleShape),
            contentDescription = "Currency Icon"
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = currency.code)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = currency.name ?: "",
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun CurrencyListItemPreview() {
    Surface {
        CurrencyListItem(
            currency = Currency(code = "USD", name = "United States Dollar"),
            onClick = {}
        )
    }
}