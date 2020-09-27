package app.sandbox.xp.model

data class NewsEnvelope(
  val errors: NewsErrors?,
  val data: List<News>?
)

val SUCCESSFUL_RESPONSE = NewsEnvelope(
  null,
  DEFAULT_NEWS_COLLECTION
)

val SUCCESSFUL_EMPTY_RESPONSE = NewsEnvelope(
    null,
    emptyList()
)

val USER_UNAUTHORIZED_RESPONSE = NewsEnvelope(
  NewsErrors(userNotAuthorizedError = "Usuário não autorizado"),
  null
)