package com.example.recipemaniaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recipemaniaapp.R


class HomeFragment : Fragment() {


    lateinit var tv_user: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//
//        supportActionBar?.title = "Home"
//        tv_user = findViewById(R.id.tv_user);
//        tv_user.setText(signedAccount.email)

    }
}