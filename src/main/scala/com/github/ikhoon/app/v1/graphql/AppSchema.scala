package com.github.ikhoon.app.v1.graphql

import com.github.ikhoon.app.v1.graphql.AppQueries.QueryType
import sangria.schema.Schema

/**
  * Created by ikhoon on 27/02/2018.
  */
object AppSchema {
  val schema: Schema[AppContext, Unit] =
    Schema(QueryType)
}
