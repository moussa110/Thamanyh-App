package com.mousa.thamnyahapp.domain.model

sealed class Content {
    abstract val id: String
    abstract val name: String
    abstract val description: String
    abstract val avatarUrl: String
    abstract val duration: Int
    abstract val score: Float
}