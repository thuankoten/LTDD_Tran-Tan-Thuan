package com.example.bai2_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Dùng MaterialTheme mặc định (không cần file theme riêng)
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    EmailCheckerScreen()
                }
            }
        }
    }
}

@Composable
fun EmailCheckerScreen() {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "Thực hành 02_2",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                message = "" // reset thông báo khi nhập lại
            },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Hiển thị thông báo lỗi hoặc thành công
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = messageColor,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
                    .padding(start = 25.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val input = email.trim()

                when {
                    input.isEmpty() -> {
                        message = "Email không hợp lệ"
                        messageColor = Color.Red
                    }
                    !input.contains("@") -> {
                        message = "Email không đúng định dạng"
                        messageColor = Color.Red
                    }
                    else -> {
                        message = "Bạn đã nhập email hợp lệ"
                        messageColor = Color(0xFF008000) // xanh lá
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(55.dp)
        ) {
            Text(text = "Kiểm tra", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailCheckerScreen() {
    MaterialTheme {
        EmailCheckerScreen()
    }
}
