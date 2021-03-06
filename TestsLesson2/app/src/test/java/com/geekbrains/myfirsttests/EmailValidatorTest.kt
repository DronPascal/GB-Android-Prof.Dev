package com.geekbrains.myfirsttests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_NoAtSignNoDomain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name"))
    }

    @Test
    fun emailValidator_NoDomain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@"))
    }

    @Test
    fun emailValidator_NoAtSignNoDomainName_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name.com"))
    }

    @Test
    fun emailValidator_NoUsernameNoDomainName_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@.com"))
    }

    @Test
    fun emailValidator_NoDomainName_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@.com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_InvalidEmailEmptyTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email."))
    }

    @Test
    fun emailValidator_InvalidEmailBlankTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email.   "))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleAtSign_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@@email.com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null))
    }
}
