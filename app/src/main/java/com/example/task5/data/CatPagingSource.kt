package com.example.task5.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.task5.api.CatApi
import java.io.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1
private const val CATS_ORDER = "ASC"

class CatPagingSource(private val catApi: CatApi):PagingSource<Int,CatPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatPhoto> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val photo = catApi.catPhotos(CATS_ORDER, position, params.loadSize)
            Log.d("MyLog", photo.toString())
            LoadResult.Page(
                data = photo,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photo.isEmpty()) null else position + 1
            )
        } catch (exception: IOException){
            Log.d("MyLog", exception.message.toString())
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            Log.d("MyLog", exception.message.toString())
            LoadResult.Error(exception)
        } catch (exception: Throwable) {
            Log.d("MyLog", exception.message.toString())
            LoadResult.Error(exception)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatPhoto>): Int? {
        TODO("Not yet implemented")
    }
}