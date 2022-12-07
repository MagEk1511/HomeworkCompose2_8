
package com.example.homeworkcompose2_8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homeworkcompose2_8.ui.theme.HomeworkCompose2_8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeworkCompose2_8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        val newUser = User()
                        Registration()
                        EmailInput(newUser)
                        PasswordInput(newUser)
                        RegButton(newUser)
                    }
                }
            }
        }
    }
}


@Composable
fun Registration() {
    Text(
        text = "Регистрация", fontSize = 50.sp, modifier = Modifier.padding(vertical = 20.dp)
    )
}


@Composable
fun EmailInput(user: User) {

    var email by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Введите вашу почту:",
            fontSize = 30.sp,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
            },
            label = { Text(text = "Email:") },
            modifier = Modifier.padding(vertical = 5.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = "Email icon"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                user.email = email
                focusManager.clearFocus()
            })

        )

        if (!email.contains('@')) {
            Text(
                text = "Адрес почты должен содержать @",
                color = Color.Red,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }
}


@Composable
fun PasswordInput(user: User) {

    var password by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Введите ваш пароль:",
            fontSize = 30.sp,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { newPassword ->
                password = newPassword
            },
            label = { Text(text = "Password:") },
            modifier = Modifier.padding(vertical = 5.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, contentDescription = "Password icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {}
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.VisibilityOff
                    else Icons.Outlined.Visibility, contentDescription = "ShowPassword icon"
                )
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    user.password = password
                    focusManager.clearFocus()
                },
            )
        )

        if (password.length < 8) {
            Text(
                text = "Длина пароля должна быть не менее 8 символов",
                color = Color.Red,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }

}


@Composable
fun RegButton(user: User) {

    val mContext = LocalContext.current

    val i = Intent(mContext, MenuActivity::class.java)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {


        Button(
            onClick = {
                Log.d("ButtonDebugger", "Starting launching new activity")
                if (user.email.contains('@') && user.email != "" && user.password.length > 7){
                    i.putExtra("user", user)
                    mContext.startActivity(i)
                }

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
            modifier = Modifier.padding(vertical = 5.dp)

        ) {
            Text(
                text = "Сделать фото", color = Color.White
            )
        }
    }

}
