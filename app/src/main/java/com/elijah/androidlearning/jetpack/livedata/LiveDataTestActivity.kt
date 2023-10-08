package com.elijah.androidlearning.jetpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.elijah.androidlearning.R
import kotlinx.android.synthetic.main.activity_live_data_test.*

class LiveDataTestActivity : AppCompatActivity() {
    private val model: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_test)

        // LiveData 观察者
        val nameObserver = Observer<String> { newName ->
            // 更新视图
            nameTextView.text = newName
        }

        // 注册 LiveData 观察者，this 为生命周期宿主
        model.currentName.observe(this, nameObserver)

        // 修改 LiveData 数据
        changeName.setOnClickListener {
            val anotherName = "John Doe"
            model.currentName.value = anotherName
        }
    }
}