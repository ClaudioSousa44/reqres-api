package br.senai.sp.jandira.retrofit_reqres_xml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var apiService:ApiService



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //Botão de GET
        findViewById<Button>(R.id.btnGET).setOnClickListener{
           getUserByID()

        }

        //Botão de PUT
        findViewById<Button>(R.id.btnPUT).setOnClickListener{
           // Log.e("PUTTING-DATA", "PUT")
            updateUser()
        }

        //Botão de DELETE
        findViewById<Button>(R.id.btnDELETE).setOnClickListener{
            //Log.e("DELETING-DATA", "DELETE")
            deleteUser()
        }

        //Botão de POST
        findViewById<Button>(R.id.btnPOST).setOnClickListener{
            //Log.e("POSTTING-DATA", "POST")
            createUser()
        }

    }

    private fun deleteUser() {
        lifecycleScope.launch{
           val result = apiService.deleteUser("2")
            if(result.isSuccessful){
                Log.e("UPDATE-DATA", "${result.body()}")
            }else{
                Log.e("UPDATE-DATA", "${result.message()}")
            }
        }
    }

    private fun updateUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply{
                addProperty("name", "Artur Reis")
                addProperty("job", "BACK-END MAROMBA")
            }

            val result = apiService.updateUser("2", body)

            if(result.isSuccessful){
                Log.e("UPDATE-DATA", "${result}")
            }else{
                Log.e("UPDATE-DATA", "${result.message()}")
            }
        }
    }

    //Inserir usuário
    private fun createUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply{
                addProperty("name", "Claudio Sousa")
                addProperty("job", "Desempregado")
            }

            val result = apiService.createUser(body)

            if(result.isSuccessful){
                Log.e("CREAT-DATA", "${result.body()}")
            }else{
                Log.e("CREAT-DATA", "${result.message()}")
            }
        }
    }

    //Listagem de usuário
    private fun getUserByID() {
        lifecycleScope.launch {
            val result = apiService.getUserByID("2")

            if(result.isSuccessful){
                Log.e("GETTING-DATA", "${result.body()?.data}")
            }else{
                Log.e("GETTING-DATA", "${result.message()}")
            }
        }
    }
}