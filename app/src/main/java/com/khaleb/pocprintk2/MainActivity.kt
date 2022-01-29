package com.khaleb.pocprintk2

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.EditText
import com.sunmi.extprinterservice.ExtPrinterService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button :  Button = findViewById(R.id.btn_test)
        val txt : EditText = findViewById(R.id.edtText)



        button.setOnClickListener {
            val serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                    val ext = ExtPrinterService.Stub.asInterface(p1)

                     if (txt.text.isNotEmpty()){
                         ext.printText("\n\n\n\n ${txt.text}\n\n\n\n\n" +
                                 "\n" +
                                 "\n" +
                                 "\n\n" +
                                 "\n" +
                                 "\n" )
                         ext.cutPaper(0, 1)
                     }
                }

                override fun onServiceDisconnected(p0: ComponentName?) {}

            }

            val intent = Intent()
            intent.setPackage("com.sunmi.extprinterservice")
            intent.setAction("com.sunmi.extprinterservice.PrinterService")
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }

    }
}


