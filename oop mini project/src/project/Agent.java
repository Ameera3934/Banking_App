import java.util.ArrayList;
import java.util.Iterator;
public class Agent{

    private ArrayList<Share> Shares;

    public Agent(){
		Shares = new ArrayList<Share>();
    }

    public int getNumberOfShares(){
	return Shares.size();
    }

    public String getShareInfo(int ShareNumber)
	{
		Share c = Shares.get(ShareNumber);
		String text = "";
		text += "Name: " + c.getName() + "\n";
		text += "Total investments: " + c.getFunds() + "\n";
		return text;
    }
    
    public String getListOfShareNames(){
	String text = "";
	Iterator<Share> it = Shares.iterator();
	while (it.hasNext()){
		Share c = it.next();
	    text += c.getName() + "\n";
	}
	return text;
    }
    
    public void addShare(Share c){
		Shares.add(c);
    }

	//when we enter money to invest into company, the funds are updated
    public boolean Invest(String ShareName, int amount){
	Iterator<Share> it = Shares.iterator();
	boolean found = false;	
	while (it.hasNext()){
		Share c = it.next();
	    if (c.getName().equals(ShareName)){
		found = true;
		c.Invest(amount);
	    }
	}
	return found;
    }

	//when we sell back money we've invested, it checks to see if we have enough to sell first.
	public boolean SellShares(String ShareName, int amount){
		Iterator<Share> it = Shares.iterator();
		boolean found = false;
		while (it.hasNext()){
			Share c = it.next();
			if (c.getName().equals(ShareName)){
				if(amount <= c.getFunds()){
					found = true;
					c.SellShares(amount);
				}
			}
		}
		return found;
	}


}
