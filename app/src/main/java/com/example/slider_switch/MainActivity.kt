package com.example.slider_switch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slider_switch.ui.theme.Slider_SwitchTheme
import dalvik.annotation.optimization.CriticalNative

class MainActivity : ComponentActivity() {

    private var onExit = mutableStateOf(false)
    private var slider = mutableStateOf(false)
    private var text =  mutableStateOf("Нет доступа")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          enableEdgeToEdge()


             setContent {

                 if (onExit.value) { Alert() }

                 Start()




             }







    }


    @Composable
    private fun Start() {

           val pr = remember { mutableStateOf(100.dp) }

        Scaffold ( containerColor = Color.LightGray, topBar = toolBar)

        {

          Column (modifier = Modifier.fillMaxSize().padding(it), horizontalAlignment = Alignment.CenterHorizontally){

              Box (contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().height(400.dp).padding(12.dp).background(color = Color.White).border(width = 2.dp, color = Color.Black)){
            Text(text = text.value, fontSize = 22.sp, fontStyle = FontStyle.Italic, modifier = Modifier.fillMaxSize().
            verticalScroll(state = rememberScrollState()).padding(12.dp))

               CircularProgressIndicator(modifier = Modifier.size(pr.value))


              }

             Button(onClick = { if (slider.value) { text.value =  Base().text; pr.value = 0.dp  } else { text.value = "Нет доступа"; pr.value = 100.dp}},Modifier.padding(top = 18.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) { Text(text = "Загрузить данные") }

              Switch(checked = slider.value,onCheckedChange = { slider.value = if (slider.value) false else true }, modifier = Modifier.padding(top = 18.dp))
        }}




    }

    @OptIn(ExperimentalMaterial3Api::class)
    val toolBar = @Composable{
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray, titleContentColor = Color.White, actionIconContentColor = Color.White),
            title = { Text("Загрузка данных", fontSize = 28.sp, modifier =  Modifier.fillMaxWidth(),textAlign = TextAlign.Center)},
            actions = { Icon(Icons.Filled.ExitToApp, contentDescription = "", modifier = Modifier.padding(end = 12.dp).clickable ( onClick = {onExit.value = true})) })

    }


    @Composable
    private fun Alert() {

        AlertDialog(onDismissRequest =  { onExit.value = false }, containerColor = Color(getColor(R.color.purple_200)),
            confirmButton = { Button(onClick = { finishAffinity()}, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                border = BorderStroke(width = 2.dp, color = Color.Black))  { Text(text = "Да") }},
            text = { Text("Выйти из программы?", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 42.dp))},

            dismissButton = { Button(onClick = { onExit.value = false}, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                border = BorderStroke(width = 2.dp, color = Color.Black)
            )
            { Text(text = "Нет") }}
        )


    }

}











