package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.Item;

public interface IItemRepository extends JpaRepository<Item, Long> {
}