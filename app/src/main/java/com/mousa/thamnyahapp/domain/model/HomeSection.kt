package com.mousa.thamnyahapp.domain.model

data class HomeSection(
    val name: String,
    val type: SectionType,
    val contentType: ContentType,
    val order: Int,
    val content: List<Content>
)