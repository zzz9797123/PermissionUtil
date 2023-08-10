package com.permissionutil.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionutil.lwclibrary.PermissionUtil

/**
 * @Description:
 * @author 95683
 * @date 2023/8/10 16:55
 */
class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callBtn = findViewById<Button>(R.id.callBtn)

        callBtn.setOnClickListener{
            PermissionUtil.request(this,Manifest.permission.CALL_PHONE){allGranted,deniedList->
                if(allGranted){
                    call()
                }else{
                    Toast.makeText(this,"You denied $deniedList",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}