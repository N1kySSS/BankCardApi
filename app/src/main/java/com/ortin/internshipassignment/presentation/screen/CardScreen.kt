package com.ortin.internshipassignment.presentation.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ortin.internshipassignment.R
import com.ortin.internshipassignment.presentation.viewmodel.CardViewModel
import com.ortin.internshipassignment.ui.theme.InternshipAssignmentTheme
import com.ortin.internshipassignment.ui.theme.LightGrey
import com.ortin.internshipassignment.ui.theme.Silver

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CardScreen() {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val viewModel = viewModel<CardViewModel>(factory = CardViewModel.factory)
    val card by viewModel.cardInfo.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val onValueChanged: (String) -> Unit = viewModel::onSearchTextChange

    val listOfText = remember {
        mutableStateOf(
            listOf(
                "Scheme / network: ${card?.scheme ?: "-"}", "Brand: ${card?.brand ?: "-"}",
                "Card number\nLength: ${card?.number?.length ?: "-"}\nLuhn: ${card?.number?.luhn ?: "-"}",
                "Type: ${card?.type ?: "-"}", "Prepaid: ${card?.prepaid ?: "-"}",
                "Country: ${card?.country?.emoji ?: "-"} ${card?.country?.name ?: "-"}\n(latitude: ${card?.country?.latitude ?: "-"}, longitude: ${card?.country?.longitude ?: "-"})"
            )
        )
    }

    LaunchedEffect(
        card
    ) {
        listOfText.value = listOf(
            "Scheme / network: ${card?.scheme ?: "-"}", "Brand: ${card?.brand ?: "-"}",
            "Card number\nLength: ${card?.number?.length ?: "-"}\nLuhn: ${card?.number?.luhn ?: "-"}",
            "Type: ${card?.type ?: "-"}", "Prepaid: ${card?.prepaid ?: "-"}",
            "Country: ${card?.country?.emoji ?: "-"} ${card?.country?.name ?: "-"}\n(latitude: ${card?.country?.latitude ?: "-"}, longitude: ${card?.country?.longitude ?: "-"})"
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Card(
            modifier = Modifier
                .widthIn(max = 240.dp)
                .heightIn(max = 144.dp)
                .fillMaxWidth(),
            border = BorderStroke(4.dp, Color.Gray),
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.gold_card_background),
                    contentDescription = "Фон",
                    contentScale = ContentScale.Crop,
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = if (viewModel.searchText.value == "") "Card Number" else viewModel.searchText.value,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Silver,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(all = 8.dp),
                    text = if (card?.scheme != null) "${card?.scheme}" else "Scheme: no info",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Silver,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(all = 8.dp),
                    text = if (card?.bank?.phone != null) "${card?.bank?.phone}" else "Phone: no info",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Silver,
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 320.dp)
                .heightIn(max = 48.dp)
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            value = searchText,
            onValueChange = onValueChanged,
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(
                    text = "Введите номер карты",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = LightGrey,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                    ),
                )
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        viewModel.updateCardInformation()
                        focusManager.clearFocus()
                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = "Поиск",
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = { onValueChanged("") },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.delete),
                        contentDescription = "Стереть текст",
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.updateCardInformation()
                    focusManager.clearFocus()
                }
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = LocalContentColor.current,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(all = 8.dp),
            text = "Additional card information",
            fontSize = 20.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(listOfText.value.size) { index: Int ->
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
                    text = "${index + 1}. ${listOfText.value[index]}",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (card?.bank?.city != null) {
                            val uri = Uri.parse("geo:${card?.bank?.city}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            context.startActivity(intent)
                        }
                    },
                text = "7. Bank: ${card?.bank?.name ?: "-"}, Town: ${card?.bank?.city ?: "-"}",
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (card?.bank?.url != null) {
                            val geo = Uri.parse(card?.bank?.url)
                            val intent = Intent(Intent.ACTION_VIEW, geo)
                            context.startActivity(intent)
                        }
                    },
                text = "Url: ${card?.bank?.url ?: "-"}",
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (card?.bank?.phone != null) {
                            val telephone = Uri.parse("tel:${card?.bank?.phone}")
                            val intent = Intent(Intent.ACTION_DIAL, telephone)
                            context.startActivity(intent)
                        }
                    },
                text = "Phone: ${card?.bank?.phone ?: "-"}",
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
            )
        }
    }
}

@Preview
@Composable
fun CardScreenPreview() {
    InternshipAssignmentTheme {
        CardScreen()
    }
}
