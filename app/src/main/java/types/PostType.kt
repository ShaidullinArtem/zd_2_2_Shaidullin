package types

data class PostType (
    val id: String,
    val author: UserType,
    val image: String,
    val title: String,
    val description: String
)