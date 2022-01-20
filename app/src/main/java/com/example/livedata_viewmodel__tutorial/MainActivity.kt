package com.example.livedata_viewmodel__tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedata_viewmodel__tutorial.MyNumberViewModel.Companion.TAG
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    companion object{
        const val TAG: String = "로그"
    }


    // 나중에 값이 실행 될 거라고 lateinit으로 설정
    lateinit var myNumberViewModel : MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다.
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            number_textview.text = it.toString()
        })

        // 리스너연결
        plus_btn.setOnClickListener(this)
        minus_bts.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val userInput = userinput_edittext.text.toString().toInt()

            // 뷰 모델에 라이브데이터 값을 변경하는 메소드 실행
        when(view){
            plus_btn ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            minus_bts ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}

