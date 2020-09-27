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

class UnauthorizedResponseHandlerPluginFactory
    : PluginFactory<Response<List<News>, NewsErrors>, NewsResponseHandlerPlugin> {

    override val killSwitch: Xp
        get() = AppExperiments.NEWS_UNAUTHORIZED_ERROR_HANDLER

    override fun createPlugin(
        dynamicDependency: Response<List<News>, NewsErrors>
    ): NewsResponseHandlerPlugin {
        return Plugin()
    }

    override fun isApplicable(dynamicDependency: Response<List<News>, NewsErrors>): Boolean {
        return dynamicDependency.serverError?.userNotAuthorizedError != null
    }

    class Plugin : NewsResponseHandlerPlugin {
        override fun handle(binding: ActivityMainBinding) {
            with(binding) {
                with(list) {
                    visibility = View.GONE
                    (adapter as NewsAdapter).update(emptyList())
                }
                unauthenticatedError.visibility = View.VISIBLE
                unauthenticatedErrorButton.setOnClickListener {
                    throw NotImplementedError()
                }
                emptyError.visibility = View.GONE
            }
        }

    }
}