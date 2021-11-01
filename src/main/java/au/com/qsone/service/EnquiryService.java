package au.com.qsone.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.qsone.entity.Job;
import au.com.qsone.entity.Property;
import au.com.qsone.repository.IJobRepository;
import au.com.qsone.repository.IPropertyRepository;

@Service
@Transactional
public class EnquiryService {
	
	@Autowired
    private IPropertyRepository propertyRepo;
	
	@Autowired
    private IJobRepository jobRepo;
	
	public Long save(Property property) {
		Property savedProperty = propertyRepo.save(property);
		
		Job job = new Job();
		job.setAssignedDate(new Date());
		job.setCreatedBy("anonymousUser");
		job.setCreatedDate(new Date());
		job.setInspected(false);
		job.setJobType("Enquiry");
		job.setStatus("CREATED");
		job.setMainAccessContactName(property.getOwner());
		job.setMainAccessContactPhone(property.getEmail());
		job.setMainAccessContactType("Owner");
		job.setPropertyId(String.valueOf(savedProperty.getId()));

		Job savedJob = jobRepo.save(job);
		
		return savedJob.getId();
    }

}
