package au.com.qsone.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.qsone.entity.Job;
import au.com.qsone.repository.IJobRepository;

@Service
@Transactional
public class JobService {

    @Autowired
    private IJobRepository repo;

    public List<Job> listAll() {
        return repo.findAll();
    }

    public Page<Job> findPaginated(String search, Pageable pageable) {
        List<Job> Jobs = repo.searchJob(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Job> list;

        if (Jobs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, Jobs.size());
            list = Jobs.subList(startItem, toIndex);
        }

        Page<Job> bookPage = new PageImpl<Job>(list, PageRequest.of(currentPage, pageSize), Jobs.size());

        return bookPage;
    }


    public void save(Job Job) {
        repo.save(Job);
    }

    public Job get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}