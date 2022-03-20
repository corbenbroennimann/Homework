package com.example.livedataexample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.livedataexample.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
     //   resultText.text = viewModel.getResult().toString()
        // TODO: Use the ViewModel
        //add this lambda - be careful, don't import java util observer
        /*
        val resultObserver = Observer<Float> { //SAM

                result -> resultText.text = result.toString() }

         */

// this is a longer versiion of the SAM above
        class resultObserver : Observer<Int> {
           override fun onChanged(result: Int?) {
                resultText.text = result.toString()
            }
        }

        val myResultObserver = resultObserver()
//end of longer version

     //   this.viewModel.getResult().observe(viewLifecycleOwner,  resultObserver())






//add this observer
        viewModel.getResult().observe(viewLifecycleOwner,myResultObserver)

        convertButton.setOnClickListener {


            if (numA.text.isNotEmpty()&& numB.text.isNotEmpty()) {

                viewModel.setAmount(numA.text.toString(), numB.text.toString())
                //don't need this
                //resultText.text = viewModel.getResult().toString()

            } else {

                resultText.text = "No Value"

            }
        }
    }

}