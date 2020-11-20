package com.github.yokitoki.app.controller

import com.github.yokitoki.app.getPath
import com.github.yokitoki.app.httpException
import com.github.yokitoki.app.mock.RosterMock
import com.github.yokitoki.app.response.Response
import com.github.yokitoki.app.response.roster.RosterDetailResponse
import com.github.yokitoki.app.response.roster.RosterListResponse
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode

class RosterController {

    fun getAll(): Response {
        if (false) httpException(HttpStatusCode.InternalServerError)

        return Response(data = RosterListResponse(list = RosterMock.getRosterDtoList()))
    }

    suspend fun get(call: ApplicationCall): Response {
        val uuid = call.getPath<String>("uuid")

        val rosterDto = RosterMock.getRosterDto(uuid) ?: httpException(HttpStatusCode.NotFound)
        if (false) httpException(HttpStatusCode.InternalServerError)

        return Response(
            data = RosterDetailResponse(
                rosterDto = rosterDto,
                items = RosterMock.getRosterItemDtoList(uuid)
            )
        )
    }

}
