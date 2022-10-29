package com.elliottsoftware.fetchtest.presentation.components.main

import androidx.annotation.DrawableRes
import com.elliottsoftware.fetchtest.R

sealed class BoardingPage(

    val title:String,
    val description:String
){

    object First:BoardingPage(
        title = "Fetch Rewards",
        description = "Coding Exercise - Software Engineering - Mobile - By Tristan Elliott"
    )

    object Second:BoardingPage(
        title = "Contact",
        description = "Contact Tristan at tristanelliott7@gmail.com"
    )

}
