package com.boradincer.hellojetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.boradincer.hellojetpack.data.model.Message
import com.boradincer.hellojetpack.data.model.SampleData
import com.boradincer.hellojetpack.ui.theme.LightBg
import com.boradincer.hellojetpack.ui.theme.MainBg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                ProfileCard("Kate", "Available")
                Conversation(messages = SampleData.conversationSample)
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleTextField()
                    }
                }
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(modifier = Modifier.background(color = MainBg)) {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
        Text(
            text = message.message,
            color = com.boradincer.hellojetpack.ui.theme.PrimaryText,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = LightBg)
                .padding(horizontal = 8.dp, vertical = 5.dp),
            maxLines = if (isExpanded) Int.MAX_VALUE else 2
        )
    }
}

@Composable
fun PrimaryText(text: String, maxLines: Int = 1, fontSize: Int = 16) {
    Text(
        text = text,
        color = com.boradincer.hellojetpack.ui.theme.PrimaryText,
        maxLines = maxLines,
        fontSize = fontSize.sp
    )
}

@Composable
fun DetailText(text: String, maxLines: Int = 1, fontSize: Int = 12) {
    Text(
        text = text,
        color = com.boradincer.hellojetpack.ui.theme.DetailText,
        maxLines = maxLines,
        fontSize = fontSize.sp
    )
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(R.drawable.profile_picture),
        contentDescription = "Contact profile picture",
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ProfileCard(name: String, status: String) {
    Row(modifier = Modifier
        .background(color = LightBg)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileImage()
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            PrimaryText(text = name)
            DetailText(text = status)
        }
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    BasicTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        modifier = Modifier.background(color = LightBg)
    )
}

@Preview
@Composable
fun PreviewTextField() {
    SimpleTextField()
}

@Preview
@Composable
fun PreviewProfileCard() {
    ProfileCard("Kate", "Available")
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("Kate", "Lorem ipsum dolor sit amet, consectetur adipisicing elit."))
}