package br.com.faculdade.imepac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Define o layout da tela
        setContentView(R.layout.activity_form_login)

        // Esconder a barra de cima para um visual mais limpo
        supportActionBar?.hide()

        // 2. Configura o ajuste de tela (System Bars)
        val mainView = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- LÓGICA DO BOTÃO ENTRAR ---
        val btnEntrada = findViewById<Button>(R.id.bt_entrada)
        btnEntrada.setOnClickListener {
            val intent = Intent(this, TelaPerfil::class.java)
            // Se você tiver o nome aqui, você envia assim:
            intent.putExtra("NOME_USER", "Nome que veio do banco")
            startActivity(intent)
        }

        // --- LÓGICA DO LINK PARA CADASTRO ---
        val linkFormCadastro = findViewById<TextView>(R.id.text_tela_cadastro)
        linkFormCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
    }
}