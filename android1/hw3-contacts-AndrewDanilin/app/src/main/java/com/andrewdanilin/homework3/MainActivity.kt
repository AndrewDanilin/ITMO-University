package com.andrewdanilin.homework3

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework3.contact.Contact
import com.andrewdanilin.homework3.contact.ContactAdapter
import com.andrewdanilin.homework3.contact.fetchAllContacts

class MainActivity: AppCompatActivity() {
    private val MY_REQUEST_ID = 77;
    private val EMPTY_CONTACTS = emptyList<Contact>()
    private val READ_CONTACTS_PERMISSION = android.Manifest.permission.READ_CONTACTS

    private lateinit var contactsView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        contactsView = findViewById(R.id.ContactsView)

        if (permissionIsGranted(READ_CONTACTS_PERMISSION)) {
            showContactsList(fetchAllContacts())
        } else {
            requestPermission(READ_CONTACTS_PERMISSION)
        }
    }

    @Suppress("SameParameterValue")
    private fun permissionIsGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this@MainActivity,
                permission) == PackageManager.PERMISSION_GRANTED
    }

    @Suppress("SameParameterValue")
    private fun requestPermission(permission: String) {
        ActivityCompat.requestPermissions(this@MainActivity,
            arrayOf(permission),
            MY_REQUEST_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_REQUEST_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showContactsList(fetchAllContacts())
                } else {
                    showContactsList(EMPTY_CONTACTS)
                    showToast(getString(R.string.no_permission_to_read_contacts));
                }
                return
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun showContactsList(contactsList: List<Contact>) {
        val viewManager = LinearLayoutManager(this)
        contactsView.apply {
            layoutManager = viewManager
            adapter = ContactAdapter(contactsList) {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${it.phoneNumber}")))
            }
        }
        showToast("${contactsList.size}" + getString(R.string.n_contacts_founded))
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this@MainActivity,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}