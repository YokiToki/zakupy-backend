package com.github.yokitoki.app.response.roster

import com.github.yokitoki.app.dto.RosterItemDto

class RosterItemResponse(rosterItemDto: RosterItemDto) {
    val uuid: String = rosterItemDto.uuid
    val name: String = rosterItemDto.name
    val checked: Boolean = rosterItemDto.checked
}
