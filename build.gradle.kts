plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.ktlint)
}
