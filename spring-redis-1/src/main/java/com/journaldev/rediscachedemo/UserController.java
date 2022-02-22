package com.journaldev.rediscachedemo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    @GetMapping(value = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        return userRepository.findById(Long.valueOf(userId)).get();
    }

    @CachePut(value = "users", key = "#userId")
    @PutMapping("/update/{userId}")
    public User updatePersonByID(@PathVariable String userId, @RequestBody User user) {
        User saveduser = userRepository.findById(Long.valueOf(userId)).get();
        if(saveduser!= null) {
            LOG.info("updating the user with id ---"+user.getId());
            saveduser.setName(user.getName());
            saveduser.setFollowers(user.getFollowers());
          userRepository.saveAndFlush(saveduser);
          return saveduser;
        }
        else {
        userRepository.save(user);
        return user;
        }
       
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("/{userId}")
    public void deleteUserByID(@PathVariable Long userId) {
        LOG.info("deleting person with id {}", userId);
        userRepository.deleteById(userId);
    }
    @PostMapping("/postbody")
    public String postBody(@RequestBody User user, @RequestParam("version") Optional<String> version ) {
        return "Hello " + user.getName()+version.get();
        
    }
}
