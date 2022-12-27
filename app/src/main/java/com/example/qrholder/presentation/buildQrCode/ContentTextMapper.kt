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
            val sourceTrimmed = source.trim()

            communication.map(
                when {
                    sourceTrimmed.isBlank() -> InputEditTextUiState.Error(
                        manageResources.string(
                            R.string.input_edit_text_error_this_field_cannot_be_empty
                        )
                    )
                    sourceTrimmed.length < 5 ->
                        InputEditTextUiState.Error(
                            manageResources.string(
                                R.string.input_edit_text_error_this_field_must_contain_at_least_d_characters,
                                CONTENT_MIN_LENGTH.toString()
                            )
                        )
                    sourceTrimmed.length > 300 ->
                        InputEditTextUiState.Error(
                            manageResources.string(
                                R.string.input_edit_text_error_this_field_must_contain_no_more_then_d_characters,
                                CONTENT_MAX_LENGTH.toString()
                            )
                        )
                    else -> InputEditTextUiState.NoError
                }
            )
        }

    }
    companion object{
        private const val CONTENT_MIN_LENGTH = 5
        private const val CONTENT_MAX_LENGTH = 300
    }

}