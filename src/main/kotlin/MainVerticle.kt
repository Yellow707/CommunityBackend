import com.sun.security.ntlm.Server
import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import java.nio.Buffer
import java.util.*

fun main(args: Array<String>) {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(MainVerticle())
}

class MainVerticle : AbstractVerticle() {

    override fun start() {
        super.start()

        val server = vertx.createHttpServer()
        server.webSocketHandler { handler ->
            handler.writeTextMessage("Connected")

            handler.handler{ msg ->
                handler.writeTextMessage("Response from server")

            }
        }.listen(8080)
    }
}