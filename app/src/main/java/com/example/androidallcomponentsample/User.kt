package com.example.androidallcomponentsample

data class User(
 val fullName : String = "",
 val email : String  = ""){

override fun toString() : String{
    return """
   $fullName
    $email
    """.trimIndent()
}

}

