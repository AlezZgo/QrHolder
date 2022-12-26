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
                when {
                    sourceTrimmed.isBlank() -> InputEditTextUiState.Error(
                        manageResources.string(
                            R.string.input_edit_text_error_this_field_cannot_be_empty
                        )
                    )
                    sourceTrimmed.length < TITLE_MIN_LENGTH ->
                        InputEditTextUiState
                            .Error(
                                manageResources.stringParametrized(
                                    R.string.input_edit_text_error_this_field_must_contain_at_least_d_characters,
                                    TITLE_MIN_LENGTH
                                )
                            )
                    sourceTrimmed.length > TITLE_MAX_LENGTH ->
                        InputEditTextUiState.Error(
                            manageResources.stringParametrized(
                                R.string.input_edit_text_error_this_field_must_contain_no_more_then_d_characters,
                                TITLE_MAX_LENGTH
                            )
                        )
                    else -> InputEditTextUiState.NoError
                }
            )
        }
    }

    companion object {
        private const val TITLE_MIN_LENGTH = 5
        private const val TITLE_MAX_LENGTH = 50
    }
}