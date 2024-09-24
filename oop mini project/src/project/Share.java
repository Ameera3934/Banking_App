public class Share extends User {
     
    private Portfolio account;
    
    public Share(String name){
	super(name);
	this.account = new Portfolio();
    }

    public void Invest(int amount)
    {
	this.account.Invest(amount);
    }

    public void SellShares(int amount)
    {
	this.account.SellShares(amount);
    }

    public int getFunds()
    {
	return this.account.getTotalShares();
    }
    
}
