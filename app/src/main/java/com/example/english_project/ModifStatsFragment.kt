package com.example.english_project

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.databinding.FragmentMenuBinding
import com.example.english_project.databinding.FragmentModifStatsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ModifStatsFragment : Fragment() {

    lateinit var binding : FragmentModifStatsBinding
    private val args : ModifStatsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modif_stats,container,false)


        val word = WordsManager.getWord(args.idWordTrad)

        binding.etstatfrench.hint = word!!.french
        binding.etstatenglish.hint = word!!.english

        binding.butdelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Do you really want to delete this translation?")
                .setMessage("Attention, you are about to delete this translation.")
                .setNeutralButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                    }
                })
                .setPositiveButton("Delete", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        WordsManager.deleteWord(word.id)
                        val action = ModifStatsFragmentDirections.actionModifStatsFragmentToDatabaseFragment()
                        findNavController().navigate(action)

                    }
                })
                .show()
        }

        binding.butmodify.setOnClickListener {

        }


        return binding.root
    }

}