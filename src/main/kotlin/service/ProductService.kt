package service

import api.client.AccessControlClient
import jakarta.inject.Singleton
import mu.KotlinLogging
import sf.*


@Singleton
class ProductService(private val accessControlClient: AccessControlClient) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun place(request: PlaceProductRequest) =
        authenticate {
            ProductResponse.newBuilder()
                .setProductId(request.productId)
                .build()
        }


    fun move(request: MoveProductRequest) =
        authenticate {
            ProductResponse.newBuilder()
                .setProductId(request.productId)
                .build()
        }


    fun get(request: GetProductRequest) =
        authenticate {
            ProductResponse.newBuilder()
                .setProductId(request.productId)
                .build()
        }

    private fun authenticate(consumer: (UserResponse) -> ProductResponse): ProductResponse? {
        val user = accessControlClient.getUser("1")

        return if (user.name == "Zhigan Limon") {
            logger.info { "authenticated user:\n $user" }
            consumer(user)
        } else {
            null
        }
    }
}