package com.example.newsapi.api.ui.categories.recyclerView

import com.example.newsapi.R

data class Category(
    val id:String,
    val name:String,
    val imageId:Int,
    val backgroundColorId:Int
){
    companion object{
        fun getCategoryList():List<Category>{

            return listOf(
                Category(
                    id = "sports",
                    name = "Sports",
                    imageId = R.drawable.sports,
                    backgroundColorId =R.color.red
                ),
                Category(
                    id = "entertainment",
                    name = "Entertainment",
                    imageId = R.drawable.politics,
                    backgroundColorId = R.color.blow
                ),
                Category(
                    id = "health",
                    name = "Health",
                    imageId = R.drawable.health,
                    backgroundColorId = R.color.bink
                ),
                Category(
                    id="business",
                    name="business",
                    imageId = R.drawable.bussines,
                    backgroundColorId = R.color.baby_blow
                ),
                Category(
                    id = "technology",
                    name = "Technology",
                    imageId = R.drawable.environment,
                    backgroundColorId = R.color.brown
                ),
                Category(
                    id = "science",
                    name = "Science",
                    imageId = R.drawable.science,
                    backgroundColorId = R.color.yellwo
                )
            )
        }
    }
}

