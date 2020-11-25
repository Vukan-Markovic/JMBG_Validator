package vukan.com.jmbg_validator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.jmbg_validator.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Set error text
        //passwordLayout.error = getString(R.string.error)

        // Clear error text
        //passwordLayout.error = null

        // Get input text
        //val inputText = outlinedTextField.editText?.text.toString()

        //outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        // Respond to input text change
        //}
    }
}