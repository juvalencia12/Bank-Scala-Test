package main.scala.com.h2.entities

import java.time.LocalDate
import java.util.UUID

class Customer(f: String, l: String, e: String, dob: LocalDate) {
  val first: String = f
  val last: String = l
  val email: String = e
  val dateOfBirth: LocalDate = dob

  val id: UUID = UUID.randomUUID()
  override def toString: String = first + "." + last
}