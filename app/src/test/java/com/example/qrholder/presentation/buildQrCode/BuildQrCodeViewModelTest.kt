package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.model.QrCodeUi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BuildQrCodeViewModelTest : AbstractBuildQrCodeViewModelTest() {

    @Test
    fun `build qr code with success`()  = runBlocking {

        val newTitle = "New Title"
        val newContent = "New Content"
        val expectedImagePath = "//InternalStorageLink"

        validateTitle.changeValidationResult(TextValidationResult.Success(newTitle))
        validateContent.changeValidationResult(TextValidationResult.Success(newContent))

        repository.changeExpectedSaveImageResult(ImagePath(expectedImagePath))

        viewModel.changeTitle(newTitle)
        viewModel.changeContent(newContent)

        viewModel.build()

        assertEquals(1, createQrCodeImage.createQrCodeImageCalledList.size)
        assertEquals(newContent, createQrCodeImage.createQrCodeImageCalledList[0])

        assertEquals(1, repository.saveImageCalledList.size)
        assertEquals(ImagePath(expectedImagePath), repository.saveImageCalledList[0])

        assertEquals(1, qrCodeBuiltCommunication.buildResult.size)
        assertEquals(
            QrCodeBuildResult.Success(QrCodeUi(newTitle, newContent, expectedImagePath)),
            qrCodeBuiltCommunication.buildResult[0]
        )

    }

    @Test
    fun `build qr code with errors`()  = runBlocking {

        val newTitle = "New Title"
        val errorTitle = "New"
        val newContent = "New Content"
        val errorContent = "         "

        validateTitle.changeValidationResult(
            TextValidationResult.Error("This field must contain at least 5 characters")
        )
        validateContent.changeValidationResult(
            TextValidationResult.Success(newContent)
        )

        viewModel.changeTitle(errorTitle)
        viewModel.changeContent(newContent)

        viewModel.build()

        assertEquals(0, createQrCodeImage.createQrCodeImageCalledList.size)
        assertEquals(0, repository.saveImageCalledList.size)
        assertEquals(0, qrCodeBuiltCommunication.buildResult.size)

        validateContent.changeValidationResult(
            TextValidationResult.Error("This field must contain at least 5 characters")
        )
        validateTitle.changeValidationResult(
            TextValidationResult.Success(newTitle)
        )

        viewModel.changeTitle(newTitle)
        viewModel.changeContent(errorContent)

        viewModel.build()

        assertEquals(0, createQrCodeImage.createQrCodeImageCalledList.size)
        assertEquals(0, repository.saveImageCalledList.size)
        assertEquals(0, qrCodeBuiltCommunication.buildResult.size)
    }


}