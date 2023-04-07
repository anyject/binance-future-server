package com.anyject.binancefutureserver.config.utils

import com.fasterxml.jackson.annotation.JsonProperty

data class ListenKeyVM(
    @JsonProperty("listenKey")
    val listenKey: String
) {}
