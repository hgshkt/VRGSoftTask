package com.hgshkt.data.mapper

import com.hgshkt.data.api.publication.model.PublicationPageJson
import com.hgshkt.domain.model.Publication

fun PublicationPageJson.Data.Children.toDomain(): Publication {
    with(childrenData!!) {
        return Publication(
            id = name!!,
            author = author!!,
            date = createdUtc,
            imageUrl = thumbnail!!,
            commentariesCount = numComments
        )
    }
}