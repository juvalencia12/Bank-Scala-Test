package main.scala.com.h2.entities

object Email {
  def apply(value: String, domain: String): Email = new Email(value, domain)

}

class Email( value: String, domain: String) {
  override def toString: String = s"$value@$domain"
}

case class Email2( value: String, domain: String) {
  override def toString: String = s"$value@$domain"
}