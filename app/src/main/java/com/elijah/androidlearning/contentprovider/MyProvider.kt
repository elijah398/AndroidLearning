package com.elijah.androidlearning.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri


class MyProvider : ContentProvider() {
    private lateinit var mContext: Context
    private lateinit var mDbHelper: DBHelper
    private lateinit var db: SQLiteDatabase

    companion object {
        // 设置ContentProvider的唯一标识
        private const val AUTHORITY = "com.elijah.androidlearning"

        const val User_Code = 1
        const val Job_Code = 2

        // UriMatcher类使用:在ContentProvider 中注册URI
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
        private var mMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "user", User_Code)
            addURI(AUTHORITY, "job", Job_Code)
        }
    }

    // 以下是ContentProvider的6个方法
    /**
     * 初始化ContentProvider
     */
    override fun onCreate(): Boolean {
        mContext = context!!
        // 在ContentProvider创建时对数据库进行初始化
        // 运行在主线程，故不能做耗时操作,此处仅作展示
        mDbHelper = DBHelper(context)
        db = mDbHelper.writableDatabase.apply {
            // 初始化两个表的数据(先清空两个表,再各加入一个记录)
            execSQL("delete from user")
            execSQL("insert into user values(1,'Carson');")
            execSQL("insert into user values(2,'Kobe');")
            execSQL("delete from job")
            execSQL("insert into job values(1,'Android');")
            execSQL("insert into job values(2,'iOS');")
        }
        return true
    }

    /**
     * 添加数据
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri {

        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        val table = getTableName(uri)

        // 向该表添加数据
        db.insert(table, null, values)

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.contentResolver.notifyChange(uri, null)

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);
        return uri
    }

    /**
     * 查询数据
     */
    override fun query(
        uri: Uri, projection: Array<String?>?, selection: String?,
        selectionArgs: Array<String?>?, sortOrder: String?
    ): Cursor {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        val table = getTableName(uri)

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        // 查询数据
        return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null)
    }

    /**
     * 更新数据
     */
    override fun update(
        p0: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        // 由于不展示,此处不作展开
        return 0
    }

    /**
     * 删除数据
     */
    override fun delete(p0: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        // 由于不展示,此处不作展开
        return 0
    }

    override fun getType(p0: Uri): String? {

        // 由于不展示,此处不作展开
        return null
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private fun getTableName(uri: Uri): String? {
        var tableName: String? = null
        when (mMatcher.match(uri)) {
            User_Code -> tableName = DBHelper.USER_TABLE_NAME
            Job_Code -> tableName = DBHelper.JOB_TABLE_NAME
        }
        return tableName
    }
}