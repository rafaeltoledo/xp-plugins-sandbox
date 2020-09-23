package app.sandbox.xp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sandbox.xp.api.ApiClient.client
import app.sandbox.xp.databinding.ActivityMainBinding
import autodispose2.androidx.lifecycle.scope
import autodispose2.autoDispose
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        client.news()
            .observeOn(mainThread())
            .autoDispose(scope())
            .subscribe {
                binding.list.adapter = NewsAdapter(it)
            }
    }
}
