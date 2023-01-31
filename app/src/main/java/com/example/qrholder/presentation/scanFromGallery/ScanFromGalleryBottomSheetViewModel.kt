package com.example.qrholder.presentation.scanFromGallery

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.ManageExternalStorageImages
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ScanFromGalleryBottomSheetViewModel @Inject constructor(
    private val galleryImages: GalleryImagesCommunication,
    private val manageExternalStorageImages: ManageExternalStorageImages,
) : AbstractViewModel(), ObserveUiState<List<GalleryImageUi>> {

    override fun init() {
        galleryImages.map(
            manageExternalStorageImages.allShownImagesPath().map { path ->
                GalleryImageUi(
                    path = path, selected = false
                )
            }
        )
    }

    fun changeSelectedState(oldImage: GalleryImageUi) = galleryImages.changeSelected(oldImage)

    override fun observeUiState(owner: LifecycleOwner, observer: Observer<List<GalleryImageUi>>) =
        galleryImages.observe(owner, observer)

}

interface GalleryImagesCommunication : Communication.Mutable<List<GalleryImageUi>> {

    fun changeSelected(selectedItem: GalleryImageUi)

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
    }
}