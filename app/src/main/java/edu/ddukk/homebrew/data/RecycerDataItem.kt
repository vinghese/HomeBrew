package edu.ddukk.homebrew.data

import edu.ddukk.homebrew.R

data class RecycerDataItem(val image: Int, val dept: String) {

    companion object {
        fun getData(): List<RecycerDataItem> {
            return listOf(
                RecycerDataItem(R.drawable.ic_launcher_foreground,"DDUKK"),
                RecycerDataItem(R.drawable.ic_launcher_foreground, "DCS"),
                RecycerDataItem(R.drawable.bluenoti, "DCA"),
                RecycerDataItem(R.drawable.ic_launcher_foreground, "SOE"),
                RecycerDataItem(R.drawable.ic_launcher_foreground, "MARINE")
                )
        }
    }
}