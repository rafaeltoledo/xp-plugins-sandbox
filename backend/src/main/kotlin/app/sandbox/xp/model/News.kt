package app.sandbox.xp.model

data class News(
    val title: String,
    val date: String
)

val DEFAULT_NEWS_COLLECTION = listOf(
    News("Uber lança plano de assinatura", "2020-08-11"),
    News("Uber inclui categoria de táxi no aplicativo para ampliar opções da plataforma", "2020-07-30"),
    News("Destino final e informação sobre novos usuários estão disponíveis para motoristas parceiros de todo o Brasil", "2020-07-23"),
    News("Uber lança podcast para motoristas sobre racismo e LGBTQIA+fobia", "2020-07-22"),
    News("Sendo uma empresa antirracista", "2020-07-21"),
)