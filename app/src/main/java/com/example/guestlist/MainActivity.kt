package com.example.guestlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val LAST_GUEST_NAME_KEY = "last-guest-name-bundle-key"
class MainActivity : AppCompatActivity() {
    private lateinit var addGuestButton: Button
    private lateinit var newGuestEditText: EditText
    private lateinit var guestList: TextView
    private lateinit var lastGuestAdded: TextView
    private lateinit var clearGuestListButton: Button

//    val guestNames = mutableListOf<String>()

    private val guestListViewModel: GuestListViewModel by lazy {
    // "lazy" means lambda function won't be called until guestListViewModel is used

        // "ViewModelProvider is being given a reference to the class definition for GuestListViewModel"
        ViewModelProvider(this).get(GuestListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeOnCreateGUIComponents() // trying to reduce lines in onCreate()
        setListeners()

        val savedLastGuestMessage = savedInstanceState?.getString(LAST_GUEST_NAME_KEY)
        lastGuestAdded.text = savedLastGuestMessage

        updateGuestList() // update from view model - needed when activity is destroyed or recreated
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_GUEST_NAME_KEY, lastGuestAdded.text.toString())
    }

    private fun setListeners() {
        addGuestButton.setOnClickListener {
            addNewGuest()
        }

        clearGuestListButton.setOnClickListener {
            clearGuestList()
        }
    }

    private fun clearGuestList() {
        guestListViewModel.clearAllGuestNames()
        lastGuestAdded.text = ""
        updateGuestList()
    }

    private fun addNewGuest() {
        val newGuestName = newGuestEditText.text.toString()
        if (newGuestName.isNotBlank()) {
//            guestNames.add(newGuestName)
            guestListViewModel.addGuest(newGuestName)
            updateGuestList()
            newGuestEditText.text.clear()
            lastGuestAdded.text = getString(R.string.last_guest_message, newGuestName)
        }
    }


    private fun initializeOnCreateGUIComponents() {
        // Looking at initializing lateInit variables in a function, for potentially cleaner code.
        addGuestButton = findViewById(R.id.add_guest_button)
        newGuestEditText = findViewById(R.id.new_guest_input)
        guestList = findViewById(R.id.list_of_guests)
        lastGuestAdded = findViewById(R.id.last_guest_added)
        clearGuestListButton = findViewById(R.id.clear_guests_button)

        // set "last guest" template holder to an empty string, because I think it looks better than
        // displaying "%1$s" to the user.
//        lastGuestAdded.text = getString(R.string.last_guest_message, "")
    }

    private fun updateGuestList() {
        val guests = guestListViewModel.getSortedGuestNames()
//        val guestDisplay = guestNames.sorted().joinToString(separator = "\n")
        val guestDisplay = guests.sorted().joinToString(separator = "\n")

        guestList.text = guestDisplay


    }


}