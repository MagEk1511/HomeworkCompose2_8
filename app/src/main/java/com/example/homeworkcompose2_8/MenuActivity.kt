package com.example.homeworkcompose2_8

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homeworkcompose2_8.ui.theme.HomeworkCompose2_8Theme


class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeworkCompose2_8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val user = intent.getSerializableExtra("user") as User
                    Log.d("UserLogger", user.email)
                    Log.d("UserLogger", user.password)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        Greeting(user)
                        ImageBox()
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(user: User) {
    Text(text = "Здравствуйте, ${user.email.replaceAfter('@', "").removeSuffix("@")}",
    fontSize = (45.sp),
    modifier = Modifier.padding(vertical = 20.dp))
}


@Composable
fun ImageBox() {
    Column() {

        val result = remember { mutableStateOf<Bitmap?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            result.value = it
        }



        Column() {

            result.value?.let {
                androidx.compose.foundation.Image(bitmap = it.asImageBitmap(),
                    contentDescription = null)
            }

            Button(onClick = {

                launcher.launch()

            }) {
                Text(text = "Сделать фото")
            }

        }

    }
}



