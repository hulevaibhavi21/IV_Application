package com.example.iv1.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.R
import java.text.NumberFormat

@Composable
fun IRScreen() {
    var volInput by remember {
        mutableStateOf("")
    }
    val volume = volInput.toDoubleOrNull() ?: 0.0

    var timeInput by remember {
        mutableStateOf("")
    }
    val time = timeInput.toDoubleOrNull() ?: 0.0

    var factorInput by remember {
        mutableStateOf("")
    }
    val dropFactor = factorInput.toDoubleOrNull() ?: 0.0

    val ir = calculateIR(volume, time, dropFactor)

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(32.dp),
        Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.ir_calc),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField(label = R.string.ir_vol,
            value = volInput,
            onValueChange = { volInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        EditNumberField(label = R.string.ir_time,
            value = timeInput,
            onValueChange = { timeInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        EditNumberField(label = R.string.ir_drop_factor,
            value = factorInput,
            onValueChange = { factorInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.ir_result, ir),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

fun calculateIR(volume: Double, time: Double, dropFactor: Double): String {
    val ir = (volume / time) * dropFactor
    return NumberFormat.getNumberInstance().format(ir)
}

@Composable
fun EditNumberField(@StringRes label: Int,
                    value: String,
                    onValueChange: (String) -> Unit,
                    keyboardOptions: KeyboardOptions,
                    keyboardActions: KeyboardActions,
                    ) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text (
            text = stringResource(id = label),
            modifier = Modifier.fillMaxWidth()
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true
    )
}

