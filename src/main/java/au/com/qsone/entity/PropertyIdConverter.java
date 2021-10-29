package au.com.qsone.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PropertyIdConverter implements AttributeConverter<String, Long> {
	
	@Override
	public Long convertToDatabaseColumn(String attribute) {
		if (attribute!= null) 
			return Long.parseLong(attribute);
		
		return (long) 0;
	}

	@Override
	public String convertToEntityAttribute(Long dbData) {
		if (dbData != null)
			return String.valueOf(dbData);
		
		return null;
	}

}
