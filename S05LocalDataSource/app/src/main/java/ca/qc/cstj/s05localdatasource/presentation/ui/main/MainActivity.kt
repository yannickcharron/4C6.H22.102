package ca.qc.cstj.s05localdatasource.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.notifyAllItemChanged
import ca.qc.cstj.s05localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.s05localdatasource.presentation.adapters.ContactRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var contactRecyclerViewAdapter: ContactRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactRecyclerViewAdapter = ContactRecyclerViewAdapter(listOf())
        binding.rcvContact.adapter = contactRecyclerViewAdapter
        binding.rcvContact.layoutManager = LinearLayoutManager(this)

        viewModel.contacts.observe(this) {
            contactRecyclerViewAdapter.contacts = it
            contactRecyclerViewAdapter.notifyAllItemChanged()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.createContact("Test", "Test", true)
        }
    }
}