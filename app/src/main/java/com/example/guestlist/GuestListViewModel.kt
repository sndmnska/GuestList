package com.example.guestlist

import android.util.Log
import androidx.lifecycle.ViewModel

// We reviewed polymorphism in OOP.  Worth further research.
class GuestListViewModel : ViewModel() {
    private val guestNames = mutableListOf<String>()


    fun addGuest(name: String) {
        Log.d("GUEST_LIST", "Got to this point.")
        guestNames.add(name)
    }

    fun getSortedGuestNames(): List<String> { // because we're only displaying it, List is fine here.
        return guestNames.sorted()
    }

    fun clearAllGuestNames() {
        guestNames.clear()
    }
}

/* Video: android lifecycle - guestList view model, guidelines -- TODO      [12:00 / 29:33]
***************************************************************************************************
    TODO - Clear List Button
        -✅ Add a new button to the UI to delete all guests
        -✅ Give the button an id
        -✅ Create a String resource for the button text
        -✅ Add a function to the GuestListViewModel to clear the guest list
        -✅ When this button is clicked, the Activity should ask the GuestListViewModel to clear the list,
        then call updateGuests to clear the list in the TextView
        - After clearing, you should still be able to add guests to
        the (empty) guest list with the add button
 */
