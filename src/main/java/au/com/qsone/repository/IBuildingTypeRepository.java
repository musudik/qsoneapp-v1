package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.BuildingType;

public interface IBuildingTypeRepository extends JpaRepository<BuildingType, Long> {
	BuildingType findByBuildingTypeId(long id);
}