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
import app.sandbox.xp.ui.RetryNewsRequestStream
import com.google.android.material.snackbar.Snackbar

class GenericResponseHandlerPluginFactory(
    private val parentComponent: ParentComponent
) : PluginFactory<Response<List<News>, NewsErrors>, NewsResponseHandlerPlugin> {
    override val killSwitch: Xp
        get() = AppExperiments.NEWS_GENERIC_ERROR_HANDLER

    override fun createPlugin(dynamicDependency: Response<List<News>, NewsErrors>): NewsResponseHandlerPlugin {
        return Plugin(parentComponent)
    }

    override fun isApplicable(dynamicDependency: Response<List<News>, NewsErrors>): Boolean {
        return true
    }

    interface ParentComponent {
        fun retryNewsRequestStream(): RetryNewsRequestStream
    }

    class Plugin(private val parentComponent: ParentComponent) : NewsResponseHandlerPlugin {
        override fun handle(binding: ActivityMainBinding) {
            with(binding.list) {
                visibility = View.GONE
                (adapter as NewsAdapter).update(emptyList())
            }
            binding.unauthenticatedError.visibility = View.GONE
            binding.emptyError.visibility = View.GONE

            Snackbar.make(binding.root, "Falha ao obter notícias", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry")
                { parentComponent.retryNewsRequestStream().retry() }.show()
        }

    }
}