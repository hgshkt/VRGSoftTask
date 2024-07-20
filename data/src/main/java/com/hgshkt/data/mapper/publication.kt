package com.hgshkt.data.mapper

import com.hgshkt.data.api.publication.model.PublicationPageJson
import com.hgshkt.domain.model.Publication

fun PublicationPageJson.Data.Children.ChildrenData.toDomain(): Publication {
    return Publication(
        id = name!!
    )
}