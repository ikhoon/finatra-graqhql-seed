package com.github.ikhoon.app.v1.graphql

import javax.inject.Inject

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.{NullNode, ObjectNode}
import com.twitter.util.Future
import sangria.ast.Document
import sangria.execution.{ExecutionResult, Executor}
import com.github.ikhoon.TwitterConverters._
import sangria.marshalling.jackson._
import sangria.execution.ExecutionScheme.Extended

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by ikhoon on 27/02/2018.
  */
class GraphqlExecutor @Inject()(appContext: AppContext) {

  def execute(queryAst: Document, variables: Option[JsonNode], operationName: Option[String]): Future[ExecutionResult[AppContext, JsonNode]] = {
    Executor.execute[AppContext, Unit, JsonNode](
      schema = AppSchema.schema,
      queryAst = queryAst,
      variables = variables.getOrElse(new ObjectNode(null)),
      operationName = operationName,
      userContext = appContext
    )
  }
}
