package se.yverling.lab.kmp.ui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
