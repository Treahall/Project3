package com.example.trever.project3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        //

        var ab = getSupportActionBar()
        ab?.setTitle("Project 3")
        ab?.setSubtitle("Friends List")

        //To get the back button in the action bar to show and work
        ab?.setDisplayHomeAsUpEnabled(true) //Set parent activity name in Manifest

        if(this.intent.hasExtra("userEmail")) {
            mUserEmail = this.intent.getStringExtra("userEmail")
            mUserImageUrl = this.intent.getStringExtra("userImageUrl")
        }
        else {
            Log.w("debug", "Activity requires a logged in user")

        }
        attachRecyclerView()

        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "That's Me"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "That's Me 2"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "That's Me 3"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "That's Me 4"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "That's Me 5"))
    }

    private var mUserEmail = ""
    private var mUserImageUrl = ""

    // Recycler view stuff

    lateinit var adapter: CellViewAdapter

    private fun attachRecyclerView() {
        var manager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        initilizeRecyclerView()
    }

    private fun initilizeRecyclerView() {
        adapter = CellViewAdapter { view, position -> rowTapped(position) }
        recyclerView.adapter = adapter
    }

    private fun rowTapped(position: Int) {
        Log.d("debug", adapter.rows[position].headerTxt + " " + adapter.rows[position].messageText)
    }

    private fun addCellToRecyclerView(cellData: CellData) {
        adapter.addCellData(cellData)
        recyclerView.smoothScrollToPosition(adapter.getCellCount() - 1)
    }
}