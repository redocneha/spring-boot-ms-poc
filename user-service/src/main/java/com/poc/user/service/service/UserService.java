package com.poc.user.service.service;

import com.poc.user.service.entity.User;
import com.poc.user.service.repository.UserRepository;
import com.poc.user.service.vo.Department;
import com.poc.user.service.vo.ResponseTemplateVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final static String USER_SERVICE = "USER_SERVICE";
    @Autowired
    private RestTemplate restTemplate;
    private static int count=1;
    public User saveUser(User user) {
        log.info("Inside save user of service");
        return userRepository.save(user);
    }
   // @CircuitBreaker(name=USER_SERVICE,fallbackMethod ="fallBackFind" )
   // @Retry(name= USER_SERVICE,fallbackMethod = "fallBackFind")
    @RateLimiter(name= USER_SERVICE,fallbackMethod = "fallBackFind")
    public ResponseTemplateVO findById(Long id){
        log.info("Inside get user of service");
        log.info("Retry method called "+count++);
        User user = userRepository.findByUserId(id);
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        responseTemplateVO.setUser(user);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getUserId(),Department.class);
        responseTemplateVO.setDepartment(department);
        return responseTemplateVO;
    }

    public ResponseTemplateVO fallBackFind(Exception e){
        log.warn("Department service looks to take so long ");
        return new ResponseTemplateVO(null,null,"Department service looks to take so long from fallback");
    }
}
