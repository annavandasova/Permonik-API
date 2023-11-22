package cz.incad.nkp.inprove.permonikapi.originentities.user;

import cz.incad.nkp.inprove.permonikapi.originentities.user.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final SolrOperations solrTemplate;

    @Autowired
    public UserService(UserRepository userRepository, SolrOperations solrTemplate) {
        this.userRepository = userRepository;
        this.solrTemplate = solrTemplate;
    }


    public List<UserDTO> getUsers() {
        Iterable<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, UserDefinition.NAME_FIELD));
        
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);

        return userList.stream().map(user -> new UserDTO(
            user.getId(),
            user.getActive(),
            user.getEmail(),
            user.getName(),
            user.getOwner(),
            user.getNote(),
            user.getRole(),
            user.getUserName()
        )).toList();
    }

    public Boolean updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.id()).orElse(null);

        if(user != null){

            user.setActive(userDTO.active());
            user.setEmail(userDTO.email());
            user.setName(userDTO.name());
            user.setOwner(userDTO.owner());
            user.setNote(userDTO.note());
            user.setRole(userDTO.role());
            user.setUserName(userDTO.userName());

            try {
                userRepository.save(user);
                return true;
            } catch (Exception e){
                throw new RuntimeException(e);
            }

        } else{
            return false;
        }
    }
}
