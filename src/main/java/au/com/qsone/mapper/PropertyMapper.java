package au.com.qsone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import au.com.qsone.entity.Property;
import au.com.qsone.web.dto.ClientPropertyDto;

@Mapper
public interface PropertyMapper {
	PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);
	
	public ClientPropertyDto entityToDto(Property enity);
	public Property dtoToEntity(ClientPropertyDto dto);
}
