package vukan.com.jmbg_validator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import vukan.com.jmbg_validator.data.model.User
import vukan.com.jmbg_validator.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private var isDataValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (isDataValid) {
            binding.validateButton.isEnabled = true
            binding.validateButton.isClickable = true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.formState.observe(viewLifecycleOwner, Observer {
            val state = it ?: return@Observer
            isDataValid = state.isDataValid
            binding.validateButton.isEnabled = isDataValid
            binding.validateButton.isClickable = isDataValid

            if (state.nameError != null) binding.outlinedName.error = getString(state.nameError)
            else binding.outlinedName.error = null

            if (state.surnameError != null)
                binding.outlinedSurname.error = getString(state.surnameError)
            else binding.outlinedSurname.error = null

            if (state.jmbgError != null) binding.outlinedJMBG.error = getString(state.jmbgError)
            else binding.outlinedJMBG.error = null
        })

        binding.validateButton.setOnClickListener {
            navigate()
        }

        binding.name.afterTextChanged {
            viewModel.checkName(binding.name.text.toString())
        }

        binding.surname.afterTextChanged {
            viewModel.checkSurname(binding.surname.text.toString())
        }

        binding.jmbg.apply {
            afterTextChanged {
                viewModel.checkJMBG(binding.jmbg.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        if (isDataValid) navigate()
                }
                false
            }
        }
    }

    private fun navigate() {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToResultFragment(
                User(
                    binding.name.text.toString(),
                    binding.surname.text.toString(),
                    binding.jmbg.text.toString()
                )
            )
        )
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}