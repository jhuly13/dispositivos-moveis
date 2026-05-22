package br.com.faculdade.imepac

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormCadastro : AppCompatActivity() {

    private lateinit var edit_nome: EditText
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
    private lateinit var btn_cadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_cadastro)

        // Esconder a barra de suporte (ActionBar)
        supportActionBar?.hide()

        // Inicializar componentes
        edit_nome = findViewById(R.id.edit_nome)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_password) // Verifique se no XML o ID é edit_password ou edit_senha
        btn_cadastrar = findViewById(R.id.bt_cadastrar) // Verifique se no XML o ID é bt_cadastrar ou btn_cadastrar

        btn_cadastrar.setOnClickListener { view ->
            val nome = edit_nome.text.toString()
            val email = edit_email.text.toString()
            val senha = edit_senha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                cadastrarUsuario(view)
            }
        }
    }

    private fun cadastrarUsuario(view: View) {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { tarefa ->
                if (tarefa.isSuccessful) {
                    val snackbar = Snackbar.make(view, "Cadastro realizado com sucesso!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.BLUE)
                    snackbar.show()

                    // Ir para a tela de login após sucesso
                    val telaLogin = Intent(this, FormLogin::class.java)
                    startActivity(telaLogin)
                    finish() // Fecha a tela de cadastro para não voltar ao clicar em "voltar"
                } else {
                    val snackbar = Snackbar.make(view, "Erro ao cadastrar usuário!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }
    }
}