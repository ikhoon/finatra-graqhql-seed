package com.github.ikhoon.app.v1.graphql

import javax.inject.Inject

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.util.Future
import sangria.execution.{ExecutionResult, Executor}
import sangria.parser.QueryParser

import scala.util.{Failure, Success}

/**
  * Created by ikhoon on 27/02/2018.
  */
case class GraphqlJson(
  query: String,
  variables: Option[JsonNode],
  operationName: Option[String]
)
class GraphqlController @Inject()(graphqlExecutor: GraphqlExecutor) extends Controller {

  post("/graphql") { graphql: GraphqlJson =>
    query(graphql)
  }

  get("/graphql") { graphql: GraphqlJson =>
    query(graphql)
  }

  private def query(graphql: GraphqlJson): Future[ResponseBuilder#EnrichedResponse] = {
    QueryParser.parse(graphql.query) match {
      case Success(queryAst) =>
        val result = graphqlExecutor.execute(queryAst, graphql.variables, graphql.operationName)
        handleErrors(result)
    }
  }

  private def handleErrors(
    result: Future[ExecutionResult[AppContext, JsonNode]]
  ): Future[ResponseBuilder#EnrichedResponse] =
    result.map { er =>
      if (er.errors.isEmpty) {
        response.ok(er.result)
      } else {
        response.internalServerError(er.errors)
      }
    }

  get("/explore") { request: Request =>
    response.ok.file("/graphqli.html")
  }

}
