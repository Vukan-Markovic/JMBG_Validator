package vukan.com.jmbg_validator.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import vukan.com.jmbg_validator.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private val viewModel by viewModels<ResultViewModel>()
    private lateinit var binding: FragmentResultBinding
    private val args by navArgs<ResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val user = args.user
        viewModel.parseJMBG(user.jmbg)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer

            if (result.error != null)
                Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()

            if (result.success != null) {
                binding.name.text = user.name
                binding.surname.text = user.surname
                binding.jmbg.text = user.jmbg

                if (result.success.country.isNotEmpty())
                    binding.country.text = result.success.country
                else {
                    binding.countryLabel.visibility = View.GONE
                    binding.country.visibility = View.GONE
                }

                if (result.success.region.isNotEmpty())
                    binding.region.text = result.success.region
                else {
                    binding.regionLabel.visibility = View.GONE
                    binding.region.visibility = View.GONE
                }

                binding.ordinalNumberOfBirth.text = result.success.ordinalNumberOfBirth.toString()
                binding.dayOfBirth.text = result.success.dayOfBirth
                binding.gender.text = result.success.gender
                binding.dateOfBirth.text = result.success.dateOfBirth
            }
        })
    }
}