package au.com.qsone.service;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.qsone.entity.Job;
import au.com.qsone.entity.Property;
import au.com.qsone.repository.IJobRepository;
import au.com.qsone.repository.IPropertyRepository;
import au.com.qsone.web.dto.ClientPropertyDto;

@Service
@Transactional
public class EnquiryService {
	
	@Autowired
    private IPropertyRepository propertyRepo;
	
	@Autowired
    private IJobRepository jobRepo;
	
	public Long save(Property property, ClientPropertyDto clientPropertyDto) {
		Property savedProperty = propertyRepo.save(property);
		
		Job job = new Job();
		job.setAssignedDate(new Date());
		job.setCreatedBy(property.getOwner());
		job.setCreatedDate(new Date());
		job.setInspected(false);
		job.setJobType(clientPropertyDto.getJobType());
		job.setStatus(clientPropertyDto.getStatus());
		job.setMainAccessContactName(property.getOwner());
		job.setMainAccessContactPhone(property.getEmail());
		job.setMainAccessContactType("Owner");
		job.setPropertyId(String.valueOf(savedProperty.getId()));

		Job savedJob = jobRepo.save(job);
		
		return savedJob.getId();
    }

}
