package com.cleanmaster.galaxya04e

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val tvStorage = findViewById<TextView>(R.id.tv_storage)
        val btnClean = findViewById<Button>(R.id.btn_clean)
        
        // عرض معلومات التخزين
        val storageInfo = getStorageInfo()
        tvStorage.text = storageInfo
        
        btnClean.setOnClickListener {
            Toast.makeText(this, "جاري التنظيف...", Toast.LENGTH_SHORT).show()
            // يمكن إضافة منطق التنظيف هنا
        }
    }
    
    private fun getStorageInfo(): String {
        val path = Environment.getDataDirectory()
        val stat = android.os.StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val totalBlocks = stat.blockCountLong
        val availableBlocks = stat.availableBlocksLong
        
        val totalSize = totalBlocks * blockSize
        val availableSize = availableBlocks * blockSize
        
        val totalGB = String.format("%.2f", totalSize / (1024.0 * 1024.0 * 1024.0))
        val availableGB = String.format("%.2f", availableSize / (1024.0 * 1024.0 * 1024.0))
        
        return "المساحة الكلية: $totalGB GB\nالمتاحة: $availableGB GB"
    }
}
