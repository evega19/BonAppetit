package org.bedu.bonappetit

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.zxing.integration.android.IntentIntegrator
import org.bedu.bonappetit.databinding.ActivityAct2ScannerTableCodeBinding

class Act2ScannerTableCode : AppCompatActivity() {

    companion object{
        val PREFS_NAME = "org.bedu.bonappetit"
        val IS_TABLE_SELECTED = "is_table_selected"

        val PERMISSION_ID =34
    }

    private lateinit var binding:ActivityAct2ScannerTableCodeBinding

    private val sharedPreferences by lazy{ getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAct2ScannerTableCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(sharedPreferences.getBoolean(IS_TABLE_SELECTED, false)){
            goToMenu()
        }else{
            checkPermissionActiveCamera()
        }

        binding.btnScanner.setOnClickListener {
            checkPermissionActiveCamera()
        }

    }

    private fun checkPermissionActiveCamera(){
        if(checkCameraPermission()){
            //Si tienes permisos abre camara
            initScanner()
        }else{
            //Si no, solicitalos
            requestPermission()
        }
    }

    private fun checkCameraPermission():Boolean{
        //Verificar si tenemos permisos
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(){
        //Recuperar el permiso para la cámara
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_ID)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_ID){
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initScanner()
            }
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
                sharedPreferences.edit().putBoolean(IS_TABLE_SELECTED,true).apply()
                goToMenu()
            }

        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun goToMenu(){
        val i = Intent(this, Act3Menu::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun isLogged():Boolean{
        return sharedPreferences.getBoolean(IS_TABLE_SELECTED,false)
    }

    override fun onStart() {
        super.onStart()
        if(isLogged()){
            goToMenu()
        }
    }




}