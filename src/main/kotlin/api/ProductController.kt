package api

import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService
import service.ProductService
import sf.*

@GrpcService
class ProductController(private val productService: ProductService): ProductServiceGrpc.ProductServiceImplBase() {

    override fun place(request: PlaceProductRequest?, responseObserver: StreamObserver<ProductResponse>?) {
        val response = productService.place(request!!)

        responseObserver!!.onNext(response)
        responseObserver.onCompleted()
    }

    override fun move(request: MoveProductRequest?, responseObserver: StreamObserver<ProductResponse>?) {
        val response = productService.move(request!!)

        responseObserver!!.onNext(response)
        responseObserver.onCompleted()
    }

    override fun get(request: GetProductRequest?, responseObserver: StreamObserver<ProductResponse>?) {
        val response = productService.get(request!!)

        responseObserver!!.onNext(response)
        responseObserver.onCompleted()
    }
}