package com.hgshkt.vrgsofttask.presentation.data

/**
 * Extension property of time in seconds.
 * "X hours ago" format
 */
val Int.hoursAgo: String
    get() {
        val now = System.currentTimeMillis() / 1000
        val seconds = now - this

        val minutes = seconds / 60
        val hours = minutes / 60
        return "$hours hours ago"
    }

/**
 * Extension property of time in seconds.
 * Using the largest possible unit of time.
 */
fun Int.toFormat(): String {
    val now = System.currentTimeMillis() / 1000
    val seconds = now - this

    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val years = days / 356

    return when {
        years > 0 -> "$years day${if (years > 1) "s" else ""} ago"
        weeks > 0 -> "$weeks day${if (weeks > 1) "s" else ""} ago"
        days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
        hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ago"
        minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        seconds > 0 -> "$seconds second${if (seconds > 1) "s" else ""} ago"
        else -> "Just now"
    }
}