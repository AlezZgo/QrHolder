package com.example.qrholder.presentation.scanFromGallery

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanFromGalleryBottomSheetViewModel @Inject constructor(
    private val galleryImages: GalleryImagesCommunication
) : AbstractViewModel(), ObserveUiState<List<GalleryImageUi>> {

    override fun init() {
        galleryImages.map(
            listOf(
                GalleryImageUi(path = "daawdawd", selected = false),
                GalleryImageUi(path = "daawd3213awd", selected = false),
                GalleryImageUi(path = "daa2331wd312awd", selected = false),
                GalleryImageUi(path = "daa123123123123wdawd", selected = false),
                GalleryImageUi(path = "daa1231231231231231wdawd", selected = false),
                GalleryImageUi(path = "da231241251235awdawd", selected = false),
                GalleryImageUi(path = "daa5342423523wdawd", selected = false),
                GalleryImageUi(path = "daa5235235235wdawd", selected = false),
                GalleryImageUi(path = "da235235235awdawd", selected = false),
                GalleryImageUi(path = "daa2352341245235w23141dawd", selected = false),
                GalleryImageUi(path = "da23523523wdawd", selected = false),
                GalleryImageUi(path = "daa523523523wdawd", selected = false),
                GalleryImageUi(path = "daa52352352wdawd", selected = false),
                GalleryImageUi(path = "daa35235235wdawd", selected = false),
                GalleryImageUi(path = "daa2352324141223415565235wdawd", selected = false),
                GalleryImageUi(path = "daa2353523wdawd", selected = false),
                GalleryImageUi(path = "daaw235235252dawd", selected = false),
                GalleryImageUi(path = "daaw5235235dawd", selected = false),
                GalleryImageUi(path = "daaw53523523dawd", selected = false),
            )
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