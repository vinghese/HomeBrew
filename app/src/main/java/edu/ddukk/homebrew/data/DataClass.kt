package edu.ddukk.homebrew.data

data class DataClass(val data: String) {
    companion object {
        fun SimpleListData(): List<DataClass> {
            val simpleData : List<DataClass> = listOf(DataClass("INDIA"))
            return simpleData

        }

        fun SimpleStringData(): MutableList<String>
        {
            val simpleList: MutableList<String> = mutableListOf("DDUKK", "SMS", "DCA")
            return  simpleList;
        }

    }
}



