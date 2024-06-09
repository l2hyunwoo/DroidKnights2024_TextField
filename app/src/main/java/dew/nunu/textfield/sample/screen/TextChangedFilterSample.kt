package dew.nunu.textfield.sample.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dew.nunu.textfield.sample.ui.theme.TextFieldSampleTheme
import java.text.BreakIterator

typealias OnTextChanged = (String) -> Unit

private const val MAX_LENGTH = 7

@Composable
fun TextChangedFilterSample() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember { mutableStateOf("") }

        val normalTextChanged: OnTextChanged = { new ->
            if (new.length <= MAX_LENGTH) {
                text = new
            }
        }

        val betterTextChanged: OnTextChanged = {
            text = when {
                it.length <= MAX_LENGTH -> it
                text.length == MAX_LENGTH -> text
                else -> it.substring(0, MAX_LENGTH)
            }
        }

        val supportEmojiContainedSequenceTextChanged: OnTextChanged = { new ->
            text = when {
                new.length <= MAX_LENGTH -> new
                text.length == MAX_LENGTH -> text
                else -> {
                    val breakIterator = BreakIterator.getCharacterInstance()
                    breakIterator.setText(new)
                    var end = 0
                    while (true) {
                        val newEnd = breakIterator.next()
                        if (newEnd == BreakIterator.DONE || newEnd > MAX_LENGTH) {
                            break
                        }
                        end = newEnd
                    }
                    new.substring(0, end)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            PrimitiveFilterTextField()
            Spacer(modifier = Modifier.height(16.dp))
            GoodFilterTextField()
            Spacer(modifier = Modifier.height(16.dp))
            BestFilterTextField()
        }
    }
}

@Composable
private fun PrimitiveFilterTextField() {
    var text by remember { mutableStateOf("") }

    val normalTextChanged: OnTextChanged = { new ->
        if (new.length <= MAX_LENGTH) {
            text = new
        }
    }

    BasicTextField(
        value = text,
        onValueChange = normalTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
private fun GoodFilterTextField() {
    var text by remember { mutableStateOf("") }

    val betterTextChanged: OnTextChanged = {
        text = when {
            it.length <= MAX_LENGTH -> it
            text.length == MAX_LENGTH -> text
            else -> it.substring(0, MAX_LENGTH)
        }
    }

    TextField(
        value = text,
        onValueChange = betterTextChanged,
        label = { Text("Better Text Changed") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
private fun BestFilterTextField() {
    var text by remember { mutableStateOf("") }

    val supportEmojiContainedSequenceTextChanged: OnTextChanged = { new ->
        text = when {
            new.length <= MAX_LENGTH -> new
            text.length == MAX_LENGTH -> text
            else -> {
                val breakIterator = BreakIterator.getCharacterInstance()
                breakIterator.setText(new)
                var end = 0
                while (true) {
                    val newEnd = breakIterator.next()
                    if (newEnd == BreakIterator.DONE || newEnd > MAX_LENGTH) {
                        break
                    }
                    end = newEnd
                }
                new.substring(0, end)
            }
        }
    }

    TextField(
        value = text,
        onValueChange = supportEmojiContainedSequenceTextChanged,
        label = { Text("Best Text Changed") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PrimitiveFilterTextFieldPreview() {
    TextFieldSampleTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimitiveFilterTextField()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GoodFilterTextFieldPreview() {
    TextFieldSampleTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoodFilterTextField()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BestFilterTextFieldPreview() {
    TextFieldSampleTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BestFilterTextField()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextChangedFilterSamplePreview() {
    TextFieldSampleTheme {
        TextChangedFilterSample()
    }
}
