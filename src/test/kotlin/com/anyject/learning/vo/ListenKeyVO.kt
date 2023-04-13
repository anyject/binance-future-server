package com.anyject.learning.vo

import com.fasterxml.jackson.annotation.JsonProperty

data class ListenKeyVO(
    @JsonProperty("listenKey")
    val listenKey: String
)
