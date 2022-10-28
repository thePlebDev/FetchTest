package com.elliottsoftware.fetchtest.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.elliottsoftware.fetchtest.R
import com.elliottsoftware.fetchtest.databinding.FragmentListBinding
import com.elliottsoftware.fetchtest.presentation.components.list.ListView
import com.elliottsoftware.fetchtest.presentation.components.main.MainView
import com.elliottsoftware.fetchtest.presentation.viewModels.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    var _binding:FragmentListBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val viewModel: ListViewModel by viewModels()
                ListView(viewModel)
            }
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}