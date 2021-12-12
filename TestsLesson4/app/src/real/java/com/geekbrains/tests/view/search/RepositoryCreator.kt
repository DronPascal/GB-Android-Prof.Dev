package com.geekbrains.tests.view.search

import com.geekbrains.tests.presenter.RepositoryContract
import com.geekbrains.tests.repository.GitHubApi
import com.geekbrains.tests.repository.GitHubRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dronpascal on 12.12.2021.
 */
class RepositoryCreator {

    companion object Factory {

        fun createRepository(): RepositoryContract {
            return GitHubRepository(createRetrofit().create(GitHubApi::class.java))
        }

        private fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}