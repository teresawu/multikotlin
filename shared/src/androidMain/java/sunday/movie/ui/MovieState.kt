package sunday.movie.ui

import sunday.movie.domain.model.Item

data class MovieState(
    var success: Boolean = false,
    var loading: Boolean = true,
    var itemList: List<Item> = emptyList()
)