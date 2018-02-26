package com.github.ikhoon.app.v1.user

import com.github.ikhoon.app.v1.graphql.AppContext
import sangria.macros.derive.{DocumentField, ObjectTypeDescription}
import sangria.schema._
import sangria.macros.derive._

/**
  * Created by ikhoon on 27/02/2018.
  */
object UserSchema {
  val Id = Argument("id", LongType, description = "유저 아이디")

  val UserType: ObjectType[Unit, UserDto] =
    deriveObjectType[Unit, UserDto](
      ObjectTypeDescription("유저 정보"),
      DocumentField("id", "아이디"),
      DocumentField("name", "이름")
    )

  val UserField: Field[AppContext, Unit] = Field(
    "user",
    OptionType(UserType),
    Some("유저정보 가져오기"),
    Id :: Nil,
    c => c.ctx.userService.findByIdWithQuill(c.arg(Id).toInt)
  )

}
