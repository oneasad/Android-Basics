package com.oneasad.artspace

class Art {
    var title: String = ""
    var artist: String = ""
    var year: String = ""
    var image: Int = 0

    constructor(title: String, artist: String, year: String, image: Int) {
        this.title = title
        this.artist = artist
        this.year = year
        this.image = image
    }

    companion object
    {
        fun getArts(): List<Art> {
            val arts = mutableListOf<Art>()
            arts.add(Art("Title 1", "Artist 1", "2021", R.drawable.castle))
            arts.add(Art("Title 2", "Artist 2", "2022", R.drawable.flowers))
            arts.add(Art("Title 3", "Artist 3", "2023", R.drawable.forest))
            arts.add(Art("Title 4", "Artist 4", "2024", R.drawable.patterns))
            return arts
        }
    }
}