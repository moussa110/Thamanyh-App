package com.mousa.thamnyahapp.presentation.composables.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.SectionType
import com.mousa.thamnyahapp.presentation.composables.SectionTitle

@Composable
fun SectionScreen(section: HomeSection) {
    Column {
        SectionTitle(title = section.name)

        when (section.type) {
            SectionType.SQUARE -> SquareSection(section)
            SectionType.BIG_SQUARE -> BigSquareSection(section)
            SectionType.QUEUE -> QueueSection(section)
            SectionType.TWO_LINES_GRID -> TwoLinesGridSection(section)
            else -> {QueueSection(section)}
        }
    }
}