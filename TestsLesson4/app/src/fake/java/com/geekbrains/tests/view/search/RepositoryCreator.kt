package com.geekbrains.tests.view.search

import com.geekbrains.tests.presenter.RepositoryContract
import com.geekbrains.tests.repository.FakeGitHubRepository

/**
 * Created by dronpascal on 12.12.2021.
 */
class RepositoryCreator {

    companion object Factory {

        fun createRepository(): RepositoryContract {
            return FakeGitHubRepository()

        }
    }
}