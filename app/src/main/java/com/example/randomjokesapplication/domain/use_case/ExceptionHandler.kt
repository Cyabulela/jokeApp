package com.example.randomjokesapplication.domain.use_case // ktlint-disable package-name

import com.example.randomjokesapplication.util.Resources
import java.io.IOException
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.HttpException

suspend inline fun <T> FlowCollector<Resources<T>>.withHandledException(action: () -> Unit) {
    try {
        action()
    } catch (e: HttpException) {
        e.printStackTrace()
        emit(Resources.Error(e.localizedMessage ?: "Unexpected reposnse"))
    } catch (e: IOException) {
        e.printStackTrace()
        emit(Resources.Error("Please check your internet connection."))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(Resources.Error(e.localizedMessage ?: "Unknown error occurred"))
    }
}
