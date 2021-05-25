package com.example.myapplicationsearch.view.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationsearch.data.model.ProductDetail
import com.example.myapplicationsearch.data.model.QuerySearch
import com.example.myapplicationsearch.data.model.SearchResponse
import com.example.myapplicationsearch.databinding.ActivityMainBinding
import com.example.myapplicationsearch.domain.usecase.UseCaseRoomSearch
import com.example.myapplicationsearch.view.adapter.ProductAdapter
import com.example.myapplicationsearch.view.adapter.QueryAdapter
import com.example.myapplicationsearch.view.viewmodel.RoomViewModel
import com.example.myapplicationsearch.view.viewmodel.SearchViewModel
import com.example.myapplicationsearch.view.viewmodel.SearchViewModelFactory
import okhttp3.internal.notify
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    lateinit var viewModelSearch: SearchViewModel
    lateinit var viewModelFactory: SearchViewModelFactory
    private val mViewModelRoom by lazy { getViewModelRoom() }

    lateinit var mAdapterResults: ProductAdapter
    lateinit var mAdapterQueries: QueryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this)).apply { lifecycleOwner = this@MainActivity }
        setContentView(mBinding.root)

        initObs()
        initViews()

    }

    override fun onResume() {
        super.onResume()
        mViewModelRoom.getAllQueriesInDB()
    }

    override fun onBackPressed() {
        if(mBinding.recyclerViewQueries.visibility.equals(View.VISIBLE)){
            mBinding.recyclerViewQueries.visibility = View.GONE
        }else{
            super.onBackPressed()
        }
    }

    fun initObs(){
        viewModelFactory = SearchViewModelFactory()
        viewModelSearch = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModel::class.java)

        this.lifecycle.addObserver(viewModelSearch)
        this.lifecycle.addObserver(mViewModelRoom)

        viewModelSearch.mResponse.observe(this, Observer {
            viewModelSearch.mResponse.observe(this, Observer {
                if(it != null){
                    mAdapterResults= ProductAdapter(it.productDetails as ArrayList<ProductDetail>)
                    mBinding.recyclerViewResults.layoutManager = GridLayoutManager(this, 2)
                    mBinding.recyclerViewResults.adapter=mAdapterResults
                }else{
                    dialogNoResultsFound()
                }
                enableViews()
            })
        })
    }

    fun initViews(){
        mBinding.button.setOnClickListener {
            if(mBinding.searchView.editText?.text.toString().trim().isEmpty()){
                Toast.makeText(this, "Enter query to search", Toast.LENGTH_SHORT).show()
            }else{
                mBinding.recyclerViewQueries.visibility = View.GONE
                var querySearch = QuerySearch(UUID.randomUUID().toString(), mBinding.searchView.editText?.text.toString(), 0L)
                UseCaseRoomSearch.saveQueryRoom(querySearch, application)
                viewModelSearch.performSearch(mBinding.searchView.editText?.text.toString().trim())
                viewModelSearch.mResponse.value = SearchResponse("", "", 0, ArrayList(), "", "", "")
                disableViews()
            }
        }

        mBinding.searchView.editText?.setOnFocusChangeListener { v, hasFocus ->
            if(v.hasFocus()){
                mViewModelRoom.getAllQueriesInDB()
                mViewModelRoom.listQueries.observe(this, Observer {queries ->
                    if(queries.isNotEmpty()){
                        mBinding.recyclerViewQueries.visibility = View.VISIBLE
                        mAdapterQueries = QueryAdapter(queries.reversed())
                        mBinding.recyclerViewQueries.layoutManager = LinearLayoutManager(this)
                        mBinding.recyclerViewQueries.adapter=mAdapterQueries
                    }
                })
            }
        }

        mBinding.searchView.editText?.setOnClickListener {
            if(mBinding.searchView.editText!!.hasFocus() && mBinding.recyclerViewQueries.visibility.equals(View.GONE)){
                mViewModelRoom.getAllQueriesInDB()
                mViewModelRoom.listQueries.observe(this, Observer {queries ->
                    if(queries.isNotEmpty()){
                        mBinding.recyclerViewQueries.visibility = View.VISIBLE
                        mAdapterQueries = QueryAdapter(queries.reversed())
                        mBinding.recyclerViewQueries.layoutManager = LinearLayoutManager(this)
                        mBinding.recyclerViewQueries.adapter=mAdapterQueries
                    }
                })
            }
        }
    }

    fun disableViews(){
        mBinding.progressBar.visibility = View.VISIBLE
        mBinding.searchView.clearFocus()
        mBinding.searchView.isEnabled = false
        mBinding.button.isEnabled = false
    }

    fun enableViews(){
        mBinding.progressBar.visibility = View.GONE
        mBinding.searchView.editText?.setText("")
        mBinding.searchView.isEnabled = true
        mBinding.button.isEnabled = true
    }

    fun dialogNoResultsFound(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("No results found")
        builder.setMessage("Please try again with another query")
        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val dialog = builder.create()
        dialog.show()
    }

    private fun getViewModelRoom(): RoomViewModel {
        val factory = RoomViewModel.Factory(application)
        return ViewModelProviders.of(this, factory).get(RoomViewModel::class.java)
    }
}
