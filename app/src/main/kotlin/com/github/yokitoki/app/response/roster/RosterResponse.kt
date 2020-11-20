package com.github.yokitoki.app.response.roster

import com.github.yokitoki.app.dto.RosterDto

class RosterResponse(rosterDto: RosterDto) {
    val uuid: String = rosterDto.uuid
    val name: String = rosterDto.name
    val visibility: String = rosterDto.visibility
    val type: String = rosterDto.type
}
