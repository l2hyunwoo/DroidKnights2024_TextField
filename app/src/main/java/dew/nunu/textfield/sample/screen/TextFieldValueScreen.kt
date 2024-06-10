package dew.nunu.textfield.sample.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldValueScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(
                TextFieldValue()
            )
        }
        BasicTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            decorationBox = {
                Box(
                    Modifier.height(48.dp)
                        .background(color = Color.Blue.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    it()
                }
            }
        )
        Spacer(Modifier.height(16.dp))
        BasicTextField(
            value = text,
            onValueChange = { text = it }
        )
        Spacer(Modifier.height(16.dp))
        AnnotatedStringTextField()
        Spacer(Modifier.height(16.dp))
        SelectionTextField()
        Spacer(Modifier.height(16.dp))
        CompositionTextField()
    }
}

@Composable
private fun AnnotatedStringTextField() {
    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                annotatedString = buildAnnotatedString {
                    append("Hello ")
                    // push green text style so that any appended text will be green
                    pushStyle(SpanStyle(color = Color.Green))
                    // append new text, this text will be rendered as green
                    append("World")
                    pop()
                    // append a string without style
                    append("!")
                    // then style the last added word as red, exclamation mark will be red
                    addStyle(
                        SpanStyle(color = Color.Blue),
                        "Hello World".length,
                        this.length
                    )
                    toAnnotatedString()
                }
            )
        )
    }
    Text("AnnotatedString Sample")
    Spacer(Modifier.height(8.dp))
    BasicTextField(
        value = textFieldValue,
        onValueChange = { textFieldValue = it },
        readOnly = true,
        textStyle = TextStyle(fontSize = 24.sp),
        modifier = Modifier.background(Color.LightGray.copy(alpha = 0.2f))
    )
}

@Composable
private fun SelectionTextField() {
    var text by remember {
        mutableStateOf(
            TextFieldValue()
        )
    }
    Text("SelectionTextField Sample")
    Spacer(Modifier.height(8.dp))
    BasicTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(16.dp)
    )
    Spacer(Modifier.height(8.dp))
    Text("Selection: ${text.selection}")
}

@Composable
private fun CompositionTextField() {
    var text by remember {
        mutableStateOf(
            TextFieldValue()
        )
    }
    Text("CompositionTextField Sample")
    Spacer(Modifier.height(8.dp))
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
                .copy(composition = TextRange(0, 6))
        },
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(16.dp)
    )
    Spacer(Modifier.height(8.dp))
    Text("Composition: ${text.composition}")
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(16.dp)

    )
}

@Preview
@Composable
private fun PreviewTextFieldValueScreen() {
    TextFieldValueScreen()
}

@Preview
@Composable
private fun PreviewAnnotatedStringTextField() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnnotatedStringTextField()
    }
}

@Preview
@Composable
private fun PreviewSelectionTextFieldScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectionTextField()
    }
}

@Preview
@Composable
private fun PreviewCompositionTextFieldScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionTextField()
    }
}
