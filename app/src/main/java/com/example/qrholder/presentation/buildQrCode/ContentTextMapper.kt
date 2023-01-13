package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.core.Mapper
import javax.inject.Inject

interface ContentTextMapper : Mapper<String, Unit> {
    class Base @Inject constructor(
        private val manageResources: ManageResources,
        private val communication: ContentUiStateCommunication
    ) : ContentTextMapper {
        override fun map(source: String) {


            communication.map(

            )
        }

    }
    companion object{
        private const val CONTENT_MIN_LENGTH = 5
        private const val CONTENT_MAX_LENGTH = 300
    }

}