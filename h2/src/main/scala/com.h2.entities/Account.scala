package main.scala.com.h2.entities

import java.util.UUID

//class Account(c: Customer, p: Product, b: Int) {
//  val customer: Customer = c
//  val product: Product = p
//  private var balance: Int = b
//
//  def deposit(amount: Int): Unit = {
//    println(s"Depositing $amount to $customer account")
//    balance += amount
//  }
//
//  def withdraw(amount: Int): Unit = {
//    println(s"Withdrawing $amount to $customer account")
//    balance -= amount
//  }
//
//  override def toString = s"$customer with $product has remaining balance of $balance"
//}

/* ----------------------------- Segunda Version ------------------- */

abstract class Account {
  val id: UUID = UUID.randomUUID()
  val customer: Customer
  val product: Product

  def getBalance: Dollars
}

class DepositAccount(val customer: Customer,
                     val product: Product,
                     private var balance: Dollars) extends Account {

  def deposit(dollars: Dollars): Unit = {
    require(amount > 0, "amount deposited should be greater than zero")
    println(s"Depositing $amount to $customer account")
    balance += amount
  }

  def withdraw(dollars: Dollars): Unit = {
    require(amount > 0 && balance > amount,
      "amount should be greater than zero and requested amount should be less than or equal to balance ")
    println(s"Depositing $amount to $customer account")
    balance -= amount
  }

  override def getBalance: Dollars = balance

  override def toString = s"$customer with $product has ramaining balance of $balance"
}

class LendingAccount(val customer: Costumer,
                     val product: Lending,
                     private val balance: Dollars) extends Account {

  def payBill(dollars: Dollars): Unit = {
    require(amount > 0, "the payment must be made for amount greater than zero")
    println(s"Paying bill of $amount against $customer account")
    balance += amount
  }

  def withdraw(dollars: Dollars): Unit = {
    require(amount > 0 "the witdrawl amount must be greater than zero")
    println(s"Debiting $amount to $customer account")
    balance -= amount
  }

  override def getBalance: Dollars = balance
  override def toString = s"$customer with $product has remaining balance of $balance"
}