package au.com.qsone.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDateTime, String> {
    
	@Override
	public String convertToDatabaseColumn(LocalDateTime databaseValue) {
		if (databaseValue != null) {
    		return databaseValue.format(DateTimeFormatter.ofPattern("dd-mm-yyyy"));
    	}
    	
    	return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String entityValue) {
		if (entityValue != null) {
    		return LocalDateTime.parse(entityValue);
    	} 
    	
    	return null;
	}
}