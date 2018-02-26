package com.github.ikhoon.app.v1.graphql

import javax.inject.Inject

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finatra.http.Controller
import sangria.execution.Executor
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
class GraphqlController @Inject()(graphqlExecutor: GraphqlExecutor)extends Controller {

  get("graphql") { graphql: GraphqlJson =>
     QueryParser.parse(graphql.query) match {
       case Success(queryAst) =>
         graphqlExecutor.execute(queryAst, graphql.variables, graphql.operationName)
       case Failure(ex) => // TODO
     }
  }

}
