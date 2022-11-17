package com.example.samplehiltapp.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.samplehiltapp.R
import com.example.samplehiltapp.data.entities.Character
import com.example.samplehiltapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(){

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.character_detail_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers(view)
    }

    private fun setupObservers(view: View) {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!, view)
                    view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
                    view.findViewById<ConstraintLayout>(R.id.character_cl).visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
                    view.findViewById<ConstraintLayout>(R.id.character_cl).visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(character: Character, view: View) {
        view.findViewById<TextView>(R.id.name).text = character.name
        view.findViewById<TextView>(R.id.species).text = character.species
        view.findViewById<TextView>(R.id.status).text = character.status
        view.findViewById<TextView>(R.id.gender).text = character.gender
        Glide.with(view.rootView)
            .load(character.image)
            .transform(CircleCrop())
            .into(view.findViewById<AppCompatImageView>(R.id.image))
    }
}