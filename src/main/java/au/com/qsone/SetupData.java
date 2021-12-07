package au.com.qsone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import au.com.qsone.entity.Role;
import au.com.qsone.repository.IRoleRepository;
import au.com.qsone.web.dto.JobState;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
	private final static Logger logger = LoggerFactory.getLogger(SetupData.class);
	boolean alreadySetup = false;

    @Autowired
    private IRoleRepository roleRepository;
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;
        createRoleIfNotFound("ADMIN");
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
    
    @Bean(name="jobStates")
	public Map<String, JobState> jobStates() throws FileNotFoundException {
		Yaml yaml = new Yaml(new Constructor(JobState.class));
		File file = ResourceUtils.getFile("classpath:state.yml");
        InputStream inputStream = new FileInputStream(file);
        Map<String, JobState> jobStates = new HashMap<String, JobState>();
        for (Object object : yaml.loadAll(inputStream)) {
        	JobState js = (JobState) object;
        	jobStates.put(js.getState(), js);
        }
        //System.out.println(map.get("JOB_DATA_CAPTURE").getNextStates().get(0).getState());
        logger.info("Loaded Job States from state.yml");
        return jobStates;
    }
}
