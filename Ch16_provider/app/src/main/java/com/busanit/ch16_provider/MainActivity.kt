package com.busanit.ch16_provider

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.busanit.ch16_provider.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 주소록 연동(requestContactsLauncher)
        val requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode == RESULT_OK) {
                Log.d("myLog", "it.data.data : ${it.data!!.data}")  // !! : null이면 exception 띄워라~
                val cursor = contentResolver.query(
                    it!!.data!!.data!!, arrayOf<String>(   // 에러가 뜨는 이유가 null 채크 이기 때문에 !! 붙여줌
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ), null, null, null
                )   // query(url, 컬럼명(null일 경우 모든 컬럼), where, 조건절의 내용, 정렬 순서 )
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    Log.d("myLog", "name ; $name, phone : $phone")
                }
            }
        }
        val btnContacts = binding.btnContacts
        btnContacts.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            requestContactsLauncher.launch(intent)
        }

        // 2. 갤러리 연동(requestGalleryLauncher)
        val btnGallery = binding.btnGallery
        val imgGal = binding.imgGal

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            try {
                val option = BitmapFactory.Options()
                option.inSampleSize = 2 // 2 = 분모, 1/2로 출력 inSmapleSize 로 데이터의 크기를 줄여 주는것
                var inputStream = contentResolver.openInputStream(it!!.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let {
                    imgGal.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("myLog", "bitmap null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        // 3-1. 촬영 데이터 가져오기(requestThumbnailLauncher)
        val btnCamera = binding.btnCamera
        val imgCam = binding.imgCam

//        val requestThumbnailLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ){
//            val bitmap = it?.data?.extras?.get("data") as Bitmap
//            imgCam.setImageBitmap(bitmap)
//        }
//
//        btnCamera.setOnClickListener {
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            requestThumbnailLauncher.launch(intent)
//        }
        
        // 3-2. 촬영 파일 공유하기(프로바이더 등록 필요, file_path.xml 생성, 매니페스트 등록)(requestFileLauncher)
        var filePath : String = ""
        val requestFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val option = BitmapFactory.Options()
            option.inSampleSize = 1  // 데이터 사이즈 안줄임 1 그대로
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                imgCam.setImageBitmap(bitmap)
            }
        }

        btnCamera.setOnClickListener {
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile("JPEG_${timeStamp}", ".jpg", storageDir)
            filePath = file.absolutePath    // filePath로 절대경로 받기(지정된 경로로 촬영한 사진이 저장됨)

            val photoURI : Uri = FileProvider.getUriForFile(this, "com.busanit.ch16_provider", file)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestFileLauncher.launch(intent)
        }
        
        // 4. 지도 연동(좌표 넣으면 되는듯)
        val btnMap = binding.btnMap
        btnMap.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:35.1535, 129.1183")) // 광안리 해수욕장 좌표
            startActivity(intent)
        }

        // 5. 전화 연동
        val btnTel = binding.btnTel
        btnTel.setOnClickListener {
//            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:010-7577-0769"))   // 바로 전화 걸림 ACTION_CALL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-7577-0769")) // 전화번호 입력 창에 번호 입력된 채로 출력 ACTION_VIEW
            startActivity(intent)
        }
    }
}