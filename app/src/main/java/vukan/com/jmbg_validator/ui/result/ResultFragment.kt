package vukan.com.jmbg_validator.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.jmbg_validator.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
    }
}