package br.com.faculdade.imepac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class TelaPerfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_perfil)

        supportActionBar?.hide()

        // Ajuste de layout para as barras do sistema
        val mainView = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Referenciar os campos de texto
        val textNome = findViewById<TextView>(R.id.txt_nome_usuario)
        val textEmail = findViewById<TextView>(R.id.ic_email)
        val btnSair = findViewById<Button>(R.id.bt_sair)

        // 2. Recuperar dados do Firebase
        // Dentro do onCreate da TelaPerfil.kt
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
            textEmail.text = usuarioAtual.email

            // Tenta pegar o nome salvo no Firebase
            val nomeFirebase = usuarioAtual.displayName

            if (!nomeFirebase.isNullOrEmpty()) {
                textNome.text = nomeFirebase
            } else {
                // Se o Firebase não tiver o nome, tenta pegar o que foi enviado pela Intent
                val nomeIntent = intent.getStringExtra("NOME_USER")
                textNome.text = nomeIntent ?: "Usuário"
            }
        }

        // 3. Configurar botão de sair
        btnSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}