package com.symb.task.demux

import org.json.JSONArray

data class Question (
    var image:Int,
    val company:String,
    var college:String,
    var frequency:Int,
    var title:String,
    var description:String,
    var tags:JSONArray,
    var interview_type:String,
    var job_nature:String
)