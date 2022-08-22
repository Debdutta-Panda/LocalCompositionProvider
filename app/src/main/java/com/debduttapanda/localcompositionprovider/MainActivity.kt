package com.debduttapanda.localcompositionprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.debduttapanda.localcompositionprovider.ui.theme.LocalCompositionProviderTheme

class MainActivity : ComponentActivity() {
    //declare the composition local, i used plain string value
    private val localMessage = compositionLocalOf { "Base text" }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalCompositionProviderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //LocalAlpha()
                    //LocalColor()
                    //MixedLocal()
                    CustomLocalCompositionProvider()
                }
            }
        }
    }

    @Composable
    fun CustomLocalCompositionProvider(){
        Column(){
            Text(localMessage.current)
            CompositionLocalProvider(
                localMessage provides "${localMessage.current} attacment1"
            ) {
                Column(){
                    Text(localMessage.current)
                    CompositionLocalProvider(
                        localMessage provides "${localMessage.current} attacment2"
                    ) {
                        Column(){
                            Text(localMessage.current)
                            CompositionLocalProvider(
                                localMessage provides "${localMessage.current} attacment3"
                            ) {
                                Column(){
                                    Text(localMessage.current)
                                }
                            }
                            Column(){
                                Text(localMessage.current)
                                CompositionLocalProvider(
                                    localMessage provides "${localMessage.current} attacment4"
                                ) {
                                    Column(){
                                        Text(localMessage.current)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LocalAlpha(){
        Column {
            Text("Normal alpha")//normal
            CompositionLocalProvider(LocalContentAlpha provides 0.3f) {
                Text("0.3 alpha")
                CompositionLocalProvider(LocalContentAlpha provides 0.1f) {
                    Text("0.1 alpha")
                }
                CompositionLocalProvider(LocalContentAlpha provides 0.2f) {
                    Text("0.2 alpha")
                }
            }
        }
    }

    @Composable
    fun LocalColor(){
        Column {
            Text("Normal color")
            CompositionLocalProvider(LocalContentColor provides Color.Cyan) {
                Text("Cyan color")
                CompositionLocalProvider(LocalContentColor provides Color.Green) {
                    Text("Green color")
                }
                CompositionLocalProvider(LocalContentColor provides Color.Red) {
                    Text("Red color")
                }
            }
        }
    }

    @Composable
    fun MixedLocal(){
        Column {
            Text("Normal")
            CompositionLocalProvider(
                LocalContentColor provides Color.Cyan,
                LocalTextStyle provides TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontSize = 32.sp
                )
            ) {
                Text("Cyan color")
                CompositionLocalProvider(
                    LocalContentColor provides Color.Green,
                    LocalTextStyle provides TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                ) {
                    Text("Green color")
                }
                CompositionLocalProvider(LocalContentColor provides Color.Red) {
                    Text("Red color")
                }
            }
        }
    }
}