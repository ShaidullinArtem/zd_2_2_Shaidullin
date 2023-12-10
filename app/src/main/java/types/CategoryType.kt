package types

data class CategoryType (
    val id: Int,
    val image: String,
    val title: String,
    val posts: ArrayList<PostType>
)