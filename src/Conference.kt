package com.caldoconf.service

import java.util.*

data class Conference(
        val identifier: String,
        val startAt: Date,
        val spot: Location,
        val motto: String?
)