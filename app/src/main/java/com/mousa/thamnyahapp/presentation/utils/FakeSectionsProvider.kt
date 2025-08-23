package com.mousa.thamnyahapp.presentation.utils

import com.mousa.thamnyahapp.domain.model.AudioArticleContent
import com.mousa.thamnyahapp.domain.model.AudioBookContent
import com.mousa.thamnyahapp.domain.model.ContentType
import com.mousa.thamnyahapp.domain.model.EpisodeContent
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.PodcastContent
import com.mousa.thamnyahapp.domain.model.SectionType

object FakeSectionsProvider {
    val sections = listOf(

        // 🔹 Square Section (Podcasts)
        HomeSection(
            name = "Top Podcasts",
            type = SectionType.SQUARE,
            contentType = ContentType.PODCAST,
            order = 1,
            content = (1..5).map { i ->
                PodcastContent(
                    id = "podcast_$i",
                    name = "Podcast $i",
                    description = "Description for Podcast $i",
                    avatarUrl = "https://picsum.photos/200/200?random=$i",
                    episodeCount = 50 + i,
                    duration = 600 * i,
                    language = "EN",
                    priority = i,
                    popularityScore = 100 + i,
                    score = 200f + i
                )
            }
        ),

        // 🔹 Big Square Section (Audio Books)
        HomeSection(
            name = "Featured Audio Books",
            type = SectionType.BIG_SQUARE,
            contentType = ContentType.AUDIO_BOOK,
            order = 2,
            content = (1..4).map { i ->
                AudioBookContent(
                    id = "audiobook_$i",
                    name = "Audio Book $i",
                    authorName = "Author $i",
                    description = "Description for Audio Book $i",
                    avatarUrl = "https://picsum.photos/400/400?random=${i + 10}",
                    duration = 3600 * i,
                    language = "EN",
                    releaseDate = "2024-05-${10 + i}",
                    score = 250f + i
                )
            }
        ),

        // 🔹 Queue Section (Episodes)
        HomeSection(
            name = "Your Queue",
            type = SectionType.QUEUE,
            contentType = ContentType.EPISODE,
            order = 3,
            content = (1..6).map { i ->
                EpisodeContent(
                    id = "episode_$i",
                    name = "Episode $i",
                    seasonNumber = 1,
                    episodeType = "FULL",
                    podcastName = "Podcast $i",
                    authorName = "Host $i",
                    description = "Description for Episode $i",
                    number = i,
                    duration = 1200 * i,
                    avatarUrl = "https://picsum.photos/150/150?random=${i + 20}",
                    separatedAudioUrl = "https://example.com/sep_audio_$i.mp3",
                    audioUrl = "https://example.com/audio_$i.mp3",
                    releaseDate = "2024-04-${10 + i}",
                    podcastId = "podcast_$i",
                    chapters = emptyList(),
                    paidIsEarlyAccess = false,
                    paidIsNowEarlyAccess = false,
                    paidIsExclusive = false,
                    paidTranscriptUrl = null,
                    freeTranscriptUrl = null,
                    paidIsExclusivePartially = false,
                    paidExclusiveStartTime = 0,
                    paidEarlyAccessDate = null,
                    paidEarlyAccessAudioUrl = null,
                    paidExclusivityType = null,
                    podcastPopularityScore = 100 + i,
                    podcastPriority = i,
                    score = 180f + i
                )
            }
        ),

        // 🔹 Two Lines Grid Section (Audio Articles)
        HomeSection(
            name = "Trending Articles",
            type = SectionType.TWO_LINES_GRID,
            contentType = ContentType.AUDIO_ARTICLE,
            order = 4,
            content = (1..8).map { i ->
                AudioArticleContent(
                    id = "article_$i",
                    name = "Article $i",
                    authorName = "Author $i",
                    description = "Description for Article $i",
                    avatarUrl = "https://picsum.photos/300/200?random=${i + 30}",
                    duration = 900 * i,
                    releaseDate = "2024-03-${10 + i}",
                    score = 210f + i
                )
            }
        )
    )
}
