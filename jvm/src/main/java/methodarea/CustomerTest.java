package methodarea;

/**
 * @author :覃玉锦
 * @create :2021-01-15 21:57:00
 */
public class CustomerTest {
    public static void main(String[] args) {
        Customer cust = new Customer();
    }
}

class Customer{
    int id = 1001;
    String name;
    Account acct;

    {
        name = "匿名客户";
    }
    public Customer(){
        acct = new Account();
    }

}
class Account{

}
