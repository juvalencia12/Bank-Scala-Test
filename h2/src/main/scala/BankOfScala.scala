import main.scala.com.h2.entities._
import java.util.UUID

//object BankOfScala {
//  def main(args: Array[String]): Unit = {
//    println("Instantiating Bank")
//
//    val coreChecking = new CoreChecking(Dollars(1000), 0.025))
//    val studentCheckings = new StudentCheckings(Dollars(0), 0.010))
//    val rewardsSavings = new RewardsSavings(Dollars(10000), 0.10, 1))
//    val creditCard = new CreditCard(99.00, 14.23, 20.00)
//    val products = Set(coreChecking, studentCheckings, rewardsSavings, creditCard)
//
//    val bobMartin = new Customer("Bob", "Martin", Email("bob","martin.com"),
//      LocalDate.of(1983, 8, 22))
//    val bobCheckingAccount = new Account (bobMartin, coreChecking, Dollars(1000))
//    val bobSavingsAccount = new Account (bobMartin, rewardsSavings, Dollars(20000))
//    val bobCreditAccount = new Account (bobMartin, creditCard, Dollars(4500))
//    val accounts = Set(bobCheckingAccount, bobSavingsAccount, bobCreditAccount)
//
//    val bank = new Bank("Bank of Scala", "Auckland", "New Zealand",
//    Email("bank","scala.com"), products, Set(bobMartin), accounts)
//
//    println(bobCheckingAccount)
//
//    bobCheckingAccount.deposit(100)
//    println(bobCheckingAccount)
//
//    bobCheckingAccount.withdraw(200)
//    println(bobCheckingAccount)
//  }
//}

/* ------------------ Tercera version ------------------------*/

object BankOfScala {
  def main(args: Array[String]): Unit = {

    println("Opening Bank")

    val bank = new Bank(name = "Bank of Scala", country = "New Zealand", city = "Auckland", email = Email("bank","scala.com"))
    val customerIds = getCustomers map (c => bank.createNewCustomer(c._1, c._2, c._3, c._4))
    val depositProductIds = getDepositProducts map (p => bank.addNewDepositProduct(p._1, p._2, p._3))
    val lendingProductIds = getLendingProducts map (l => bank.addNewLendingProduct(l._2, l._3, l._4))

    /* logging */
    println(s"Bank: $bank")
    println(s"CustomerIds: $customerIds")
    println(s"DepositProductIds: $depositProductIds")
    println(s"LendingProductIds: $lendingProductIds")

    def openAccounts(customerId: UUID, productId: UUID, productType: String) = productTypematch {
      case "Deposits" => bank.openDepositAccount(customerId, productId, _: Dollars)
      case "Lending" => bank.openLendingAccount(customerId, productId, _: Dollars)
    }

    /* Opening accounts but without money the accounts are not active */
    val depositAccounts = for {c <- customerIds; p <- depositProductIds} yield bank.openDepositAccount(c, p, "Deposits")

    /* Depositing money into accounts */
    val random = new scala.util.Random()
    val depositAccountIds = depositAccounts.map(account => account(Dollars(10000 + random.nextInt(10000))))

    /* logging */
    println(s"Deposits Accounts: $depositAccounts")
    println(s"Deposit Account Ids: $depositAccountIds")

    /* open credit card accounts but balance will be known later */
    val lendingAccounts = for {c <- customerIds; p <- depositProductIds} yield bank.openLendingAccount(c, p, "Lending")
    val lendingAccountIds = lendingAccounts.map(account => account(Dollars(random.nextInt(500))))

    /* logging */
    println(s"Lending Accounts: $lendingAccounts")
    println(s"Lending Account Ids: $lendingAccountIds")
    println(s"Bank: $bank")

    /* Performing Deposit Account Transactions */
    val randomAmount = new scala.util.Random(100)
    depositAccountIds.foreach(bank.deposit(_, Dollars(1 + randomAmount.nextInt(100))))
    depositAccountIds.foreach(bank.withdraw(_, Dollars(1 + randomAmount.nextInt(50))))

    /* Performing Lending Accounts Transactions */
    depositAccountIds.foreach(bank.useCreditCard(_, Dollars(1 + randomAmount.nextInt(500))))
    depositAccountIds.foreach(bank.payCreditCardBill(_, Dollars(1 + randomAmount.nextInt(100))))
  }

  /* ---------------------Data -----------------------*/
  def getCustomers: Seq[(String, String, String, String)] = {
    Seq(
      ("Bob", "Martin", "bob@martin.com", "1976/11/25"),
      ("Amy", "Jones", "amy.jones@google.com", "1983/4/12"),
      ("Taylor", "Jackson", "taylor33@jackson.com", "1985/4/5")
    )
  }

  def getDepositProducts: Seq[(String, Int, Double)] = {
    Seq(
      ("CoreChecking", 1000, 0.025),
      ("StudentCheckings", 0, 0.010),
      ("RewardsSavings", 10000, 0.10)
    )
  }

  def getLendingProducts: Seq[(String, Double, Double, Double)] = {
    Seq(("CreditCard", 99.00, 14.23, 20.00))
  }

}