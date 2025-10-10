package com.example.bt2_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Dùng MaterialTheme mặc định để không phụ thuộc file theme khác
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    Bai1Screen()
                }
            }
        }
    }
}

@Composable
fun Bai1Screen() {
    var hoTen by remember { mutableStateOf("") }
    var tuoi by remember { mutableStateOf("") }
    var ketQua by remember { mutableStateOf("") }
    var thongBaoLoi by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "THỰC HÀNH 02_3",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            // Ô nhập họ tên
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Họ và tên", modifier = Modifier.width(80.dp))
                TextField(
                    value = hoTen,
                    onValueChange = { hoTen = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Ô nhập tuổi
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tuổi", modifier = Modifier.width(80.dp))
                TextField(
                    value = tuoi,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() } || input.isEmpty()) {
                            tuoi = input
                            thongBaoLoi = ""
                        } else {
                            thongBaoLoi = "Tuổi chỉ được nhập số!"
                        }
                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }
        }

        if (thongBaoLoi.isNotEmpty()) {
            Text(
                text = thongBaoLoi,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (hoTen.trim().isEmpty() || tuoi.trim().isEmpty()) {
                    ketQua = "Vui lòng nhập đầy đủ thông tin!"
                } else {
                    val age = tuoi.toIntOrNull()
                    ketQua = when {
                        age == null -> "Tuổi không hợp lệ!"
                        age > 65 -> "Người già"
                        age in 6..65 -> "Người lớn"
                        age in 2..5 -> "Trẻ em"
                        age >= 0 -> "Em bé"
                        else -> "Tuổi không hợp lệ!"
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
        ) {
            Text("Kiểm tra", color = Color.White)
        }

        if (ketQua.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = ketQua,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
