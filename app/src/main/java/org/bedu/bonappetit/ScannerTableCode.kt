package org.bedu.bonappetit

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import org.bedu.bonappetit.databinding.ActivityMainBinding
import org.bedu.bonappetit.databinding.ScannerTableCodeBinding

class ScannerTableCode : AppCompatActivity() {

    private lateinit var binding:ScannerTableCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScannerTableCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanner.setOnClickListener {
            initScanner()
        }

    }

    private fun initScanner(){
        //Lanza un activity y se queda escuchando si hay una respuesta.
        val integrator = IntentIntegrator(this)
        //Especificamos que solo queremos leer el código QR
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Coloque el código QR en el interior del rectángulo del visor para escanear.")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)

        if(result != null){
            if(result.contents == null){
                Toast.makeText(this,"Escaneo cancelado",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"¡Bienvenido! Mesa 1 escaneada.",Toast.LENGTH_SHORT).show()
                //Enviamos al siguiente Activity
            }

        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}