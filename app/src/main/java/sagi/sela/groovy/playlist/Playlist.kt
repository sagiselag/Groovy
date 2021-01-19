package sagi.sela.groovy.playlist

import sagi.sela.groovy.R

data class Playlist(
    val id:String,
    val name:String,
    val category: String,
    val image: Int = R.mipmap.playlist
)