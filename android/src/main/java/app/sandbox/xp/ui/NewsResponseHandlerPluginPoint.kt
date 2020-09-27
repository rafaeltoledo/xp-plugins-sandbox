package app.sandbox.xp.ui

import app.sandbox.xp.api.News
import app.sandbox.xp.api.NewsErrors
import app.sandbox.xp.api.Response
import app.sandbox.xp.databinding.ActivityMainBinding
import app.sandbox.xp.plugin.PluginFactory
import app.sandbox.xp.plugin.PluginPoint
import app.sandbox.xp.ui.news.EmptyResultsResponseHandlerPluginFactory
import app.sandbox.xp.ui.news.GenericResponseHandlerPluginFactory
import app.sandbox.xp.ui.news.SuccessfulResponseHandlerPluginFactory

interface NewsResponseHandlerPlugin {
    fun handle(binding: ActivityMainBinding)
}

class NewsResponseHandlerPluginPoint(
    private val parentComponent: ParentComponent
) : PluginPoint<Response<List<News>, NewsErrors>, NewsResponseHandlerPlugin>() {
    override fun getInternalFactories():
        List<PluginFactory<Response<List<News>, NewsErrors>, NewsResponseHandlerPlugin>> {
        return listOf(
            SuccessfulResponseHandlerPluginFactory(),
            EmptyResultsResponseHandlerPluginFactory(),
            GenericResponseHandlerPluginFactory(parentComponent)
        )
    }

    interface ParentComponent : GenericResponseHandlerPluginFactory.ParentComponent
}