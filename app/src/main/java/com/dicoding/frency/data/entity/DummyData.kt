package com.dicoding.frency.data.entity

object DummyData {
    val dataDummy = mutableListOf<Franchise>()

    init {
        for (i in 1..22) {
            val dummyData = Franchise(
                "item $i",
                "Item $i address",
                "Item descriptionn $i",
                "Category $i",
                "'$i'+9090909+'$i'",
                arrayOf("Type1", "Type2"), // Replace with actual types
                arrayOf("https://picsum.photos/720/1", "https://picsum.photos/720/2") // Replace with actual URLs
            )
            dataDummy.add(dummyData)
        }
    }
}
