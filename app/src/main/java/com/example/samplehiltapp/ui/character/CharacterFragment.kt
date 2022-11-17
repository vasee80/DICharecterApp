package com.example.samplehiltapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplehiltapp.R
import com.example.samplehiltapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterFragment : Fragment(), CharactersAdapter.CharacterItemListener {

    private val TAG = "CharacterFragment"

    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharactersAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.character_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        setupObservers(view)
    }

    private fun setupObservers(view: View) {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        Timber.d(TAG, "setupObservers: ${it.data[0].name}")
                        adapter.setItems(ArrayList(it.data))
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_characterFragment_to_characterDetailFragment,
            bundleOf("id" to characterId)
        )
    }
}