package com.github.yokitoki.app.response.roster

import com.github.yokitoki.app.dto.RosterItemDto

data class RosterItemListResponse(private val list: List<RosterItemDto>) :
    MutableList<RosterItemResponse> by mutableListOf() {
    init {
        this.addAll(list.map { RosterItemResponse(it) })
    }
}
