package com.example.qrholder.presentation.main

import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

sealed class MainFabUiState {

    abstract fun opposite() : MainFabUiState

    abstract fun show(
        parentFab: FloatingActionButton,
        buildQrCodeFab: FloatingActionButton,
        chooseFromGalleryQrCodeFab: FloatingActionButton,
        ScanFromCameraQrCodeFab: FloatingActionButton,
        onParentFabClick: () -> Unit
    )

    object Closed : MainFabUiState() {

        override fun opposite() = Opened

        override fun show(
            parentFab: FloatingActionButton,
            buildQrCodeFab: FloatingActionButton,
            chooseFromGalleryQrCodeFab: FloatingActionButton,
            ScanFromCameraQrCodeFab: FloatingActionButton,
            onParentFabClick: () -> Unit
        ) {
            parentFab.isVisible = true
            buildQrCodeFab.isVisible = false
            chooseFromGalleryQrCodeFab.isVisible = false
            ScanFromCameraQrCodeFab.isVisible = false
            parentFab.setOnClickListener { onParentFabClick.invoke() }
        }

    }

    object Opened : MainFabUiState() {

        override fun opposite() = Closed

        override fun show(
            parentFab: FloatingActionButton,
            buildQrCodeFab: FloatingActionButton,
            chooseFromGalleryQrCodeFab: FloatingActionButton,
            ScanFromCameraQrCodeFab: FloatingActionButton,
            onParentFabClick: () -> Unit
        ) {
            parentFab.isVisible = true
            buildQrCodeFab.isVisible = true
            chooseFromGalleryQrCodeFab.isVisible = true
            ScanFromCameraQrCodeFab.isVisible = true
            parentFab.setOnClickListener { onParentFabClick.invoke() }
        }

    }

}
