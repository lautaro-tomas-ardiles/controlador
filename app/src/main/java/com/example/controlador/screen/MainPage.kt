@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.example.controlador.screen

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.controlador.R
import com.example.controlador.ui.theme.Black
import com.example.controlador.ui.theme.Blue
import com.example.controlador.ui.theme.ControladorTheme
import com.example.controlador.ui.theme.DarkGreen
import com.example.controlador.ui.theme.DarkYellow
import com.example.controlador.ui.theme.LightGreen
import com.example.controlador.ui.theme.LightYellow

@Composable
fun TopBar() {
    var bluetooth by remember { mutableStateOf(true) }
    var ip by remember { mutableStateOf("") }

    TopAppBar(
        title = {},
        navigationIcon = {
            Switch(
                checked = bluetooth,
                onCheckedChange = { bluetooth = it },
                colors = SwitchDefaults.colors(
                    checkedBorderColor = DarkGreen,
                    checkedTrackColor = LightGreen,
                    checkedThumbColor = DarkGreen,
                    checkedIconColor = Black,

                    uncheckedBorderColor = DarkYellow,
                    uncheckedTrackColor = LightYellow,
                    uncheckedThumbColor = DarkYellow,
                    uncheckedIconColor = Black
                ),
                thumbContent = {
                    if (bluetooth){
                        Icon(
                            painter = painterResource(R.drawable.group_11),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }else {
                        Icon(
                            painter = painterResource(R.drawable.group_10),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (bluetooth) {
                    Text(
                        text = "Precione para conectar : ",
                        color = Black,
                        fontSize = 17.sp
                    )
                    IconButton(
                        onClick = { },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = LightYellow
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.group_11),
                            contentDescription = null,
                            tint = Black
                        )
                    }

                } else {
                    OutlinedTextField(
                        value = ip,
                        onValueChange = { ip = it },
                        placeholder = { Text(text = "ingrese la IP") },
                        minLines = 1,
                        trailingIcon = {
                            IconButton(
                                onClick = { }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = LightGreen
                        )
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Blue
        )
    )
}

@Composable
fun Button(
    direction: String,
    onPress: (String) -> Unit,
    onRelease: () -> Unit
) {
    val arrowDirection = when (direction) {
        "up" -> Icons.Default.KeyboardArrowUp
        "down" -> Icons.Default.KeyboardArrowDown
        "left" -> Icons.Default.KeyboardArrowLeft
        "right" -> Icons.Default.KeyboardArrowRight
        else -> Icons.Default.KeyboardArrowUp
    }

    Box(
        modifier = Modifier
            .size(70.dp)
            .background(
                DarkGreen,
                shape = CircleShape
            )
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        onPress(direction)  // Cuando se presiona
                        true
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        onRelease()  // Cuando se suelta
                        true
                    }
                    else -> false
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = arrowDirection,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Black
        )
    }
}

@Composable
fun Indicators(pressedButton: String) {
    val colorUp = if ("up" == pressedButton) DarkYellow else Blue
    val colorDown = if ("down" == pressedButton) DarkYellow else Blue
    val colorLeft = if ("left" == pressedButton) DarkYellow else Blue
    val colorRight = if ("right" == pressedButton) DarkYellow else Blue

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier.size(65.dp),
            tint = colorLeft
        )
        Column {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null,
                modifier = Modifier.size(65.dp),
                tint = colorUp
            )
            Spacer(Modifier.padding(25.dp))

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(65.dp),
                tint = colorDown
            )
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(65.dp),
            tint = colorRight
        )
    }
}

@Composable
fun GridButton() {
    var directionPressed by remember { mutableStateOf("") }

    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Button(
                direction = "up",
                onPress = { directionPressed = it },
                onRelease = { directionPressed = "" } // Al soltar, se limpia
            )
            Spacer(Modifier.padding(15.dp))

            Button(
                direction = "down",
                onPress = { directionPressed = it },
                onRelease = { directionPressed = "" }
            )

            Text(directionPressed)
        }
        Spacer(Modifier.padding(15.dp))

        Indicators(directionPressed)

        Spacer(Modifier.padding(15.dp))

        Row {
            Button(
                direction = "left",
                onPress = { directionPressed = it },
                onRelease = { directionPressed = "" }
            )
            Spacer(Modifier.padding(15.dp))

            Button(
                direction = "right",
                onPress = { directionPressed = it },
                onRelease = { directionPressed = "" }
            )
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopBar() },
        containerColor = Black
    ){ padding ->
        Column(
            Modifier.padding(padding)
        ) {
            GridButton()
        }
    }
}

@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun Preview() {
    ControladorTheme {
        MainScreen()
    }
}