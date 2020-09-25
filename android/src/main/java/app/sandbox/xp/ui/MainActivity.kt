package app.sandbox.xp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.sandbox.xp.api.ApiClient.client
import app.sandbox.xp.databinding.ActivityMainBinding
import autodispose2.androidx.lifecycle.scope
import autodispose2.autoDispose
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestNews()
    }

    private fun requestNews() {
        client.news()
            .observeOn(mainThread())
            .autoDispose(scope())
            .subscribe({
                binding.list.adapter = NewsAdapter(it)
            }, {
                Log.e("ERROR!", "Failed to display news", it)
                Snackbar.make(binding.root, "Falha ao obter notícias", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry") { requestNews() }.show()
            })
    }
}
