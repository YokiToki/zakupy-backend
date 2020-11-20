package com.github.yokitoki.app.mock

import com.github.yokitoki.app.dto.RosterDto
import com.github.yokitoki.app.dto.RosterItemDto
import java.util.UUID

object RosterMock {
    private val uuids = (0..2).map { UUID.randomUUID().toString() }
    private val itemUuids = (0..7).map { UUID.randomUUID().toString() }

    private val rosterDtoList: List<RosterDto> = listOf(
        RosterDto(
            uuid = uuids[0],
            name = "Some list",
            visibility = "PERSONAL",
            type = "BASIC",
        ),
        RosterDto(
            uuid = uuids[1],
            name = "Another one",
            visibility = "PERSONAL",
            type = "BASIC",
        ),
        RosterDto(
            uuid = uuids[2],
            name = "Another two",
            visibility = "SHARED",
            type = "BASIC",
        ),
    )

    val rosterItemMap = mapOf(
        uuids[0] to listOf(
            RosterItemDto(itemUuids[0], "Item", true),
            RosterItemDto(itemUuids[1], "Item", false),
            RosterItemDto(itemUuids[2], "Item", true)
        ),
        uuids[1] to listOf(
            RosterItemDto(itemUuids[3], "Item", true),
            RosterItemDto(itemUuids[4], "Item", false),
        ),
        uuids[2] to listOf(
            RosterItemDto(itemUuids[5], "Item", true),
            RosterItemDto(itemUuids[6], "Item", false),
            RosterItemDto(itemUuids[7], "Item", true)
        )
    )

    fun getFirstUUid(): String {
        return rosterDtoList[0].uuid
    }

    fun getRosterDto(uuid: String): RosterDto? {
        return rosterDtoList.firstOrNull { it.uuid == uuid }
    }

    fun getRosterDtoList(): List<RosterDto> {
        return rosterDtoList
    }

    fun getRosterItemDtoList(uuid: String): List<RosterItemDto> {
        return rosterItemMap[uuid] ?: listOf()
    }
}
