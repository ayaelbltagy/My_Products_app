package com.example.myproducts.pojo.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myproducts.R
import kotlinx.coroutines.NonCancellable


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            if (!NonCancellable.isCancelled) {
                // navigate from splash to list
                findNavController().navigate(R.id.action_splashFragment_to_productsFragment)
            }
        }, 2000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}