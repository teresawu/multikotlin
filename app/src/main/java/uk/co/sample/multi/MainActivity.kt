package uk.co.sample.multi

import android.app.Activity
import android.os.Bundle
import co.uk.share.ServiceApi
import co.uk.share.providePlatform
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : Activity(), CoroutineScope {

    private val engine: HttpClientEngine = OkHttpEngine(OkHttpConfig())
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job
    private val serviceApi = ServiceApi(engine)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) { serviceApi.fetchData() }
                textView.text = providePlatform() + "\n" + "\n" + result
            } catch (e: Exception) {
            }
        }
    }
}