package com.mousa.thamnyahapp.data.mappers

import com.mousa.thamnyahapp.data.remote.response.SectionResponse
import com.mousa.thamnyahapp.data.remote.response.SectionsResponse
import com.mousa.thamnyahapp.domain.model.Content
import com.mousa.thamnyahapp.domain.model.ContentType
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.SectionType
import javax.inject.Inject

class HomeMapper @Inject constructor(
    private val contentMapper: ContentMapper
) {

    fun mapToHomeSections(response: SectionsResponse): List<HomeSection> {
        return response.sectionResponses.map { sectionResponse ->
            mapToHomeSection(sectionResponse)
        }
    }

    fun mapToHomeSection(sectionResponse: SectionResponse): HomeSection {
        return HomeSection(
            name = sectionResponse.name,
            type = mapToSectionType(sectionResponse.type),
            contentType = mapToContentType(sectionResponse.contentType),
            order = sectionResponse.order,
            content = mapToContentList(sectionResponse.content, sectionResponse.contentType)
        )
    }

    private fun mapToContentList(
        contentList: List<Map<String, Any>>,
        contentType: String
    ): List<Content> {
        return contentList.map { contentMap ->
            contentMapper.mapToContent(contentMap, contentType)
        }
    }

    private fun mapToSectionType(type: String): SectionType {
        return when (type) {
            "square" -> SectionType.SQUARE
            "big_square","big square" -> SectionType.BIG_SQUARE
            "2_lines_grid" -> SectionType.TWO_LINES_GRID
            "queue" -> SectionType.QUEUE
            else -> SectionType.UNKNOWN
        }
    }

    private fun mapToContentType(contentType: String): ContentType {
        return when (contentType) {
            "podcast" -> ContentType.PODCAST
            "episode" -> ContentType.EPISODE
            "audio_article" -> ContentType.AUDIO_ARTICLE
            "audio_book" -> ContentType.AUDIO_BOOK
            else -> ContentType.UNKNOWN
        }
    }
}