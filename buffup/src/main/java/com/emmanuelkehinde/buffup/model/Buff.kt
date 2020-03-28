package com.emmanuelkehinde.buffup.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Emmanuel Kehinde on 2020-03-27.
 */
data class Buff(
    var id: Long?,
    var author: Author?,
    var question: Question?,
    var answers: ArrayList<Answer>?
)

data class Author(
    @SerializedName("first_name")
    var firstName: String?,

    @SerializedName("last_name")
    var lastName: String?
)

data class Question(
    var id: Long?
)

data class Answer(
    var id: Long?
)