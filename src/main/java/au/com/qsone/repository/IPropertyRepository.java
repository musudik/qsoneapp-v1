package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.com.qsone.entity.Property;

import java.util.List;

public interface IPropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p " +
            "where 1=1" +
            "and ( upper(p.owner) like concat('%', upper(?1), '%') " +
            "       or upper(p.propertyType) like concat('%', upper(?1), '%') " +
            "       or upper(p.mobile) like concat('%', upper(?1), '%')" +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Property> searchProperty(String criteria);
}