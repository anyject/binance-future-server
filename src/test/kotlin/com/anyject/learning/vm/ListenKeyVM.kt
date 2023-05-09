package com.anyject.learning.vm

import com.fasterxml.jackson.annotation.JsonProperty

data class ListenKeyVM(
    @JsonProperty("listenKey")
    val listenKey: String
)
