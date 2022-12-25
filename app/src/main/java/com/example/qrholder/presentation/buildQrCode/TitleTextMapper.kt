package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.core.Mapper
import javax.inject.Inject


interface TitleTextMapper : Mapper<String, Unit> {

    class Base @Inject constructor(
        private val manageResources: ManageResources,
        private val communication: TitleUiStateCommunication,
    ) : TitleTextMapper {
        override fun map(source: String) {
            val sourceTrimmed = source.trim()

            communication.map(
                if (sourceTrimmed.isBlank())
                    InputEditTextUiState.Error(
                        manageResources.string(
                            R.string.input_edit_text_error_this_field_cannot_be_empty
                        )
                    )
                else if (sourceTrimmed.length < 5)
                    InputEditTextUiState.Error(
                        manageResources.string(
                            R.string.input_edit_text_error_this_field_must_contain_at_least_5_characters
                        )
                    )
                else if (sourceTrimmed.length > 50)
                    InputEditTextUiState.Error(
                        manageResources.string(
                            R.string.title_input_edit_text_error_this_field_must_contain_no_more_then_50_characters
                        )
                    )
                else InputEditTextUiState.NoError
            )
        }
    }
}