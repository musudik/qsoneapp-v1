package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.com.qsone.entity.Job;

import java.util.List;

public interface IJobRepository extends JpaRepository<Job, Long> {
    @Query("select j from Job j " +
            "where 1=1" +
            "and ( upper(j.jobType) like concat('%', upper(?1), '%') " +
            "       or upper(j.status) like concat('%', upper(?1), '%') " +
            "       or upper(j.inspected) like concat('%', upper(?1), '%')" +
            ")")
    List<Job> searchJob(String criteria);
}