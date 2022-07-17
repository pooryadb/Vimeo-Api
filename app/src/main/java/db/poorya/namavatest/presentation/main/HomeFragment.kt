package db.poorya.namavatest.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import db.poorya.namavatest.databinding.FragHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragHomeBinding.inflate(inflater, container, false)

        return requireNotNull(binding).root
    }

}