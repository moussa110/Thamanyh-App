package com.mousa.thamnyahapp.data.mappers

import com.mousa.thamnyahapp.data.remote.response.search.SearchContentResponse
import com.mousa.thamnyahapp.data.remote.response.search.SearchSectionResponse
import com.mousa.thamnyahapp.domain.model.ContentType
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.PodcastContent
import com.mousa.thamnyahapp.domain.model.SectionType

fun SearchSectionResponse.toDomain(): HomeSection {
    return HomeSection(
        name = name,
        type = SectionType.QUEUE,
        contentType = ContentType.PODCAST,
        order = order.toIntOrNull() ?: 0,
        content = content.map { it.toDomain() }
    )
}

fun SearchContentResponse.toDomain(): PodcastContent {
    return PodcastContent(
        id = podcastId,
        name = name,
        description = description,
        avatarUrl = avatarUrl,
        episodeCount = episodeCount.toIntOrNull() ?: 0,
        duration = duration.toIntOrNull() ?: 0,
        language = language,
        priority = 0,
        popularityScore = 0,
        score = 0f
    )
}