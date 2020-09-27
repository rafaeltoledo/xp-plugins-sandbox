package app.sandbox.xp.api

data class Response<T, E>(
    val serverError: E?,
    val networkError: Throwable?,
    val unexpectedError: Throwable?,
    val data: T?
)