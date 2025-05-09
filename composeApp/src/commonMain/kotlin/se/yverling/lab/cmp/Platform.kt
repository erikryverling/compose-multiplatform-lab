package se.yverling.lab.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
