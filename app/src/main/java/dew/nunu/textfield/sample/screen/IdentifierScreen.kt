package dew.nunu.textfield.sample.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IdentifierScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "IdentifierTextField")
        Spacer(modifier = Modifier.height(16.dp))
        IdentifierTextField()
    }
}

@Composable
fun IdentifierTextField() {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = {
            if (it.length <= 7) {
                text = it
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(16.dp),
        textStyle = TextStyle.Default.copy(fontSize = 32.sp),
        visualTransformation = IdentifierTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )
}

class IdentifierTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.text.isEmpty()) {
            val placeholder = buildAnnotatedString {

                withStyle(SpanStyle(color = Color.LightGray)) {
                    append("6글자 ")
                }
                withStyle(SpanStyle(color = Color.Black)) {
                    append("-")
                }
                withStyle(SpanStyle(color = Color.LightGray)) {
                    append(" •")
                }
                withStyle(SpanStyle(color = Color.Black)) {
                    append("••••••")
                }
            }
            return TransformedText(placeholder, object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = 0
                override fun transformedToOriginal(offset: Int): Int = 0
            })
        } else {
            val originalText = text.text
            val transformedText = buildAnnotatedString {
                append(originalText.take(6))
                append("-")
                if (originalText.length >= 7) {
                    append(originalText.drop(6).take(1))
                } else {
                    withStyle(SpanStyle(color = Color.LightGray)) {
                        append("•")
                    }
                }
                append("•".repeat(6))
            }

            return TransformedText(transformedText, object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return if (offset <= 5) offset else offset + 1
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return if (originalText.length <= 6) {
                        originalText.length
                    } else {
                        7
                    }
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterUserTextFieldPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "IdentifierTextField")
        Spacer(modifier = Modifier.height(16.dp))
        IdentifierTextField()
    }
}
