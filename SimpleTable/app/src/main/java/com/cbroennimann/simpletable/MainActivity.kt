package com.cbroennimann.simpletable

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cbroennimann.simpletable.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMember.setOnClickListener {
            val db = DBHelper(this, null)
            val id = binding.enterUniqueID.text.toString().toInt()
            val firstName = binding.enterFirstName.text.toString()
            val lastName = binding.enterLastName.text.toString()
            val reward = binding.enterRewards.text.toString().toInt()

            try {
                db.addMember(id, firstName, lastName, reward)
            } catch(e:Exception) {

            }
                Toast.makeText(this, id.toString() + " added to database", Toast.LENGTH_LONG).show()

                binding.enterUniqueID.text.clear()
                binding.enterFirstName.text.clear()
                binding.enterLastName.text.clear()
                binding.enterRewards.text.clear()

        }

        binding.deleteMember.setOnClickListener {
            val db = DBHelper(this, null)
            try {
                db.deleteMember(binding.enterID.text.toString().toInt())
                binding.enterID.text.clear()
            } catch (e: Exception) {

            }

        }

        binding.displayInfo.setOnClickListener {
            val db = DBHelper(this, null)
            try {
                if (binding.enterID.text.toString() != null) {
                    val cursor = db.getMember(binding.enterID.text.toString().toInt())

                    cursor!!.moveToFirst()
                    binding.PrimeKey.setText(
                        cursor.getInt(cursor.getColumnIndex(DBHelper.UNIQUE_ID_COL))
                            .toString() + "\n"
                    )
                    binding.fName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.FIRST_NAME_COL)) + "\n")
                    binding.lName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.LAST_NAME_COL)) + "\n")
                    binding.reward.setText(cursor.getString(cursor.getColumnIndex(DBHelper.REWARDS_COL)) + "\n")

                    binding.enterID.text.clear()
                    cursor.close()
                }
            }catch(e:Exception) {

            }
        }



    }
}