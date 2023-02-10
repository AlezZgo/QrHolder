package com.alezzgo.qrholder.presentation.scanFromGallery

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.presentation.core.ManageExternalStorageImages
import com.alezzgo.qrholder.presentation.core.ObserveUiState
import com.alezzgo.qrholder.presentation.core.ScanQrCodeFromImage
import com.alezzgo.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.alezzgo.qrholder.presentation.core.viewmodel.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ScanFromGalleryBottomSheetViewModel @Inject constructor(
    private val galleryImages: GalleryImagesCommunication,
    private val scanResult: ScanResultCommunication,
    private val manageExternalStorageImages: ManageExternalStorageImages,
    private val imageScanner: ScanQrCodeFromImage
) : AbstractViewModel(), ObserveUiState<List<GalleryImageUi>>, ObserveScanQrCodeResult {

    override fun init() {}

    fun resetGalleryImages() = galleryImages.map(
        manageExternalStorageImages.allShownImagesPath().reversed().map { path ->
            GalleryImageUi(
                path = path, selected = false
            )
        }
    )

    fun changeSelectedState(oldImage: GalleryImageUi) = galleryImages.changeSelected(oldImage)

    fun createQrCodeFromImage() {
        galleryImages.fetchSelected().scanQrCode(
            imageScanner = imageScanner,
            onSuccess = {
                scanResult.map(ScanQrCodeResult.Success(it))
            },
            onFailure = {
                scanResult.map(ScanQrCodeResult.Error(it))
            }
        )
    }


    override fun observeUiState(owner: LifecycleOwner, observer: Observer<List<GalleryImageUi>>) =
        galleryImages.observe(owner, observer)

    override fun observeScanResult(owner: LifecycleOwner, observer: Observer<ScanQrCodeResult>) =
        scanResult.observe(owner, observer)

}

interface ObserveScanQrCodeResult {
    fun observeScanResult(owner: LifecycleOwner, observer: Observer<ScanQrCodeResult>)
}

interface ScanResultCommunication : Communication.Mutable<ScanQrCodeResult> {
    class Base @Inject constructor() : Communication.Post<ScanQrCodeResult>(),
        ScanResultCommunication
}

interface GalleryImagesCommunication : Communication.Mutable<List<GalleryImageUi>> {

    fun changeSelected(selectedItem: GalleryImageUi)

    fun fetchSelected(): GalleryImageUi

    class Base @Inject constructor() : Communication.Post<List<GalleryImageUi>>(),
        GalleryImagesCommunication {

        override fun changeSelected(selectedItem: GalleryImageUi) {
            liveData.postValue(liveData.value?.map { oldListItem ->
                if (oldListItem.matchesId(selectedItem))
                    selectedItem.opposite()
                else
                    oldListItem.unselected()
            }
            )
        }

        override fun fetchSelected() = liveData.value?.filter { it.isSelected() }!!.first()
    }
}
