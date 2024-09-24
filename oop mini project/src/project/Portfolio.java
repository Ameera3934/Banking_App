
public class Portfolio{
    private int totalShares;
    
    public Portfolio()
    {
        this.totalShares=0;
    }    

    public Portfolio(int totalShares)
    {
        this.totalShares = totalShares;
    }

    public int getTotalShares()
    {
        return this.totalShares;
    }    

    public void Invest(int amount)
    {
        this.totalShares += amount;
    }    

    public void SellShares(int amount)
    {
        this.totalShares -= amount;
    }

    public String toString()
    {
	return "Total Share: " + this.totalShares;
    }
    
}
