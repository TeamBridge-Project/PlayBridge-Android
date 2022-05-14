package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.service.UserService
import com.example.domain.model.UserModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val apiService: UserService,
    private val query: Int
) : PagingSource<Int, UserModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getUserList(query)
            val responseBody = checkNotNull(response.body())
            val prevKey = if (position == 1) null else position - 1
            val nextKey = if (responseBody.result.isEmpty()) null else position + 1

            LoadResult.Page(
                responseBody.result,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? =
        state.anchorPosition
}
