package com.alezzgo.qrholder.presentation.core

import android.graphics.BitmapFactory
import android.util.Log
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.core.ManageResources
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage
import javax.inject.Inject


interface ScanQrCodeFromImage {

    fun scanQrCode(
        filePath: String,
        onSuccess: (resultRaw: String) -> Unit,
        onFailure: (error: String) -> Unit = {},
        onCancelled: () -> Unit = {},
        onComplete: () -> Unit = {},
    )

    class Base @Inject constructor(
        private val scanner: BarcodeScanner,
        private val manageResources: ManageResources
    ) : ScanQrCodeFromImage {
        override fun scanQrCode(
            filePath: String,
            onSuccess: (resultRaw: String) -> Unit,
            onFailure: (error: String) -> Unit,
            onCancelled: () -> Unit,
            onComplete: () -> Unit
        ) {
            try {

                scanner.process(InputImage.fromBitmap(BitmapFactory.decodeFile(filePath), 0))
                    .addOnSuccessListener {
                        val result = it.firstOrNull()
                        if (result != null && result.rawValue != null) {
                            onSuccess.invoke(result.rawValue!!)
                        } else {
                            onFailure.invoke(manageResources.string(R.string.qr_code_was_not_found))
                        }

                    }.addOnFailureListener { exception ->
                        onFailure.invoke(
                            exception.localizedMessage
                                ?: manageResources.string(R.string.defaultErrorMessage)
                        )
                    }.addOnCanceledListener {
                        onCancelled.invoke()
                    }.addOnCompleteListener {
                        onComplete.invoke()
                    }


            } catch (error: Exception) {
                Log.e(
                    manageResources.string(R.string.error),
                    error.message ?: manageResources.string(R.string.defaultErrorMessage)
                )
                Log.e(manageResources.string(R.string.error), error.stackTraceToString())
                onFailure.invoke(
                    error.localizedMessage ?: manageResources.string(R.string.defaultErrorMessage)
                )
            }
        }
    }
}

