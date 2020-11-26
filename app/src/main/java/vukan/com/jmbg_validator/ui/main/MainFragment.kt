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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val ime = binding.name.text.toString()
        val prezime = binding.surname.text.toString()
        val jmbg = binding.jmbg.text.toString()

        viewModel.formState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer
            binding.validateButton.isEnabled = loginState.isDataValid

            if (loginState.imeError != null) binding.outlinedName.error =
                getString(loginState.imeError)

            if (loginState.prezimeError != null) binding.outlinedSurname.error =
                getString(loginState.prezimeError)

            if (loginState.jmbgError != null) binding.outlinedJMBG.error =
                getString(loginState.jmbgError)
        })

        binding.validateButton.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToResultFragment(
                    User(ime, prezime, jmbg)
                )
            )
        }

        binding.name.afterTextChanged {
            viewModel.loginDataChanged(ime, prezime, jmbg)
        }

        binding.surname.afterTextChanged {
            viewModel.loginDataChanged(ime, prezime, jmbg)
        }

        binding.jmbg.apply {
            afterTextChanged {
                viewModel.loginDataChanged(ime, prezime, jmbg)
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToResultFragment(
                                User(ime, prezime, jmbg)
                            )
                        )
                }
                false
            }
        }

//        binding.jmbg.setOnKeyListener { view, keyCode, _ ->
//            handleKeyEvent(
//                view,
//                keyCode
//            )
//        }

//        val inflater = layoutInflater
//        val container: ViewGroup = findViewById(R.id.custom_toast_container)
//        val layout: View = inflater.inflate(R.layout.custom_toast, container)
//        val text: TextView = layout.findViewById(R.id.text)
//
//        with(Toast(context)) {
//            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
//            duration = Toast.LENGTH_LONG
//            view = layout
//            setText(text.text)
//            show()
//        }

        // Clear error text
        //passwordLayout.error = null

        // Get input text
        //val inputText = outlinedTextField.editText?.text.toString()

        //outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        // Respond to input text change
        //}
    }
//    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_ENTER) {

//            return true
//        }
//
//        return false
//    }

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