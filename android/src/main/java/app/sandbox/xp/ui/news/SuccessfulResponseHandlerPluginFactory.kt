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

class SuccessfulResponseHandlerPluginFactory : PluginFactory<Response<List<News>, NewsErrors>, NewsResponseHandlerPlugin> {
    override val killSwitch: Xp
        get() = AppExperiments.NEWS_SUCCESSFUL_HANDLER

    override fun createPlugin(dynamicDependency: Response<List<News>, NewsErrors>): NewsResponseHandlerPlugin {
        return Plugin(dynamicDependency.data!!)
    }

    override fun isApplicable(dynamicDependency: Response<List<News>, NewsErrors>): Boolean {
        return dynamicDependency.data?.isNotEmpty() ?: false
    }

    private class Plugin(private val items: List<News>) : NewsResponseHandlerPlugin {
        override fun handle(binding: ActivityMainBinding) {
            binding.emptyError.visibility = View.GONE
            binding.unauthenticatedError.visibility = View.GONE

            with(binding.list) {
                visibility = View.VISIBLE
                (adapter as NewsAdapter).update(items)
            }
        }

    }
}