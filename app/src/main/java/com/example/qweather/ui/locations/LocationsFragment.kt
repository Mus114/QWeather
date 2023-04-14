package com.example.qweather.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import android.content.Context.*
import android.util.Log
import com.example.qweather.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val locationsViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(LocationsViewModel::class.java)

        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLocations
        locationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
         val PREFS_NAME = "MyPrefs"
        val HISTORY_KEY = "history"
        val MAX_HISTORY_SIZE = 10

        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        fun getQueryHistory(): MutableList<String> {
            return sharedPreferences.getStringSet(HISTORY_KEY, emptySet())?.toMutableList() ?: mutableListOf()
        }
        fun saveQueryToHistory(query: String) {
            val history = getQueryHistory()
            history.remove(query)
            history.add(0, query)
            while (history.size > MAX_HISTORY_SIZE) {
                history.removeAt(history.size - 1)
            }
            val editor = sharedPreferences.edit()
            editor.putStringSet(HISTORY_KEY, history.toSet())
            editor.apply()
        }





        // Сохранение запроса в истории
        val query = "search query"
        saveQueryToHistory(query)

        // Получение списка запросов из истории
        val history = getQueryHistory()
        for (item in history) {
            Log.d("QUERY_HISTORY", item)
        }
        return root
    }

    private fun getSharedPreferences(prefsName: String, modePrivate: Int): Any {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}