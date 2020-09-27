package app.sandbox.xp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import app.sandbox.xp.AppConfig
import app.sandbox.xp.AppExperiments
import app.sandbox.xp.Experiments
import app.sandbox.xp.R
import app.sandbox.xp.api.ApiClient.client
import app.sandbox.xp.api.Response
import app.sandbox.xp.api.mapToResponse
import app.sandbox.xp.databinding.ActivityMainBinding
import autodispose2.androidx.lifecycle.scope
import autodispose2.autoDispose
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val retryStream = RetryNewsRequestStream()
    private val pluginPointParentComponent = object : NewsResponseHandlerPluginPoint.ParentComponent {
        override fun retryNewsRequestStream() = retryStream

    }
    private val pluginPoint = NewsResponseHandlerPluginPoint(pluginPointParentComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            list.adapter = NewsAdapter()
            setContentView(root)
        }

        bindRetryStream()
        requestNews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (Experiments.isTreated(AppExperiments.TEST_USER)) {
            menuInflater.inflate(R.menu.admin_menu, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Experiments.isTreated(AppExperiments.TEST_USER)) {
            AppConfig.requestVariant = when (item.itemId) {
                R.id.empty_variation -> "empty"
                R.id.unauthorized_variation -> "unauthorized"
                else -> "success"
            }
            requestNews()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun bindRetryStream() {
        retryStream
            .retries()
            .autoDispose(scope())
            .subscribe {
                requestNews()
            }
    }

    private fun requestNews() {
        client.news(AppConfig.requestVariant)
            .map { it.mapToResponse() }
            .onErrorReturn {
                if (it is IOException) {
                    Response(null, it, null, null)
                } else {
                    Response(null, null, it, null)
                }
            }
            .observeOn(mainThread())
            .autoDispose(scope())
            .subscribe {
                pluginPoint.getPlugin(it).handle(binding)
            }
    }
}
