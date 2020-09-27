package app.sandbox.xp.ui.news

import android.view.View
import app.sandbox.xp.AppExperiments
import app.sandbox.xp.Xp
import app.sandbox.xp.api.News
import app.sandbox.xp.api.NewsErrors
import app.sandbox.xp.api.Response
import app.sandbox.xp.databinding.ActivityMainBinding
import app.sandbox.xp.plugin.PluginFactory
import app.sandbox.xp.ui.NewsAdapter
import app.sandbox.xp.ui.NewsResponseHandlerPlugin

class EmptyResultsResponseHandlerPluginFactory : PluginFactory<Response<List<News>, NewsErrors>, NewsResponseHandlerPlugin> {
    override val killSwitch: Xp
        get() = AppExperiments.NEWS_EMPTY_HANDLER

    override fun createPlugin(dynamicDependency: Response<List<News>, NewsErrors>): NewsResponseHandlerPlugin {
        return Plugin()
    }

    override fun isApplicable(dynamicDependency: Response<List<News>, NewsErrors>): Boolean {
        return dynamicDependency.data?.isEmpty() ?: false
    }

    class Plugin : NewsResponseHandlerPlugin {
        override fun handle(binding: ActivityMainBinding) {
            binding.unauthenticatedError.visibility = View.GONE

            with(binding.list) {
                visibility = View.GONE
                (adapter as NewsAdapter).update(emptyList())
            }

            binding.emptyError.visibility = View.VISIBLE
        }

    }
}