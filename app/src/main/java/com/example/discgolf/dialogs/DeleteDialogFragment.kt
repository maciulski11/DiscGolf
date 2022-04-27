package com.example.discgolf.dialogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.discgolf.R
import com.example.discgolf.base.BaseDialogFragment
import com.example.discgolf.room.player.Player
import com.example.discgolf.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.dialog_delete.*

class DeleteDialogFragment(private val action: () -> Unit): BaseDialogFragment() {
    override val layout = R.layout.dialog_delete

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        delete.setOnClickListener {
            action()
            dismiss()
        }

        back.setOnClickListener {
            dismiss()
        }
    }

}