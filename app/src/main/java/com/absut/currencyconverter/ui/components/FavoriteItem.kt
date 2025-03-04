package com.absut.currencyconverter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.absut.currencyconverter.R

@Composable
fun FavoriteItem(
	fromCurrency: String,
	toCurrency: String,
	onClick: () -> Unit
) {
	OutlinedCard(
		modifier = Modifier
		//.size(160.dp, 120.dp)
		//.padding(16.dp),
		,
		onClick = onClick
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier.padding(16.dp)
		) {
			// Currency circles
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center,
				modifier = Modifier.fillMaxWidth()
			) {
				Image(
					painter = painterResource(id = R.drawable.ic_launcher_background),
					modifier = Modifier
						.size(24.dp)
						.clip(CircleShape)
						.background(Color.LightGray)
						.border(
							width = 1.dp,
							color = MaterialTheme.colorScheme.surface,
							shape = CircleShape
						),
					contentDescription = "Currency Icon"
				)
				Box(
					modifier = Modifier
						.size(24.dp)
						.offset(x = (-8).dp)
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_launcher_background),
						modifier = Modifier
							.size(24.dp)
							.clip(CircleShape)
							.background(Color.LightGray)
							.border(
								width = 1.dp,
								color = MaterialTheme.colorScheme.surface,
								shape = CircleShape
							),
						contentDescription = "Currency Icon"
					)
				}
			}

			Spacer(modifier = Modifier.height(8.dp))

			// Currency codes with swap icon
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = fromCurrency.take(3),
					fontWeight = FontWeight.Bold,
					fontSize = 16.sp
				)

				Icon(
					imageVector = Icons.Default.ArrowDropDown,
					contentDescription = "Swap currencies"
				)

				Text(
					text = toCurrency.take(3),
					fontWeight = FontWeight.Bold,
					fontSize = 16.sp
				)
			}

			Spacer(modifier = Modifier.height(8.dp))

			// Exchange rate
			//todo here limit to 2 digit after decimal
			Text(
				text = "1 $fromCurrency = 22.34 $toCurrency",
				fontSize = 13.sp,
				maxLines = 2,
				overflow = TextOverflow.Ellipsis
			)
		}
	}
}

@Preview
@Composable
private fun FavoriteItemPreview() {
	Surface {
		FavoriteItem("AED", "INR") {

		}
	}
}