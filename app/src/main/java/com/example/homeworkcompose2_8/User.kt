package com.example.homeworkcompose2_8

class User : java.io.Serializable {

    var email = ""

    var password = ""

    fun isEmailValid(): Boolean{
        return email != "" && email.contains('@')
    }

    fun isPasswordValid(): Boolean{
        return password.length >= 8
    }

}