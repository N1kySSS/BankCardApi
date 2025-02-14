package com.ortin.internshipassignment.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ortin.internshipassignment.presentation.viewmodel.HistoryViewModel
import com.ortin.internshipassignment.ui.theme.CardNumber
import com.ortin.internshipassignment.ui.theme.InternshipAssignmentTheme

@Composable
fun HistoryScreen() {
    val viewModel = viewModel<HistoryViewModel>(factory = HistoryViewModel.factory)
    val listCards = viewModel.listOfCards.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(listCards.value) { item ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                border = BorderStroke(2.dp, Color.Gray),
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = item.cardNumber.toString(),
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = CardNumber,
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Number info: ${item.number}",
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Scheme: ${item.scheme}",
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Type: ${item.type}",
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Brand: ${item.brand}",
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Prepaid: ${item.prepaid}",
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Country info: ${item.country}",
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Bank info: ${item.bank}",
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    InternshipAssignmentTheme {
        HistoryScreen()
    }
}
