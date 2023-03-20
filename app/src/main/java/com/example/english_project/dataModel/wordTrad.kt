package com.example.english_project.dataModel

data class wordTrad(
    var french : String,
    var english : String,
    var id : Int=0, // ne pas renseigner d'Id lors de la création d'un wordTrad, géré par la BDD
    var multiplier : Int = 1
)
