package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.R

@Composable
fun StartScreen(
    onIRCalcButtonClicked: () -> Unit,
    onCompatibilityCheckButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(modifier = Modifier
            .size(350.dp)
            .clip(RectangleShape)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(30.dp)),
            painter = painterResource(id = R.drawable.img1),
            contentDescription = "Circular image")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome to NeoCheck where you can check compatibility of IV Fluids",
            color = Color.DarkGray,
            fontFamily = FontFamily.Serif,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 17.sp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(onClick = { onCompatibilityCheckButtonClicked()},
            modifier = Modifier.height(60.dp)) {
            Text(text = "Check Compatibility",fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(onClick = { onIRCalcButtonClicked() },
            modifier = Modifier.height(60.dp)) {
            Text(text = "Infusion Rate Calculator",fontSize = 20.sp)
        }
    }
}