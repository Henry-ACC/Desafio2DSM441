package www.pepperinferno.com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var txtREmail: EditText
    private lateinit var txtRPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnLogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        txtREmail = findViewById(R.id.txtREmail)
        txtRPassword = findViewById(R.id.txtRPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)

        btnRegister.setOnClickListener {
            val email: String = txtREmail.text.toString()
            val password: String = txtRPassword.text.toString()
            signUp(email, password)
        }

        // Definir el clic del botón para abrir la actividad de registro
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun signUp(email: String, password: String) {

        // Validar los campos
        when {
            email.isEmpty() || password.isEmpty() -> {
                showAlertDialog("Error", "Por favor, complete todos los campos")
                return
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showAlertDialog("Error", "Por favor, ingrese una dirección de correo electrónico válida")
                return
            }
            password.length < 6 -> {
                showAlertDialog("Error", "La contraseña debe tener al menos 6 caracteres")
                return
            }
        }

        // Validar los campos
        if (email.isEmpty() || password.isEmpty()) {
            // Mostrar un mensaje si algún campo está vacío
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            // Mostrar un mensaje si el correo electrónico no es válido
            Toast.makeText(this, "Por favor, ingrese una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            // Mostrar un mensaje si la contraseña es demasiado corta
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso, actualizar la interfaz de usuario con la información del usuario registrado
                    val user = auth.currentUser
                    Log.d(TAG, "signUpWithEmail:success, User: $user")
                    // Aquí puedes redirigir a la siguiente actividad o realizar otras operaciones después del registro exitoso
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Si el registro falla, mostrar un mensaje al usuario.
                    Log.w(TAG, "signUpWithEmail:failure", task.exception)
                    // Aquí puedes mostrar un mensaje de error al usuario, por ejemplo, Toast
                    showAlertDialog("Error", "signUpWithEmail:failure" + task.exception)

                }
            }
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}