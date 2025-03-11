package com.absut.currencyconverter.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.absut.currencyconverter.R
import com.absut.currencyconverter.data.util.Resource
import com.absut.currencyconverter.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
	modifier: Modifier = Modifier,
	viewModel: MainViewModel,
	navController: NavController
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { /*Text(text = "Currency Converter")*/ },
				actions = {
					IconButton(onClick = {

					}) {
						Icon(
							imageVector = Icons.Default.Refresh,
							contentDescription = "Menu"
						)
					}
					IconButton(onClick = {}) {
						Icon(
							imageVector = Icons.Default.MoreVert,
							contentDescription = "Menu"
						)
					}
				},
			)
			Column(
				modifier =
					Modifier
						.windowInsetsPadding(TopAppBarDefaults.windowInsets)
						.padding(start = 16.dp, end = 16.dp, top = 36.dp)
			) {
				Text(
					text = "Convert",
					fontSize = 32.sp,
					fontWeight = FontWeight.SemiBold
				)
				Text(
					text = "Updated today 12:12 pm",
					fontSize = 12.sp,
					color = LocalContentColor.current.copy(alpha = 0.8f),
				)
			}

		},
	) { innerPadding ->
		Column(
			modifier = modifier
				.padding(innerPadding)
				.imeNestedScroll()
				.verticalScroll(rememberScrollState())
		) {
			CurrencyConverterScreen(viewModel = viewModel, navController = navController)
		}
	}
}

@Preview
@Composable
private fun HomeScreenPreview() {
	Surface {
		HomeScreen(navController = rememberNavController(), viewModel = viewModel<MainViewModel>())
	}
}
