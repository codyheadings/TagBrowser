package com.example.tagbrowser

import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tagbrowser.ui.theme.TagBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TagBrowserTheme {
                TagBrowser()
            }
        }
    }
}

val tags = listOf("Cats", "Dogs", "Birds", "Rabbits", "Snakes", "Bears",
    "Lizards", "Dolphins", "Whales", "Fish", "Frogs", "Turtles", "Weasels", "Ferrets", "Mice",
    "Hamsters", "Horses", "Foxes")

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TagBrowser() {
    val selectedTags = remember { mutableStateSetOf<String>() }

    Scaffold(
        topBar = { TopAppBar(title = {Text("Tag Browser")}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                tags.forEach { tag ->
                    val isSelected = tag in selectedTags
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) selectedTags.remove(tag)
                            else selectedTags.add(tag)
                        },
                        label = { Text(tag) }
                    )
                }
            }

            ElevatedButton(onClick = {
                selectedTags.clear()
                val randNum = (0 until tags.size).random()
                for (i in 0..randNum){
                    val randIdx = (0 until tags.size).random()
                    if (tags[randIdx] !in selectedTags){
                        selectedTags.add(tags[randIdx])
                    }
                }
            }) {
                Text("Random")
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Selected Tags", style = MaterialTheme.typography.titleSmall)
                if (selectedTags.isNotEmpty()) {
                    TextButton(onClick = { selectedTags.clear() }) {
                        Text("Clear")
                    }
                }
            }

            if (selectedTags.isEmpty()) {
                Text(
                    "No tags selected.",
                    style = MaterialTheme.typography.bodySmall
                )
            } else {
                FlowColumn(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachColumn = 8,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    selectedTags.forEach { tag ->
                        InputChip(
                            selected = true,
                            onClick = { selectedTags.remove(tag) },
                            label = { Text(tag) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TagBrowserTheme {
        TagBrowser()
    }
}