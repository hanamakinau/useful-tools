package jp.hanamakinau.usefultools

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform