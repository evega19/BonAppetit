package org.bedu.bonappetit

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.bedu.bonappetit.databinding.ActivityAct4ConektaBinding
import io.conekta.conektasdk.Card
import io.conekta.conektasdk.Conekta
import io.conekta.conektasdk.Token
import org.json.JSONObject

class Act4_Conekta : AppCompatActivity() {

    private lateinit var binding: ActivityAct4ConektaBinding

    private val PUBLIC_KEY = "key_eYvWV7gSDkNYXsmr"
    private val API_VERSION = "0.3.0"

    private var hasValidCardData: Boolean? = false
    private var cardName: String? = null
    private var cardNumber: String? = null
    private var cardCvc: String? = null
    private var cardMonth: String? = null
    private var cardYear: String? = null
    private var tokenIdTag: String? = null
    private var errorTag: String? = null
    private var uuidDeviceTag: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAct4ConektaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tokenIdTag = resources.getString(R.string.theTokenIdLabel) // The token id:
        errorTag = resources.getString(R.string.errorLabel) // Error:
        uuidDeviceTag = resources.getString(R.string.uuidDeviceLabel) // Uuid device:

        binding.btnTokenize.setOnClickListener { onPressTokenizeButton() }


    }

    override fun onStart() {
        super.onStart()
        enableInputs(true)
        enableProgressBar(false)
    }

    private fun enableInputs(isEnable: Boolean) {
        binding.btnTokenize.isEnabled = isEnable
        binding.numberText.isEnabled = isEnable
        binding.nameText.isEnabled = isEnable
        binding.monthText.isEnabled = isEnable
        binding.yearText.isEnabled = isEnable
        binding.cvcText.isEnabled = isEnable
    }

    private fun enableProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.shadowView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun getCardData() {
        hasValidCardData = true
        cardName = binding.nameText.text.toString()
        cardNumber = binding.numberText.text.toString()
        cardCvc = binding.cvcText.text.toString()
        cardMonth = binding.monthText.text.toString()
        cardYear = binding.yearText.text.toString()
        if (cardName!!.isEmpty() || cardNumber!!.isEmpty() || cardCvc!!.isEmpty()
            || cardMonth!!.isEmpty() || cardYear!!.isEmpty()
        ) {
            hasValidCardData = false
        }
    }

    private fun hasInternetConnection(): Boolean {
        val cm = getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    private fun onPressTokenizeButton() {
        enableInputs(false)
        enableProgressBar(true)
        if (hasInternetConnection()) {
            Conekta.setPublicKey(PUBLIC_KEY)
            Conekta.setApiVersion(API_VERSION)
            Conekta.collectDevice(this)
            getCardData()
            if (hasValidCardData!!) {
                val card = Card(cardName, cardNumber, cardCvc, cardMonth, cardYear)
                val token = Token(this)

                //Listen when token is returned
                token.onCreateTokenListener { data -> showTokenResult(data) }

                //Request for create token
                token.create(card)
            } else {
                Toast.makeText(
                    this@Act4_Conekta,
                    resources.getString(R.string.cardDataIncomplete),
                    Toast.LENGTH_LONG
                ).show()
                enableInputs(true)
                enableProgressBar(false)
            }
        } else {
            Toast.makeText(
                this@Act4_Conekta,
                resources.getString(R.string.needInternetConnection),
                Toast.LENGTH_LONG
            ).show()
            //binding.outputView.text = resources.getString(R.string.needInternetConnection)
            enableInputs(true)
            enableProgressBar(false)
        }
    }

    private fun showTokenResult(data: JSONObject) {
        try {
            Log.e("TAG", "showTokenResult: $data")
            var tokenId: String = ""
            if (data.has("id")) {
                tokenId = data.getString("id")
                Toast.makeText(
                    binding.root.context,
                    "Pago Exitoso",
                    Toast.LENGTH_LONG
                ).show()
                //intent
                startActivity(Intent(applicationContext, Act4_PaymentSuccess::class.java))

            } else {
                tokenId = data.getString("message")
                Toast.makeText(
                    binding.root.context,
                    tokenId,
                    Toast.LENGTH_LONG
                ).show()
            }
            val tokenMessage = "$tokenIdTag $tokenId"
            //binding.outputView.text = tokenMessage

            Log.d(tokenIdTag, tokenId)
        } catch (error: Exception) {
            val errorMessage = "$errorTag $error"
            //binding.outputView.text = errorMessage
        }
        enableInputs(true)
        enableProgressBar(false)

        val uuidMessage: String = uuidDeviceTag + " " + Conekta.deviceFingerPrint(this)
        //binding.uuidDevice.text = uuidMessage

    }
}