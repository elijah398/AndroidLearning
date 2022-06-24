//package com.elijah.androidlearning.contentprovider
//
//import android.Manifest
//import android.content.ContentValues
//import android.content.pm.PackageManager
//import android.database.Cursor
//import android.net.Uri
//import android.os.Bundle
//import android.provider.ContactsContract
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.elijah.androidlearning.R
//import kotlinx.android.synthetic.main.activity_content_provider.*
//
//
//class ContentProviderActivity : AppCompatActivity() {
//
//    private val contactsList = ArrayList<String>()
//    private lateinit var adapter: ArrayAdapter<String>
//
//    private val listener =  View.OnClickListener { v ->
//        when (v.id) {
//            R.id.requestContacts -> requestContacts()
//            R.id.requestMyContentProvider -> requestMyContentProvider()
//        }
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_content_provider)
//        findViewById<View>(R.id.requestContacts).setOnClickListener(listener)
//        findViewById<View>(R.id.requestMyContentProvider).setOnClickListener(listener)
//    }
//
//    private fun requestContacts() {
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
//        contactsView.adapter = adapter
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
//        } else {
//            readContacts()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            1 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    readContacts()
//                } else {
//                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun readContacts() {
//        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)?.apply {
//            while (moveToNext()) {
//                val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                contactsList.add("$displayName\n$number")
//            }
//            adapter.notifyDataSetChanged()
//            close()
//        }
//    }
//
//    private fun requestMyContentProvider() {
//        /**
//         * 对user表进行操作
//         */
//
//        // 设置URI
//        val uri_user: Uri = Uri.parse("content://cn.scu.myprovider/user")
//
//        // 插入表中数据
//        val values = ContentValues()
//        values.put("_id", 3)
//        values.put("name", "Iverson")
//
//
//        // 获取ContentResolver
//        val resolver = contentResolver
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        resolver.insert(uri_user, values)
//
//        // 通过ContentResolver 向ContentProvider中查询数据
//        val cursor: Cursor? =
//            resolver.query(uri_user, arrayOf("_id", "name"), null, null, null)
//        while (cursor.moveToNext()) {
//            System.out.println(
//                "query book:" + cursor.getInt(0).toString() + " " + cursor.getString(1)
//            )
//            // 将表中数据全部输出
//        }
//        cursor.close()
//        // 关闭游标
//        /**
//         * 对job表进行操作
//         */
//        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
//        val uri_job: Uri = Uri.parse("content://cn.scu.myprovider/job")
//
//        // 插入表中数据
//        val values2 = ContentValues()
//        values2.put("_id", 3)
//        values2.put("job", "NBA Player")
//
//        // 获取ContentResolver
//        val resolver2 = contentResolver
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        resolver2.insert(uri_job, values2)
//
//        // 通过ContentResolver 向ContentProvider中查询数据
//        val cursor2: Cursor? =
//            resolver2.query(uri_job, arrayOf("_id", "job"), null, null, null)
//        while (cursor2.moveToNext()) {
//            System.out.println(
//                "query job:" + cursor2.getInt(0).toString() + " " + cursor2.getString(1)
//            )
//            // 将表中数据全部输出
//        }
//        cursor2.close()
//        // 关闭游标
//    }
//}