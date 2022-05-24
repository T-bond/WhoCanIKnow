package hu.t_bond.whocaniknow.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import hu.t_bond.whocaniknow.databinding.DetailFragmentBinding


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private val viewModel: DetailViewModel by viewModels()
    private var firebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())

        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        viewModel.contact.observe(viewLifecycleOwner) {
            binding.contact = it
            startPostponedEnterTransition()

            if (it != null) {
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, it.id.value)
                firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle)
            }
        }

        val act = activity
        if (act is AppCompatActivity) {
            act.supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
            }
        }

        return binding.root
    }


}