package com.ortin.internshipassignment.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.internshipassignment.R
import com.ortin.internshipassignment.ui.theme.InternshipAssignmentTheme
import com.ortin.internshipassignment.ui.theme.Silver

@Composable
fun CardScreen() {
    val focusManager = LocalFocusManager.current

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
                    text = "Card Number",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Silver,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(all = 8.dp),
                    text = "Bank Name",
                    fontSize = 16.sp,
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
                .fillMaxWidth(),
            value = "",
            onValueChange = { },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
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
        Box(
            modifier = Modifier
                .widthIn(max = 320.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(all = 8.dp),
                text = "Additional card information",
                fontSize = 20.sp,
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
