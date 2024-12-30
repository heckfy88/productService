package api.client

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import sf.AccessControl
import sf.GetUserRequest
import sf.UserResponse
import sf.UserServiceGrpc


@Singleton
class AccessControlClient(
    @Value("\${grpc.channels.accesscontrol.host:accesscontrol}") private val host: String,
    @Value("\${grpc.channels.accesscontrol.port:9090}") private val port: Int,
) {

    private var userClient: UserServiceGrpc.UserServiceBlockingStub

    init {
        // Create the gRPC channel
        val channel: ManagedChannel = ManagedChannelBuilder
            .forTarget("http://accesscontrol:50052")
            .build()

        // Initialize the blocking stub
        userClient = UserServiceGrpc.newBlockingStub(channel)
    }

    fun getUser(userId: String): UserResponse {
        // Build the request
        val request = GetUserRequest.newBuilder()
            .setUserId(userId)
            .build()

        // Call the gRPC server and get the response
        val response = userClient.getUser(request)
        return response
    }

}