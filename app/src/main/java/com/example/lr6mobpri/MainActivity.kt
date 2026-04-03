package com.example.lr6mobpri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lr6mobpri.ui.theme.Lr6mobpriTheme
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp


//Выполнили Челяпов и Кузнецов
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lr6mobpriTheme {
                RecipeCard()
            }
        }
    }
}

// 1.1 Первый экран
@Composable
fun GreetingScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Привет, Compose!")
        Button(onClick = { }) {
            Text("Нажми")
        }
        // Дополнительные элементы для 1.2
        Text("Дополнительный текст 1")
        Text("Дополнительный текст 2")
        Button(onClick = { }) { Text("Ещё кнопка") }

        StatsRow()
        Spacer(modifier = Modifier.height(8.dp))
        OverlayBadge()
    }
}


// 1.2 Row – горизонтальная статистика
@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Лекций: 19")
        Text("Практик: 31")
        Text("Курс: 3")
    }
}

// 1.2 Box – наложение бейджа
@Composable
fun OverlayBadge() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Центральный текст")
        Text(
            text = "NEW",
            modifier = Modifier.align(Alignment.TopEnd),
            color = Color.Red
        )
    }
}

// 1.3 Экран "О приложении"
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("О приложении", modifier = Modifier.fillMaxWidth())
        Text("Это приложение создано в рамках лабораторной работы.")
        Text("Используются: Compose, модификаторы, превью.")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { }) { Text("Версия") }
            Button(onClick = { }) { Text("Лицензия") }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingScreenPreview() {
    GreetingScreen()
}

@Preview(name = "Светлая тема", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AboutScreenLightPreview() {
    AboutScreen()
}

@Preview(name = "Тёмная тема", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutScreenDarkPreview() {
    Lr6mobpriTheme(darkTheme = true) {
        AboutScreen()
    }
}

// 2.2 Preview на разных устройствах
@Composable
fun PreviewPhoneContent() {
    AboutScreen()
}

@Preview(device = Devices.PIXEL_4, name = "Телефон")
@Composable
private fun PreviewOnPhone() {
    PreviewPhoneContent()
}

@Preview(device = Devices.NEXUS_9, name = "Планшет")
@Composable
private fun PreviewOnTablet() {
    PreviewPhoneContent()
}

// 3.1 + 3.2 Стилизованная карточка с фоном, скруглением, тенью
@Composable
fun StyledCard() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(12.dp))
            .shadow(4.dp, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clickable {
                Log.d("StyledCard", "Карточка нажата")
                Toast.makeText(context, "Карточка нажата", Toast.LENGTH_SHORT).show()
            }
            .semantics { contentDescription = "Карточка с заголовком и кнопкой" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Заголовок карточки", modifier = Modifier.fillMaxWidth())
        Text("Подзаголовок: дополнительная информация")
        Button(onClick = { }) { Text("Действие") }
    }
}

@Preview(name = "StyledCard светлая")
@Composable
private fun StyledCardLightPreview() {
    StyledCard()
}

@Preview(name = "StyledCard тёмная", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StyledCardDarkPreview() {
    Lr6mobpriTheme(darkTheme = true) {
        StyledCard()
    }
}

//композиция ui
@Composable
fun ProfileHeader(name: String, role: String) {
    Column {
        Text(name, modifier = Modifier.fillMaxWidth())
        Text(role, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ProfileStat(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Text(value)
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileHeader("Иван Иванов", "Студент")
        ProfileStat("Курс", "3")
        ProfileStat("Группа", "ПР313")
        Button(onClick = { }) { Text("Редактировать") }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}

//5. Карточка рецепта

@Composable
fun RecipeHeader(title: String, description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, modifier = Modifier.fillMaxWidth())
        Text(description, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun RecipeMeta(time: String, servings: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("⏱️ $time мин")
        Text("🍽️ $servings порции")
    }
}

@Composable
fun RecipeCard() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFF5E6))
            .clickable {
                Toast.makeText(context, "Открыть рецепт", Toast.LENGTH_SHORT).show()
            }
            .semantics { contentDescription = "Карточка рецепта" }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RecipeHeader(
                title = "Паста Карбонара",
                description = "Классический итальянский рецепт с гуанчиале и яйцом."
            )
            RecipeMeta(time = "20", servings = "2")
            Button(
                onClick = { /* переход к рецепту */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Рецепт")
            }
        }
    }
}

// Превью для финального задания
@Preview(name = "RecipeCard светлая", showBackground = true)
@Composable
private fun RecipeCardLightPreview() {
    RecipeCard()
}

@Preview(name = "RecipeCard тёмная", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RecipeCardDarkPreview() {
    Lr6mobpriTheme(darkTheme = true) {
        RecipeCard()
    }
}
