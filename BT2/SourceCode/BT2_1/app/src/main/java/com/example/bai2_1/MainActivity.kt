package com.example.bai2_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai2_1.ui.theme.Bai21Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bai21Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    NumberScreen()
                }
            }
        }
    }
}

@Composable
fun NumberScreen() {
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    var numbers by remember { mutableStateOf(listOf<Int>()) }
    var errorMessage by remember { mutableStateOf("") }

    // Trạng thái cuộn
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Thực hành 02-1",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    errorMessage = "" // Reset lỗi khi người dùng nhập lại
                },
                label = { Text("Nhập vào số lượng") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    val text = inputValue.text.trim()
                    val n = text.toIntOrNull()
                    if (n == null || n <= 0) {
                        errorMessage = "Dữ liệu bạn nhập không hợp lệ"
                        numbers = emptyList()
                    } else {
                        errorMessage = ""
                        numbers = (1..n).toList()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text(text = "Tạo")
            }
        }

        // Hiển thị thông báo lỗi
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 5.dp, start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Hiển thị các nút đỏ, có thể cuộn xuống xem hết
        numbers.forEach { num ->
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .height(50.dp)
            ) {
                Text(text = num.toString(), color = Color.White, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(50.dp)) // tạo khoảng trống cuối danh sách
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumberScreen() {
    Bai21Theme {
        NumberScreen()
    }
}
