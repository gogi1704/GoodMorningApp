package com.example.goodmorningapp.extensions

import android.widget.TextView


fun TextView.parseDateTime(stringDate: String) {
    val d = stringDate.substring(3 , 16)
    this.text = d

}