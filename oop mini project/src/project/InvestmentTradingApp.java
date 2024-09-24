/** Investment trading app */
import java.awt.*;
import java.awt.event.*;

public class InvestmentTradingApp extends Frame {

	private static TextArea infoArea = new TextArea("Investment Trading App ");
	public static void print(String text){
		infoArea.setText(text);
	}

	private Agent agent;
	private Panel ShareButtonsPanel;

	//This is for printing a success message when the amount we want to invest goes through, otherwise it prints out error
	public void Invest(String name, int amount){
		boolean found = agent.Invest(name, amount);
		if(found == false){
			System.out.println("Transaction failed");
		}
		else{
			System.out.println("Successful transaction");
		}
	}
	// Prints error if we do not have enough money to sell, and prints success otherwise
	public void SellShares(String name, int amount){
		boolean found = agent.SellShares(name, amount);
		if(found == false){
			System.out.println("Transaction failed - not enough shares to sell");
		}
		else{
			System.out.println("Successful transaction");
		}
	}
	// prints the names of all Shares.
	public void printShares()
	{
		String text = agent.getListOfShareNames();
		print(text);
	}
	// prints the information of the Share with the given index.
	public void printShareInfo(int index){
		String text = agent.getShareInfo(index);
		print(text);
	}

	// This method takes all the necessary steps when a Share is added
	public void addShare(String name){
		agent.addShare(new Share(name));

		//prints the information for each share in their own window when their specific button is pressed.
		int numShares = agent.getNumberOfShares();
		Button btn = new Button("Share " + numShares);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				printShareInfo(numShares-1);
			}
		});
		ShareButtonsPanel.add(btn);
		this.setVisible(true); // Just to refresh the frame, so that the button shows up
	}

	public InvestmentTradingApp(){

		this.agent = new Agent();
		this.setLayout(new FlowLayout());

		//prints list of all company names when u click the print shares list button
		Button reportButton=new Button("Portfolio");
		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				printShares();   //function print Shares list
			}
		});
		this.add(reportButton);

		//user adds a share they want to invest into when they click the add share button - gets added to share list
		Button addShareButton=new Button("Add Share");
		addShareButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				Prompt acp = new Prompt();

				TextField TF = new TextField("Enter Share Name"); //text field created for the Share name
				acp.add(TF);

				acp.addSubmitListener(new ActionListener() {         //submit button
					public void actionPerformed(ActionEvent evt) {
						addShare(TF.getText());
					}
				});
				acp.activate();
			}
		});

		this.add(addShareButton);


		//button for user to input how much they want to invest in specific company, that companies funds get updated.
		Button InvestButton = new Button("Invest");
		InvestButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				Prompt acp = new Prompt();

				TextField TF = new TextField("Enter Share Name");
				acp.add(TF);
				TextField TF2 = new TextField("Enter amount to invest");
				acp.add(TF2);

				acp.addSubmitListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String name = TF.getText();
						int amount = Integer.parseInt(TF2.getText());
						Invest(name, amount);
					}
				});
				acp.activate();
			}
		});
		this.add(InvestButton);

		//button to sell company shares - updates company total funds
		Button SellSharesButton = new Button("Sell Shares");
		SellSharesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				Prompt acp = new Prompt();

				TextField TF = new TextField("Enter Share Name");
				acp.add(TF);
				TextField TF2 = new TextField("Enter amount to sell ");
				acp.add(TF2);

				acp.addSubmitListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String name = TF.getText();
						int amount = Integer.parseInt(TF2.getText());
						SellShares(name, amount);
					}
				});
				acp.activate();
			}
		});
		this.add(SellSharesButton);

		// Output console
		infoArea.setEditable(false);
		this.add(infoArea);

		// Share button panel
		ShareButtonsPanel = new Panel();
		ShareButtonsPanel.setLayout(new GridLayout(0,1));
		ShareButtonsPanel.setVisible(true);
		this.add(ShareButtonsPanel);

		// automatically add this share for testing purpose
		this.addShare("Mixed share");

		// closes app when we press X
		WindowCloser wc = new WindowCloser();
		this.addWindowListener(wc);

		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args){
		new InvestmentTradingApp();
	}
}
