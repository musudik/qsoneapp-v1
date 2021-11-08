package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.BuildingGroup;

public interface IBuildingGroupRepository extends JpaRepository<BuildingGroup, Long> {
	BuildingGroup findByBuildingGroupId(long id);
}