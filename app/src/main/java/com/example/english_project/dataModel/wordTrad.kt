package com.example.english_project.dataModel

import java.time.ZoneId
import java.time.ZonedDateTime


data class wordTrad(
    var french : String,
    var english : String,
    var multiplier : Int = 15,
    var addingDate : Long = System.currentTimeMillis(),
    var id : Int=0 // ne pas renseigner d'Id lors de la création d'un wordTrad, géré par la BDD
)
