package ir.mahdi.filimoapplication.core.util

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>( val data: T? = null , val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Fail<T>(message: String? , data: T?): Resource<T>(data, message)
}