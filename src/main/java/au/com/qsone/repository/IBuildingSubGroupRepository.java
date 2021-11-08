package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.BuildingSubGroup;

public interface IBuildingSubGroupRepository extends JpaRepository<BuildingSubGroup, Long> {
	BuildingSubGroup findByBuildingSubGroupId(long id);
}