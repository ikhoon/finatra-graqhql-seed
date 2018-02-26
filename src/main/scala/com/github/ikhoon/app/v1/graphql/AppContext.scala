package com.github.ikhoon.app.v1.graphql

import javax.inject.Inject

import com.github.ikhoon.app.v1.fake.FakeService
import com.github.ikhoon.app.v1.user.UserService

/**
  * Created by ikhoon on 27/02/2018.
  */
class AppContext @Inject()(
  val fakeService: FakeService,
  val userService: UserService
)
