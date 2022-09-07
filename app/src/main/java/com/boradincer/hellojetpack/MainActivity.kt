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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.boradincer.hellojetpack.data.model.Message
import com.boradincer.hellojetpack.data.model.SampleData
import com.boradincer.hellojetpack.ui.theme.*
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (header, middle, footer) = createRefs()

                Surface(modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                }) {
                    ProfileCard("Kate", "Available")
                }

                Surface(modifier = Modifier.constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                }) {
                    Row(modifier = Modifier.background(MainBg)) {
                        MessageTextBox()
                    }
                }

                Surface(modifier = Modifier.constrainAs(middle) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(footer.top)
                    height = Dimension.fillToConstraints
                }) {
                    Conversation(messages = SampleData.conversationSample)
                }
            }
        }
    }
}

@Composable
fun MessageTextBox() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)) {
        val (mainPart, sendBtn) = createRefs()

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(sendBtn) {
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                Icons.Default.Send,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.clip(CircleShape).background(HeaderGreenLight).padding(8.dp).size(18.dp)
            )
        }

        Card(
            modifier = Modifier
                //.fillMaxWidth()
                .constrainAs(mainPart) {
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(sendBtn.start)
                    top.linkTo(sendBtn.top)
                    bottom.linkTo(sendBtn.bottom)
                    width = Dimension.fillToConstraints
                }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(Color.White)) {
                Column(
                    Modifier
                        .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 4.dp)
                        .clickable { /*TODO*/ }) {
                    Icon(
                        Icons.Default.SentimentSatisfiedAlt,
                        contentDescription = "Stickers button",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(24.dp)
                            .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp)
                    )
                }
                Column(
                    Modifier
                        .padding(start = 4.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)
                        .clickable { /*TODO*/ }) {
                    Icon(Icons.Default.AttachFile,
                        contentDescription = "Attach button",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(24.dp)
                            .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp)
                    )
                }
                SimpleTextField()
            }
        }
    }

}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(
        modifier = Modifier
            .background(color = MainBg),
        reverseLayout = true,
    ) {
        items(messages.reversed()) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if(message.fromMe()) Alignment.End else Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .width((LocalConfiguration.current.screenWidthDp - 50).dp)
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = MessageCorners,
                        topEnd = MessageCorners,
                        bottomStart = if (message.fromMe()) MessageCorners else ZeroDp,
                        bottomEnd = if (message.fromMe()) ZeroDp else MessageCorners
                    )
                )
                .background(color = if (message.fromMe()) OutgoingMessage else LightBg)
                .clickable { isExpanded = !isExpanded }
                .padding(horizontal = 9.dp, vertical = 6.dp),) {
            Text(
                text = message.message,
                color = PrimaryText,
                fontSize = 14.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 5
            )
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                DetailText(text = message.time, fontSize = 10)
            }
        }
    }
}

@Composable
fun PrimaryText(text: String, maxLines: Int = 1, fontSize: Int = 16, color: Color = PrimaryText) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        fontSize = fontSize.sp
    )
}

@Composable
fun DetailText(text: String, maxLines: Int = 1, fontSize: Int = 12, color: Color = DetailText) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        fontSize = fontSize.sp
    )
}

@Composable
fun ProfileCard(name: String, status: String) {
    Row(modifier = Modifier
        .background(color = HeaderGreenLight)
        .padding(vertical = 8.dp)
        .padding(end = 16.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            Modifier
                .padding(horizontal = 6.dp)
                .clickable { /*TODO*/ }) {
            Icon(Icons.Default.ChevronLeft,
                contentDescription = "Attach button",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(32.dp)
                    .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )
        Column(Modifier.padding(start = 8.dp)) {
            PrimaryText(text = name, color = Color.White)
            DetailText(text = status, color = Color.White)
        }
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Box(contentAlignment = Alignment.CenterStart) {
        if(text.text.isEmpty())
            PrimaryText("Message", color = DetailText, fontSize = 16)
        BasicTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        )
    }

}

@Preview
@Composable
fun PreviewMessageTextBox() {
    MessageTextBox()
}

@Preview
@Composable
fun PreviewProfileCard() {
    ProfileCard("Kate", "Available")
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("Kate", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "14:05"))
}

@Preview
@Composable
fun PreviewMessageCard2() {
    MessageCard(Message("Me", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "14:05"))
}