package com.marijannovak.autismhelper.ui.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marijannovak.autismhelper.data.KEY_SESSION_ID
import com.marijannovak.autismhelper.R
import com.tumblr.remember.Remember

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println(Remember.getString(KEY_SESSION_ID, ""))
    }

}
