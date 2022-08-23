package com.arema.suryeyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arema.suryeyapp.home.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null)
        {
            val Intent = Intent(this , MainActivity::class.java)
            startActivity(Intent)
            finish()
        }

            login_button.setOnClickListener {
                if(checkdata() == true )
                {
                    val email = emailornumber.text.toString()
                    val password = password.text.toString()

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            Toast.makeText(this , "Login Successful !" , Toast.LENGTH_LONG).show()
                            val Intent = Intent(this , MainActivity::class.java)
                            startActivity(Intent)
                            finish()
                        }else
                        {
                            Toast.makeText(this , "Login Failed ! ${it.exception}" , Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
    }

    private fun checkdata(): Boolean {

        var a = true

        if(emailornumber.text.toString().isNullOrEmpty())
        {
            emailornumber.error = "Required"
             a= false
        }


        if( password.text.toString().isNullOrEmpty())
        {
            password.error = "Required"
            a = false
        }else if( password.text.length < 6)
        {
            a = false
            password.error = "Password should be greater than 6 letters"
            Toast.makeText(this ,"Password should be greater than 6 letters" , Toast.LENGTH_SHORT ).show()

        }

        return a
    }
}