package com.github.yokitoki.app.response.roster

import com.github.yokitoki.app.dto.RosterDto
import com.github.yokitoki.app.dto.RosterItemDto

class RosterDetailResponse(rosterDto: RosterDto, items: List<RosterItemDto>) {
    val uuid: String = rosterDto.uuid
    val name: String = rosterDto.name
    val visibility: String = rosterDto.visibility
    val type: String = rosterDto.type
    val items: RosterItemListResponse = RosterItemListResponse(items)
}
