package edu.ddukk.homebrew.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.homebrew.DAO.TimeTableViewDAO
import edu.ddukk.homebrew.R
import edu.ddukk.homebrew.adapters.RecycerTimetableAdapter
import edu.ddukk.homebrew.databases.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentTimetable : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val db: AppDatabase by lazy { AppDatabase.getDatabase(appContext) }
    lateinit var appContext: Context
    val dao: TimeTableViewDAO by lazy { db.viewDAO() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appContext = requireContext()

        val recyclerView: RecyclerView = view.findViewById(R.id.recycerTimetable)

        recyclerView.layoutManager = GridLayoutManager(view.context, 2)

        lifecycleScope.launch {

            var adapter: RecycerTimetableAdapter = RecycerTimetableAdapter(emptyList())
            recyclerView.adapter = adapter

            adapter.isLoading = true
            adapter.notifyItemInserted(adapter.itemCount)

            val items = withContext(Dispatchers.IO) {
                delay(5000)
                dao.getAllTimeTableView("Monday")
            }

            adapter.isLoading = false
            adapter = RecycerTimetableAdapter(items)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

//            val adapter = RecycerTimetableAdapter(
//                dao.getAllTimeTableView("Monday")
//            )
//            recyclerView.adapter = adapter
//            adapter.notifyDataSetChanged()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTimetable().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}