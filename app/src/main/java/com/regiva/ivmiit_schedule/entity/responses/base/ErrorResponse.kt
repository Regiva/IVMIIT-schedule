package com.regiva.ivmiit_schedule.entity.responses.base

data class ErrorResponse(
    val error: VkError
)

data class VkError(
    val error_code: Int,
    val error_mgs: String
)