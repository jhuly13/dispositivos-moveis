// ARQUIVO: Jhuly_App/build.gradle.kts (Raiz do Projeto)
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // ADICIONE ESTA LINHA COM A VERSÃO:
    id("com.google.gms.google-services") version "4.4.2" apply false

}
