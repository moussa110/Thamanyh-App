package com.mousa.thamnyahapp.data.mappers

import com.mousa.thamnyahapp.domain.model.AudioArticleContent
import com.mousa.thamnyahapp.domain.model.AudioBookContent
import com.mousa.thamnyahapp.domain.model.Content
import com.mousa.thamnyahapp.domain.model.EpisodeContent
import com.mousa.thamnyahapp.domain.model.PodcastContent

class ContentMapper {
    
    fun mapToContent(contentMap: Map<String, Any>, contentType: String): Content {
        return when (contentType) {
            "podcast" -> mapToPodcast(contentMap)
            "episode" -> mapToEpisode(contentMap)
            "audio_article" -> mapToAudioArticle(contentMap)
            "audio_book" -> mapToAudioBook(contentMap)
            else -> throw IllegalArgumentException("Unknown content type: $contentType")
        }
    }
    
    private fun mapToPodcast(map: Map<String, Any>): PodcastContent {
        return PodcastContent(
            id = map["podcast_id"] as String,
            name = map["name"] as String,
            description = map["description"] as String,
            avatarUrl = map["avatar_url"] as String,
            episodeCount = (map["episode_count"] as Number).toInt(),
            duration = (map["duration"] as Number).toInt(),
            language = map["language"] as? String,
            priority = (map["priority"] as Number).toInt(),
            popularityScore = (map["popularityScore"] as Number).toInt(),
            score = (map["score"] as Number).toFloat()
        )
    }
    
    private fun mapToEpisode(map: Map<String, Any>): EpisodeContent {
        return EpisodeContent(
            id = map["episode_id"] as String,
            name = map["name"] as String,
            seasonNumber = (map["season_number"] as? Number)?.toInt(),
            episodeType = map["episode_type"] as String,
            podcastName = map["podcast_name"] as String,
            authorName = map["author_name"] as String,
            description = map["description"] as String,
            number = (map["number"] as? Number)?.toInt(),
            duration = (map["duration"] as Number).toInt(),
            avatarUrl = map["avatar_url"] as String,
            separatedAudioUrl = map["separated_audio_url"] as String,
            audioUrl = map["audio_url"] as String,
            releaseDate = map["release_date"] as String,
            podcastId = map["podcast_id"] as String,
            chapters = map["chapters"] as List<Any>,
            paidIsEarlyAccess = map["paid_is_early_access"] as Boolean,
            paidIsNowEarlyAccess = map["paid_is_now_early_access"] as Boolean,
            paidIsExclusive = map["paid_is_exclusive"] as Boolean,
            paidTranscriptUrl = map["paid_transcript_url"] as? String,
            freeTranscriptUrl = map["free_transcript_url"] as? String,
            paidIsExclusivePartially = map["paid_is_exclusive_partially"] as Boolean,
            paidExclusiveStartTime = (map["paid_exclusive_start_time"] as Number).toInt(),
            paidEarlyAccessDate = map["paid_early_access_date"] as? String,
            paidEarlyAccessAudioUrl = map["paid_early_access_audio_url"] as? String,
            paidExclusivityType = map["paid_exclusivity_type"] as? String,
            podcastPopularityScore = (map["podcastPopularityScore"] as Number).toInt(),
            podcastPriority = (map["podcastPriority"] as Number).toInt(),
            score = (map["score"] as Number).toFloat()
        )
    }
    
    private fun mapToAudioArticle(map: Map<String, Any>): AudioArticleContent {
        return AudioArticleContent(
            id = map["article_id"] as String,
            name = map["name"] as String,
            authorName = map["author_name"] as String,
            description = map["description"] as String,
            avatarUrl = map["avatar_url"] as String,
            duration = (map["duration"] as Number).toInt(),
            releaseDate = map["release_date"] as String,
            score = (map["score"] as Number).toFloat()
        )
    }

    private fun mapToAudioBook(map: Map<String, Any>): AudioBookContent {
        return AudioBookContent(
            id = map["audiobook_id"] as String,
            name = map["name"] as String,
            authorName = map["author_name"] as String,
            description = map["description"] as String,
            avatarUrl = map["avatar_url"] as String,
            duration = (map["duration"] as Number).toInt(),
            language = map["language"] as String,
            releaseDate = map["release_date"] as String,
            score = (map["score"] as Number).toFloat()
        )
    }
}
