/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

/**
 *
 * @author jessekong
 */

import java.util.List;
import dataaccesslayer.RecipientsDao;
import dataaccesslayer.RecipientsDaoImp;
import transferobjects.RecipientsDTO;

public class RecipientsBusinessLogic {
    private static final int NAME_MAX_LENGTH = 30;
    private static final int CITY_MAX_LENGTH = 30;
    private static final int CATEGORY_MAX_LENGTH = 50;
    
    private RecipientsDao recipientsDao = null;
    
    public RecipientsBusinessLogic() {
        recipientsDao = new RecipientsDaoImp();
    }
    
    public List<RecipientsDTO> getAllRecipients(){
		return recipientsDao.getAllRecipients();
	}
    
    public RecipientsDTO getRecipient(Integer awardID){
		return recipientsDao.getRecipientByAwardId(awardID);
	}
    
    public void addRecipient(RecipientsDTO recipient) throws ValidationException{
		cleanRecipient(recipient);
		validateRecipient(recipient);
		recipientsDao.addRecipient(recipient);
	}
    
    public void deleteRecipient(RecipientsDTO recipient){
		recipientsDao.deleteRecipient(recipient);
	}
    
    private void cleanRecipient(RecipientsDTO recipient){
		if(recipient.getName() != null){ 
			recipient.setName(recipient.getName().trim());
		}
		if(recipient.getYear() != null){ 
			recipient.setYear(recipient.getYear());
		}
                if(recipient.getCity() != null){ 
			recipient.setCity(recipient.getCity().trim());
		}
                if(recipient.getCategory() != null){ 
			recipient.setCategory(recipient.getCategory().trim());
		}
	}
    
    private void validateRecipient(RecipientsDTO recipient) throws ValidationException{
		validateString(recipient.getName(), "Name", NAME_MAX_LENGTH, true);
		validateInt(recipient.getYear(), "Year");
                validateString(recipient.getCity(), "City", CITY_MAX_LENGTH, true);
                validateString(recipient.getCategory(), "Category", CATEGORY_MAX_LENGTH, true);
	}
    
    private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed)
	    throws ValidationException{
		if(value == null && isNullAllowed){
			// return; // null permitted, nothing to validate
		}
		else if(value == null && ! isNullAllowed){
		    throw new ValidationException(String.format("%s cannot be null", 
		    		fieldName));
		}
		else if(value.length() == 0){
			throw new ValidationException(String.format("%s cannot be empty or only whitespace", 
					fieldName));
		}
		else if(value.length() > maxLength){
			throw new ValidationException(String.format("%s cannot exceed %d characters", 
					fieldName, maxLength));
		}
	}
    
    private void validateInt(int value, String fieldName)
	    throws ValidationException{
		if(value <= 0){
			throw new ValidationException(String.format("%s cannot be a negative number", 
					fieldName));
		}
	}
}
