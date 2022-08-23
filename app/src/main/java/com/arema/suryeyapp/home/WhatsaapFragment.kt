package com.arema.suryeyapp.home

import android.app.ProgressDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arema.suryeyapp.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_whatsaap.*


class WhatsaapFragment : Fragment(R.layout.fragment_whatsaap) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Loading Data.....")
        progressDialog.show()


            FirebaseDatabase.getInstance().reference.child("AdminData").child("phone").get().addOnSuccessListener {

                progressDialog.dismiss()
                val contact = "+91 ${it.value.toString()}"

                    whatsaap_redirect_btn.setOnClickListener {

                        try {
                            openWhatsAppConversation(requireContext() , contact , "hi")
                        }catch (e : Exception)
                        {
                            Toast.makeText(context , "error opening whatsaap !" , Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }


    fun openWhatsAppConversation(context: Context, number: String, message: String?) {
        var number = number
        number = number.replace(" ", "").replace("+", "")
        val sendIntent = Intent("android.intent.action.MAIN")
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, message)
        sendIntent.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net")
        context.startActivity(sendIntent)
    }
    }
