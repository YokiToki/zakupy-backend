package com.github.yokitoki.app.response.roster

import com.github.yokitoki.app.dto.RosterDto

data class RosterListResponse(private val list: List<RosterDto>) : MutableList<RosterResponse> by mutableListOf() {
    init {
        this.addAll(list.map { RosterResponse(it) })
    }
}
