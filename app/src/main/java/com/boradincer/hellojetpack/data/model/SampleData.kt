package com.boradincer.hellojetpack.data.model

object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Colleague",
            "Test...Test...Test...",
            "22:07"
        ),
        Message(
        "Colleague",
        "List of Android versions:\n" +
                "Android KitKat (API 19)\n" +
                "Android Lollipop (API 21)\n" +
                "Android Marshmallow (API 23)\n" +
                "Android Nougat (API 24)\n" +
                "Android Oreo (API 26)\n" +
                "Android Pie (API 28)\n" +
                "Android 10 (API 29)\n" +
                "Android 11 (API 30)\n" +
                "Android 12 (API 31)\n",
            "22:10"
        ),
        Message(
        "Me",
        "I think Kotlin is my favorite programming language.\n" +
                "It's so much fun!",
            "22:14"
        ),
        Message(
        "Colleague",
        "Searching for alternatives to XML layouts...",
            "22:22"
        ),
        Message(
        "Me",
        "Hey, take a look at Jetpack Compose, it's great!\n" +
                "It's the Android's modern toolkit for building native UI." +
                "It simplifies and accelerates UI development on Android." +
                "Less code, powerful tools, and intuitive Kotlin APIs :)",
            "22:25"
        ),
        Message(
        "Me",
        "It's available from API 21+ :)",
            "22:39"
        ),
        Message(
        "Colleague",
        "Writing Kotlin for UI seems so natural, Compose where have you been all my life?",
            "22:43"
        ),
        Message(
        "Me",
        "Android Studio next version's name is Arctic Fox",
            "22:57"
        ),
        Message(
        "Me",
        "Android Studio Arctic Fox tooling for Compose is top notch ^_^",
            "22:59"
        ),
        Message(
        "Colleague",
        "I didn't know you can now run the emulator directly from Android Studio",
            "23:06"
        ),
        Message(
        "Colleague",
        "Compose Previews are great to check quickly how a composable layout looks like",
            "23:07"
        ),
        Message(
        "Me",
        "Previews are also interactive after enabling the experimental setting",
            "23:14"
        ),
        Message(
        "Me",
        "Have you tried writing build.gradle with KTS?",
            "23:14"
        ),
    )
}