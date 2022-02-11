package com.example.catfacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.adapters.CatFactAdapter
import com.example.catfacts.data.MainViewModel
import com.example.catfacts.pojo.CatFact

const val ARG_OBJECT = "object"

class SliderFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val recyclerViewFacts: RecyclerView = view.findViewById(R.id.recyclerViewFacts)
            val adapter = CatFactAdapter()
            recyclerViewFacts.layoutManager = LinearLayoutManager(activity)
            recyclerViewFacts.adapter = adapter
            val tabPosition = getInt(ARG_OBJECT)
            viewModel = requireActivity().run {ViewModelProvider(this)[MainViewModel::class.java]}
            if (tabPosition==0) {
                viewModel.loadData()
                viewModel.catFactsList?.observe(requireActivity(), Observer {
                    adapter.clear()
                    adapter.catFactList = ArrayList(it)
                    Log.i("MyRes", " adapter.catFactList = ArrayList(it) сработал")
                })
            } else if (tabPosition==1) {
                viewModel.favFactList?.observe(requireActivity(), Observer {
                    Log.i("MyRes", "viewModel.favFactList?.observe сработал")
                    adapter.clear()
                    for (i in it) {
                        adapter.catFactList.add(CatFact(i.FactId,i.text))
                        adapter.notifyDataSetChanged()
                    }
                })
            }
            adapter.onfactClick = {
                val intent = Intent(activity,DetailActivity::class.java)
                intent.putExtra("text", it.text)
                intent.putExtra("id", it.Factid)
                startActivity(intent)
            }
        }
    }
}
