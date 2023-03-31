package com.android.simple.movie.list.app.ui.movie_list

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.simple.movie.list.app.R
import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.uitls.publishSubject

class FiltersDialogFragment : DialogFragment() {

    val onApplyFilters = publishSubject<List<Genre>>()

    private val filtersItems = ArrayList<Pair<Genre, Boolean>>()

    fun setFilters(data: List<Pair<Genre, Boolean>>) {
        filtersItems.clear()
        filtersItems.addAll(data)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Genre>()
            val builder = AlertDialog.Builder(it)
            // All genres to be displayed in the dialog
            val genres = filtersItems.map { it.first.name }.toTypedArray()
            // A list of boolean values ​​that indicate whether an item is selected
            val selected = filtersItems.map { it.second }.toList().toBooleanArray()
            // Adds previously selected
            selectedItems.addAll(filtersItems.filter { it.second }.map { it.first })

            builder.setTitle(R.string.filter_by_genre)
                .setMultiChoiceItems(genres, selected) { dialog, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(filtersItems[which].first)
                    } else if (selectedItems.contains(filtersItems[which].first)) {
                        selectedItems.remove(filtersItems[which].first)
                    }
                }
                .setPositiveButton(R.string.apply) { dialog, id ->
                    onApplyFilters.onNext(selectedItems)
                }
                .setNegativeButton(R.string.cancel) { dialog, id -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}