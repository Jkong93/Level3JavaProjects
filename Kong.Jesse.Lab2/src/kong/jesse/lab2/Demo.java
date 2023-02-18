/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kong.jesse.lab2;

/**
 *
 * @author jessekong
 */
import java.util.List;
import businesslayer.RecipientsBusinessLogic;
import businesslayer.ValidationException;
import transferobjects.RecipientsDTO;
public class Demo {

	public void demo(){
            try {
                RecipientsBusinessLogic logic = new RecipientsBusinessLogic();
		List<RecipientsDTO> list = null;
		RecipientsDTO recipient = null;
                
                System.out.println("Printing Recipients");
			list = logic.getAllRecipients();
			printRecipients(list);
                
                System.out.println("Printing One Recipient");
			recipient = logic.getRecipient(1);
			printRecipient(recipient);
			System.out.println();
                        
                System.out.println("Inserting One Recipient");
			recipient = new RecipientsDTO();
			recipient.setName("Kong; Jesse");
			recipient.setYear(2023);
                        recipient.setCity("Ottawa");
                        recipient.setCategory("test");
			logic.addRecipient(recipient);
			list = logic.getAllRecipients();
			printRecipients(list);
                        
                System.out.println("Deleting last recipient");
			recipient = list.get(list.size() - 1);
			logic.deleteRecipient(recipient);
			list = logic.getAllRecipients();
			printRecipients(list);
            }
            		catch(ValidationException e){
			System.err.println(e.getMessage());
		}
	}
        private static void printRecipient(RecipientsDTO recipient){
	    	String output = String.format("%d, %s, %d, %s, %s",
	    			recipient.getAwardID(),
	    			recipient.getName(),
	    			recipient.getYear(),
                                recipient.getCity(),
                                recipient.getCategory());
	    	System.out.println(output);
        }
        private static void printRecipients(List<RecipientsDTO> recipients){
	    for(RecipientsDTO recipient : recipients){
	    	printRecipient(recipient);
	    }
	    System.out.println();
}
}

