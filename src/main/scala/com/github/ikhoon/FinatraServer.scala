package com.github.ikhoon

import com.github.ikhoon.app.v1.graphql.GraphqlController
import com.github.ikhoon.modules._
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

object FinatraServerMain extends FinatraServer

class FinatraServer extends HttpServer {

  override def modules =
    Seq(TypesafeConfigModule, QuillDatabaseModule, SlickDatabaseModule) ++ HttpClientModules.modules

  override def jacksonModule = CustomJacksonModule

  override def defaultFinatraHttpPort = ":9999"

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[GraphqlController]

  }

}
