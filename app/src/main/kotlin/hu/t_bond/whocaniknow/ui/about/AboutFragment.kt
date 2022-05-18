package hu.t_bond.whocaniknow.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import hu.t_bond.whocaniknow.databinding.AboutFragmentBinding

class AboutFragment : Fragment() {

    private lateinit var binding: AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AboutFragmentBinding.inflate(inflater, container, false)

        val act = activity
        if (act is AppCompatActivity) {
            act.supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
            }
        }

        return binding.root
    }


}