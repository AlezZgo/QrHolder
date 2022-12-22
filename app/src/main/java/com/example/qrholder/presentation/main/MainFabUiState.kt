package com.example.qrholder.presentation.main

import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

sealed class MainFabUiState {

    abstract fun show(
        parentFab: FloatingActionButton,
        createQrCodeFab: FloatingActionButton,
        chooseFromGalleryQrCodeFab: FloatingActionButton,
        ScanFromCameraQrCodeFab: FloatingActionButton,
        onParentFabClick: (mainFabUiState: MainFabUiState) -> Unit
    )

    object Closed : MainFabUiState() {
        override fun show(
            parentFab: FloatingActionButton,
            createQrCodeFab: FloatingActionButton,
            chooseFromGalleryQrCodeFab: FloatingActionButton,
            ScanFromCameraQrCodeFab: FloatingActionButton,
            onParentFabClick: (mainFabUiState: MainFabUiState) -> Unit
        ) {
            parentFab.isVisible = true
            createQrCodeFab.isVisible = false
            chooseFromGalleryQrCodeFab.isVisible = false
            ScanFromCameraQrCodeFab.isVisible = false
            parentFab.setOnClickListener { onParentFabClick.invoke(this) }
        }

    }

    object Opened : MainFabUiState() {
        override fun show(
            parentFab: FloatingActionButton,
            createQrCodeFab: FloatingActionButton,
            chooseFromGalleryQrCodeFab: FloatingActionButton,
            ScanFromCameraQrCodeFab: FloatingActionButton,
            onParentFabClick: (mainFabUiState: MainFabUiState) -> Unit
        ) {
            parentFab.isVisible = true
            createQrCodeFab.isVisible = true
            chooseFromGalleryQrCodeFab.isVisible = true
            ScanFromCameraQrCodeFab.isVisible = true
            parentFab.setOnClickListener { onParentFabClick.invoke(this) }
        }

    }

}
