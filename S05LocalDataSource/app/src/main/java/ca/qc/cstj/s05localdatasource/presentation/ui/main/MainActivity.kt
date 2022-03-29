package ca.qc.cstj.s05localdatasource.presentation.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.notifyAllItemChanged
import ca.qc.cstj.s05localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import ca.qc.cstj.s05localdatasource.presentation.adapters.AsyncContactRecyclerViewAdapter
import ca.qc.cstj.s05localdatasource.presentation.adapters.ContactRecyclerViewAdapter
import ca.qc.cstj.s05localdatasource.presentation.ui.dialogs.ContactDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    //private lateinit var contactRecyclerViewAdapter: ContactRecyclerViewAdapter
    private lateinit var contactRecyclerViewAdapter: AsyncContactRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //contactRecyclerViewAdapter = ContactRecyclerViewAdapter(listOf())
        contactRecyclerViewAdapter = AsyncContactRecyclerViewAdapter(this::onContactItemClick)
        binding.rcvContact.adapter = contactRecyclerViewAdapter
        binding.rcvContact.layoutManager = LinearLayoutManager(this)


        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val contact = contactRecyclerViewAdapter.differ.currentList[viewHolder.adapterPosition]
                when(direction) {
                    ItemTouchHelper.RIGHT -> {
                        viewModel.delete(contact)
                        contactRecyclerViewAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                    }

                    ItemTouchHelper.LEFT -> {
                        contact.firstName = "Yannick's"
                        contact.isOnline = !contact.isOnline

                        viewModel.update(contact)
                        contactRecyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                }
            }

        }

//        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
//        itemTouchHelper.attachToRecyclerView(binding.rcvContact)

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rcvContact)
        }


        viewModel.contacts.observe(this) {
            contactRecyclerViewAdapter.differ.submitList(it)
            //contactRecyclerViewAdapter.notifyAllItemChanged()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.createContact("Test", "Test", true)
        }
    }

    private fun onContactItemClick(contact: Contact) {
        //Toast.makeText(this, contact.fullName, Toast.LENGTH_LONG).show()
        val args = Bundle()
        args.putString(ContactDialogFragment.CONTACT_NAME, contact.fullName)
        ContactDialogFragment().apply {
            arguments = args
        }.show(supportFragmentManager, ContactDialogFragment.CONTACT_DIALOG_TAG)

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}