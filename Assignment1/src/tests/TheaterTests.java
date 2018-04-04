package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import business.model.ShowModel;
import business.services.PasswordEncryptor;
import business.services.ShowService;

public class TheaterTests {
	
	@Test
	public void passwordEncryptor() {
		
		assertEquals("3FA814B227DFA89CD634567783C48D595B016FABF3F073C275687E3D473EA371",PasswordEncryptor.computeHash("Andrei"));
		assertEquals("B1259567B8A27CD0EE0CE4C79D0670C75BADA9E86DCDEFF374FFD922D41CBE7E",PasswordEncryptor.computeHash("Dan"));
		assertEquals("4D3D5F7BB907957D2A7472C50C658A5E57AB7E720AF0521522789554FB106A30",PasswordEncryptor.computeHash("Matei"));
	
	}
	
	@Test
	public void ticketLimit() {
		ShowService showService = new ShowService();
		ShowModel showModel = new ShowModel();
		showModel.setRemainingTickets(2);
		assertEquals(true,showService.decrementTickets(showModel));
		assertEquals(true,showService.decrementTickets(showModel));
		assertEquals(false,showService.decrementTickets(showModel));
	}

}
