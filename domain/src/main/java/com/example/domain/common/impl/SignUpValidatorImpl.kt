package com.example.domain.common.impl

import com.example.domain.common.SignUpValidator
import javax.inject.Inject

internal class SignUpValidatorImpl @Inject constructor() : SignUpValidator{
    override fun isEmailValidity(email: String): Boolean {
        return email.matches("""^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$""".toRegex())
    }

    override fun isPasswordValidity(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$".toRegex())
    }

    override fun isNickNameValidity(nickname: String): Boolean {
        return nickname.matches("^[가-힣a-zA-Z0-9]{2,6}$".toRegex())
    }

    override fun isDateValidity(date: String): Boolean {
        return date.matches("^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$".toRegex())
    }
}