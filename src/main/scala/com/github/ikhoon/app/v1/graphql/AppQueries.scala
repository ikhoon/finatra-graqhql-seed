package com.github.ikhoon.app.v1.graphql

import sangria.schema._
import com.github.ikhoon.app.v1.user.UserSchema._

/**
  * Created by ikhoon on 27/02/2018.
  */
object AppQueries {

  val QueryType =
    ObjectType(
      "Query",
      description = "Query API",
      fields[AppContext, Unit](
        UserField
      )
    )
}
