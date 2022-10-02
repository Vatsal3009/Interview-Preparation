package com.vcdevelopmentworld.interview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.firebase.client.Firebase
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.vcdevelopmentworld.interview.R
import com.vcdevelopmentworld.interview.AddQuestion
import com.google.firebase.database.FirebaseDatabase
import com.vcdevelopmentworld.interview.StudentDetails
import android.widget.Toast
import com.vcdevelopmentworld.interview.databinding.ActivityAddQuestionBinding

class AddQuestion : BaseActivity<ActivityAddQuestionBinding>() {
    var NameEditText: EditText? = null
    var PhoneNumberEditText: EditText? = null
    var NameHolder: String? = null
    var NumberHolder: String? = null
    var firebase: Firebase? = null
    var context: Context = this
    var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)
        NameEditText = findViewById(R.id.Que)
        PhoneNumberEditText = findViewById(R.id.Ans)
        val add = findViewById<Button>(R.id.btnAdd)
        Firebase.setAndroidContext(this@AddQuestion)
        firebase = Firebase(Firebase_Server_URL)
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)
        add.setOnClickListener(View.OnClickListener {
            val studentDetails = StudentDetails()
            GetDataFromEditText()

            // Adding name into class function object.
            studentDetails.studentName = NameHolder

            // Adding phone number into class function object.
            studentDetails.studentPhoneNumber = NumberHolder

            // Getting the ID from firebase database.
            val StudentRecordIDFromServer = databaseReference!!.push().key

            // Adding the both name and number values using student details class object using ID.
            databaseReference!!.child(StudentRecordIDFromServer!!).setValue(studentDetails)


            // Showing Toast message after successfully data submit.
        })
    }

    private fun GetDataFromEditText() {
        val isError = false
        if (NameEditText!!.text.toString().length == 0 && PhoneNumberEditText!!.text.toString().length == 0) {
            NameEditText!!.error = "Can't be blank"
            PhoneNumberEditText!!.error = "Can't be blank"
            Toast.makeText(this@AddQuestion, "Please check the details", Toast.LENGTH_SHORT).show()
        } else {
            NameHolder = NameEditText!!.text.toString().trim { it <= ' ' }
            NumberHolder = PhoneNumberEditText!!.text.toString().trim { it <= ' ' }
            Toast.makeText(this@AddQuestion, "Thank you", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    companion object {
        const val Firebase_Server_URL = "https://interview-70bc1.firebaseio.com/"
        const val Database_Path = "Student_Details_Database"
    }

    override fun initControl() {
        TODO("Not yet implemented")
    }

    override fun getViewBinding(): ActivityAddQuestionBinding {
        TODO("Not yet implemented")
    }
}