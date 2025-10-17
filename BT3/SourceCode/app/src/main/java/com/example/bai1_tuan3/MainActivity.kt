package com.example.bai1_tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.font.FontStyle
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "jetpack_screen") {
        composable("jetpack_screen") { JetpackScreen(navController) }
        composable("ui_list_screen") { UIComponentsListScreen(navController) }
        composable("text_detail_screen") { TextDetailScreen(navController) }
        composable("image_list_screen") { ImageListScreen(navController) }
        composable("input_screen") { TextFieldUI(navController) }
        composable("row_layout_screen") { RowLayoutScreen(navController) }
    }
}
@Composable
fun JetpackScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.jetpack_logo),
            contentDescription = "Jetpack Logo",
            modifier = Modifier.size(550.dp),
            contentScale = ContentScale.Fit
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Jetpack Compose",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                navController.navigate("ui_list_screen")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "I'm ready", color = Color.White, fontSize = 16.sp)
        }
    }
}
@Composable
fun UIComponentsListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            color = Color(0xFF2196F3),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // --- Display ---
        Text(text = "Display", fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        ComponentItem(
            title = "Text",
            description = "Displays text",
            onClick = { navController.navigate("text_detail_screen") }
        )
        ComponentItem(
            title = "Image",
            description = "Displays an image",
            onClick = { navController.navigate("image_list_screen") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Input ---
        Text(text = "Input", fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        ComponentItem(
            title = "TextField",
            description = "Input field for text",
            onClick = { navController.navigate("input_screen") }
        )
        ComponentItem(title = "PasswordField", description = "Input field for passwords")

        Spacer(modifier = Modifier.height(16.dp))

        // --- Layout ---
        Text(text = "Layout", fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        ComponentItem(title = "Column", description = "Arranges elements vertically")
        ComponentItem(
            title = "Row",
            description = "Arranges elements horizontally",
            onClick = { navController.navigate("row_layout_screen") }
        )
    }
}

@Composable
fun ComponentItem(title: String, description: String, onClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD6EAF8), shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
            .padding(vertical = 8.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it }
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = description, color = Color.DarkGray, fontSize = 14.sp)
    }
    Spacer(modifier = Modifier.height(8.dp))
}
@Composable
fun TextDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Thanh tiêu đề có nút quay lại
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("<", color = Color(0xFF2196F3), fontSize = 20.sp)
            }
            Text(
                text = "Text Detail",
                color = Color(0xFF2196F3),
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        // Nội dung chính
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("The ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick ")
                    }

                    withStyle(style = SpanStyle(color = Color(0xFF8B4513), fontWeight = FontWeight.Bold)) {
                        append("Brown\n")
                    }
                    append("fox j u m p s ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over\n")
                    }
                    append("the ")
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)) {
                        append("lazy")
                    }
                    append(" dog.")
                },
                fontSize = 50.sp,
                lineHeight = 60.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ImageListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("<", color = Color(0xFF2196F3), fontSize = 20.sp)
            }
            Text(
                text = "Images",
                color = Color(0xFF2196F3),
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(Modifier.height(16.dp))
        // Ảnh 1 - từ URL
        Image(
            painter = rememberAsyncImagePainter("https://cdn.tgdd.vn/Files/2021/04/18/1344308/tim-hieu-giong-cho-alaska-nguon-goc-dac-diem-cach-nuoi-bang-gia-202203281549087060.jpg"),
            contentDescription = "Image from URL",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))

        // Ảnh 2 - từ local (drawable)
        Image(
            painter = painterResource(id = R.drawable.download), // thay bằng tên ảnh của bạn
            contentDescription = "Local Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun TextFieldUI(navController: NavHostController) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔙 Thanh tiêu đề
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("<", color = Color(0xFF2196F3), fontSize = 20.sp)
            }
            Text(
                text = "TextField",
                color = Color(0xFF2196F3),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        // 📝 Ô nhập liệu
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("Thông tin nhập") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🔴 Dòng chữ mô tả
        Text(
            text = "Tự động cập nhật dữ liệu theo textfield",
            color = Color.Red,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🟢 Dữ liệu hiển thị theo input
        Text(
            text = inputText,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
@Composable
fun RowLayoutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔙 Thanh tiêu đề
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("<", color = Color(0xFF2196F3), fontSize = 20.sp)
            }
            Text(
                text = "Row Layout",
                color = Color(0xFF2196F3),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 🟦 Nội dung Row Layout
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(4) { rowIndex ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(3) { columnIndex ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(2f)
                                .background(
                                    if (columnIndex == 1) Color(0xFF4A90E2)
                                    else Color(0xFF90CAF9),
                                    shape = RoundedCornerShape(12.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}
