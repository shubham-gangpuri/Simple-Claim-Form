package com.android.bizom.presentation.claims.addclaim

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bizom.R
import com.android.bizom.databinding.FragmentClaimBinding
import com.android.bizom.presentation.MainActivity
import com.android.bizom.presentation.claims.addclaim.adapter.DataAdapter
import com.android.bizom.presentation.claims.addclaim.adapter.utils.ItemButtonVM
import com.android.bizom.presentation.claims.addclaim.adapter.utils.ItemDateVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClaimFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var claimId: String? = null
    private val viewModel: ClaimsFragmentViewModel by viewModels()
    private lateinit var binding: FragmentClaimBinding
    private val dataAdapter: DataAdapter by lazy {
        DataAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            claimId = it.getString(CLAIM_ID)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_claim, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.submit -> {
                view?.rootView?.let {
                    Snackbar.make(it, "Claims submitted", Snackbar.LENGTH_LONG).show()
                }
                true
            }
            android.R.id.home -> {
                findNavController().popBackStack()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        val claimTypeAdapter = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_spinner_item
        )
        claimTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.itemDate.viewModel = ItemDateVM()
        binding.itemButton.viewModel = ItemButtonVM()

        binding.claimTypeSpinner.adapter = claimTypeAdapter
        binding.claimTypeSpinner.onItemSelectedListener = this

        binding.claimFormView.apply {
            layoutManager = LinearLayoutManager(activity)
            hasFixedSize()
            this.adapter = dataAdapter
        }

        viewModel.claimTypeDetailMap.observe(this, {
            claimTypeAdapter.addAll(it.keys)
        })

        binding.itemButton.addButtonView.setOnClickListener {
            var errorString: String = ""
            dataAdapter.formItems.forEach { type ->
                if (type.Claimfield.required == 1 && type.Claimfield.input.isNullOrEmpty()) {
                    errorString += "${type.Claimfield.label} is required field. "
                }
            }
            if (errorString.isNotEmpty())
                Snackbar.make(it, errorString, Snackbar.LENGTH_LONG).show()
        }
        dataAdapter.setOnItemClickListener { claimtypedetail, i ->
            view?.let {
                Snackbar.make(
                    it,
                    "${claimtypedetail.Claimfield.label} ${i}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val name = parent?.getItemAtPosition(position) as String
        viewModel.claimTypeDetailMap.value?.get(name)
            ?.let { dataAdapter.setDataClaimTypeDetails(it) }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}