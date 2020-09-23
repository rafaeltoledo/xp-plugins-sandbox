package app.sandbox.xp.model

data class News(
    val title: String,
    val date: String
)

val DEFAULT_NEWS_COLLECTION = listOf(
    News("Uber lança novo portal que pode ajudar autoridades a rastrear casos de COVID-19", "2020-09-09"),
    News("Uber lança novos recursos de segurança para motoristas, entregadores e usuários durante evento", "2020-09-03"),
    News("Fatos e Dados sobre a Uber", "2020-08-27"),
    News("Uber lança plano de assinatura", "2020-08-11"),
    News("Uber inclui categoria de táxi no aplicativo para ampliar opções da plataforma", "2020-07-30"),
    News("Destino final e informação sobre novos usuários estão disponíveis para motoristas parceiros de todo o Brasil", "2020-07-23"),
    News("Uber lança podcast para motoristas sobre racismo e LGBTQIA+fobia", "2020-07-22"),
    News("Sendo uma empresa antirracista", "2020-07-21"),
    News("Uber traz de volta a Ciclofaixa de Lazer de São Paulo", "2020-07-17"),
    News("Uber lança integração com supermercados no app em parceria com a Cornershop", "2020-07-07"),
    News("Uber lança Centro de Higienização para parceiros em São Paulo", "2020-05-28"),
    News("COVID-19: Uber anuncia viagens gratuitas para apoiar doação de sangue", "2020-05-26"),
)