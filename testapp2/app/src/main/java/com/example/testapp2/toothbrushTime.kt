package com.example.testapp2

import java.time.LocalTime

val currentTime: LocalTime = LocalTime.now()
val morningStart = LocalTime.of(6, 0)   //6 am
val morningEnd = LocalTime.of(16, 30)      //2 pm

val nightStart = LocalTime.of(18, 0)    // 6 pm
val nightEnd = LocalTime.of(23, 59)     //11 59pm

val streakStart = 0

data class brushCheck (
    var morningCheck: Boolean = false,
    var nightCheck: Boolean = false

)

fun isToothbrushTimeMorning(now:LocalTime): Boolean {
    return (now.isAfter(morningStart) && now.isBefore(morningEnd))


}
fun isToothbrushTimeNight(now:LocalTime): Boolean {
    return (now.isAfter(nightStart) && now.isBefore(nightEnd))

}


val isBrushTimeMorning = isToothbrushTimeMorning(currentTime)
val isBrushTimeNight = isToothbrushTimeNight(currentTime)