package com.busanit.ch16_profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.busanit.ch16_profile.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var filePath : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userImg = binding.userImg
        val btnGallery = binding.btnGallery
        val btnCamera = binding.btnCamera

        // 갤러리 연동
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // inSampleSize 비율 계산 함수
            val calRatio = calculateInSampleSize(it.data!!.data!!,
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize))
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio

            // 니미지 로딩
            var inputStream = contentResolver.openInputStream(it.data!!.data!!)
            val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
            inputStream!!.close()
            inputStream = null
            bitmap?.let {
                userImg.setImageBitmap(bitmap)
            } ?: let {
                Log.d("myLog", "bitmap null")
            }
        }
        btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*" // image 까지 넣어야 함. images 오류 발생
            requestGalleryLauncher.launch(intent)
        }
        
        // 카메라 연동, file_paths.xml 생성, 프로바이더 등록
        val requestCameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val calRatio = calculateInSampleSize(Uri.fromFile(File(filePath)),
            resources.getDimensionPixelSize(R.dimen.imgSize),
            resources.getDimensionPixelSize(R.dimen.imgSize))
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                userImg.setImageBitmap(bitmap)
            }
        }

        btnCamera.setOnClickListener {
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(this,
            "com.busanit.ch16_profile.fileprovider", file)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraLauncher.launch(intent)

        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true   // 비트맵 객체 생성없이 정보만 options객체에 저장
        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight : Int = height / 2
            val halfWidth : Int = width / 2
            while (halfHeight/inSampleSize >= reqHeight && inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}