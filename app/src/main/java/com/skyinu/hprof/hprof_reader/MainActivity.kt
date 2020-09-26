package com.skyinu.hprof.hprof_reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.widget.Toast
import com.skyinu.hprof.reader.HprofReader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread {
            var count = 0
            while (true) {
                Thread.sleep(100)
                count++
            }
        }.start()
        tvDumpJava.setOnClickListener {
            Debug.dumpHprofData(this.applicationContext.filesDir.absolutePath + File.separator + "java.hprof")
            Toast.makeText(this, "dump finished", Toast.LENGTH_SHORT).show()
        }
        tvParseJava.setOnClickListener {
            val hporf =
                File(this.applicationContext.filesDir.absolutePath + File.separator + "java.hprof")
            HprofReader().read(hporf)
        }
    }
}