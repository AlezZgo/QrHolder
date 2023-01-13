package com.example.qrholder.presentation.core

import com.example.qrholder.presentation.core.validation.LengthCondition
import com.example.qrholder.presentation.core.validation.TextValidationResult
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals


class LengthConditionTest {

    val lengthNoConditions = LengthCondition()

    val lengthValidatorMinNegative = LengthCondition(min = -1)
    val lengthValidatorMaxNegative = LengthCondition(max = -1)
    val lengthValidatorMaxZero = LengthCondition(min = 0)

    val lengthValidatorMinZero = LengthCondition(min = 0)

    val lengthValidatorMin3 = LengthCondition(min = 3)


    val lengthValidatorMin3Max35 = LengthCondition(min = 3, max = 35)
    val lengthValidatorMin3Max36 = LengthCondition(min = 3, max = 36)

    val emptyText = ""
    val blankText = "           "
    val textWith2Characters = "Th    "
    val textWith32Characters = "This text contains 32 characters"
    val textWith36SCharacters = "This text contains 36 characters hah"

    @Test
    fun `validate empty text`() {

        assertEquals(
            TextValidationResult.Success(emptyText),
            lengthNoConditions.validate(emptyText)
        )

        assertEquals(
            TextValidationResult.Success(emptyText),
            lengthValidatorMinZero.validate(emptyText)
        )

        assertEquals(
            TextValidationResult.Error("This field cannot be empty"),
            lengthValidatorMin3.validate(emptyText)
        )

        assertEquals(
            TextValidationResult.Error("This field cannot be empty"),
            lengthValidatorMin3Max35.validate(emptyText)
        )

    }

    @Test
    fun `validate blank text`() {

        assertEquals(
            TextValidationResult.Success(blankText),
            lengthNoConditions.validate(blankText)
        )

        assertEquals(
            TextValidationResult.Success(blankText),
            lengthValidatorMinZero.validate(blankText)
        )

        assertEquals(
            TextValidationResult.Error("This field cannot be empty"),
            lengthValidatorMin3.validate(blankText)
        )

        assertEquals(
            TextValidationResult.Error("This field cannot be empty"),
            lengthValidatorMin3Max35.validate(blankText)
        )
    }

    @Test
    fun `validate text with 32 characters`() {

        assertEquals(
            TextValidationResult.Success(textWith32Characters),
            lengthNoConditions.validate(textWith32Characters)
        )

        assertEquals(
            TextValidationResult.Success(textWith32Characters),
            lengthValidatorMinZero.validate(textWith32Characters)
        )

        assertEquals(
            TextValidationResult.Success(textWith32Characters),
            lengthValidatorMin3.validate(textWith32Characters)
        )

        assertEquals(
            TextValidationResult.Success(textWith32Characters),
            lengthValidatorMin3Max35.validate(textWith32Characters)
        )

        assertEquals(
            TextValidationResult.Success(textWith32Characters),
            lengthValidatorMin3Max35.validate(textWith32Characters)
        )
    }

    @Test
    fun `validate text with 36 characters`() {

        assertEquals(
            TextValidationResult.Success(textWith36SCharacters),
            lengthNoConditions.validate(textWith36SCharacters)
        )

        assertEquals(
            TextValidationResult.Success(textWith36SCharacters),
            lengthValidatorMinZero.validate(textWith36SCharacters)
        )

        assertEquals(
            TextValidationResult.Success(textWith36SCharacters),
            lengthValidatorMin3.validate(textWith36SCharacters)
        )

        assertEquals(
            TextValidationResult.Error("This field must contain no more than 35 characters"),
            lengthValidatorMin3Max35.validate(textWith36SCharacters)
        )

        assertEquals(
            TextValidationResult.Success(textWith36SCharacters),
            lengthValidatorMin3Max36.validate(textWith36SCharacters)
        )
    }

    @Test
    fun `validate text with 2 characters`() {

        assertEquals(
            TextValidationResult.Success(textWith2Characters),
            lengthNoConditions.validate(textWith2Characters)
        )

        assertEquals(
            TextValidationResult.Success(textWith2Characters),
            lengthValidatorMinZero.validate(textWith2Characters)
        )

        assertEquals(
            TextValidationResult.Error("This field must contain at least 3 characters"),
            lengthValidatorMin3.validate(textWith2Characters)
        )

        assertEquals(
            TextValidationResult.Error("This field must contain at least 3 characters"),
            lengthValidatorMin3Max35.validate(textWith2Characters)
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `constructor argument less then zero`() {

        lengthValidatorMinNegative.validate(emptyText)
        lengthValidatorMaxNegative.validate(emptyText)
        lengthValidatorMinNegative.validate(blankText)
        lengthValidatorMaxNegative.validate(blankText)
        lengthValidatorMinNegative.validate(textWith32Characters)
        lengthValidatorMaxNegative.validate(textWith32Characters)
        lengthValidatorMaxZero.validate(textWith32Characters)

    }

}