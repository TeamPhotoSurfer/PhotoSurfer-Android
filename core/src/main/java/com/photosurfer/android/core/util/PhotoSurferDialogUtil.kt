package com.photosurfer.android.core.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.photosurfer.android.core.constant.DialogMode
import com.photosurfer.android.core.databinding.DialogPhotoSurferUtilBinding
import com.photosurfer.android.shared.R.style

class PhotoSurferDialogUtil(
    private val dialogMode: String,
    private val doAfterConfirm: () -> Unit,
    private val tagName: String? = null
) :
    DialogFragment() {
    private var _binding: DialogPhotoSurferUtilBinding? = null
    val binding get() = _binding!!

    private lateinit var dialogClass: DialogMode

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), style.DisableDialogBackground)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogPhotoSurferUtilBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogClass = DialogMode.findClassByDialogMode(dialogMode)
            ?: throw IllegalStateException("Dialog Mode를 Enum Class에 추가해주세요")

        setLayout(view)
        setTitle()
        setDescription()
        setConfirmText()
        clickCancelListener()
        clickConfirmListener()
    }

    private fun setLayout(view: View) {
        view.layoutParams.width = (resources.displayMetrics.widthPixels * 0.80).toInt()
    }

    private fun setTitle() {
        binding.title =
            if (tagName == null) dialogClass.title
            else "'$tagName' ${dialogClass.title}"
    }

    private fun setDescription() {
        binding.description = dialogClass.description
    }

    private fun setConfirmText() {
        binding.confirmText = dialogClass.confirmText
    }

    private fun clickCancelListener() {
        binding.btnCancel.setOnClickListener { dismiss() }
    }

    private fun clickConfirmListener() {
        binding.btnConfirm.setOnClickListener {
            dismiss()
            doAfterConfirm()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
