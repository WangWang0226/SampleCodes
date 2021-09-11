package com.example.wangsamplecode

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wangsamplecode.databinding.DialogFragmentBinding

class MyDialogFragment : DialogFragment() {

    lateinit var binding: DialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //把dialog設成全螢幕
        setStyle(STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen)

    }

    //若想return一個dialog就用這個
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    //若想return一個view就用這個
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //在這裡綁定layout檔
        binding = DialogFragmentBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onStart() {
        super.onStart()


    }




}