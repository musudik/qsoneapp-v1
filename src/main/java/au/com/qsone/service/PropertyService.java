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

import au.com.qsone.entity.Property;
import au.com.qsone.repository.IPropertyRepository;

@Service
@Transactional
public class PropertyService {

    @Autowired
    private IPropertyRepository repo;

    public List<Property> listAll() {
        return repo.findAll();
    }

    public Page<Property> findPaginated(String search, Pageable pageable) {
        List<Property> Propertys = repo.searchProperty(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Property> list;

        if (Propertys.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, Propertys.size());
            list = Propertys.subList(startItem, toIndex);
        }

        Page<Property> bookPage = new PageImpl<Property>(list, PageRequest.of(currentPage, pageSize), Propertys.size());

        return bookPage;
    }


    public void save(Property property) {
        repo.save(property);
    }

    public Property get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}