package com.example.bai1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai1.R
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
// Nút Back (trái trên)
        IconButton(
            onClick = { /* TODO: Xử lý quay lại */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(50.dp) // kích thước nút
                .clip(CircleShape) // bo tròn nút
                .background(Color.LightGray) // màu nền
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_previous),
                contentDescription = "Back",
                tint = Color.Black // đổi màu icon
            )
        }

// Nút Edit (phải trên)
        IconButton(
            onClick = { /* TODO: Xử lý edit */ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFF90CAF9)) // màu xanh nhạt
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_edit),
                contentDescription = "Edit",
                tint = Color.White
            )
        }

        // Nội dung chính
        Column(
            modifier = Modifier.align(Alignment.CenterStart),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.thuan), // Thay ảnh avatar trong drawable
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Trần Tấn Thuận",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Sinh viên UTH đến từ Long An",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
