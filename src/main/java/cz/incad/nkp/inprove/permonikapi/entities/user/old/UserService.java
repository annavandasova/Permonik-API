//package cz.incad.nkp.inprove.permonikapi.entities.user.old;
//
//import cz.incad.nkp.inprove.permonikapi.entities.user.NewUser;
//import cz.incad.nkp.inprove.permonikapi.entities.user.UserRepo;
//import cz.incad.nkp.inprove.permonikapi.entities.user.dto.ResetPasswordDto;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.json.JSONObject;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.solr.core.query.result.SolrResultPage;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.ForbiddenException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Slf4j
//public class UserService {
//
//    private UserRepo userRepository;
//
//    private ModelMapper modelMapper;
//
//    public NewUser login(HttpServletRequest req, String username, String pwd) {
//        NewUser user = userRepository.findByUsernameIgnoreCase(username);
//
//        if (!user.getHeslo().equals(pwd)) {
//            throw new ForbiddenException("Invalid password");
//        }
//
//        req.getSession().setAttribute("login", user);
//        return user;
//    }
//
//    public Map<String, Object> logout(HttpServletRequest req) {
//        req.getSession().invalidate();
//        return Collections.singletonMap("msg", "logged out");
//    }
//
//    public Map<String, Object> resetHeslo(ResetPasswordDto resetPasswordDto) {
//        NewUser orig = getOne(resetPasswordDto.getId());
//        if (resetPasswordDto.getOldheslo().equals(orig.getHeslo())) {
//            orig.setHeslo(resetPasswordDto.getNewheslo());
//            NewUser saved = userRepository.save(orig);
//            return modelMapper.map(saved, JSONObject.class).toMap();
//        } else {
//            return Collections.singletonMap("error", "heslo.nespravne_heslo");
//        }
//    }
//
//    public NewUser save(NewUser user) {
//        if (userRepository.existsById(user.getId())) {
//            NewUser orig = getOne(user.getId());
//            user.setHeslo(orig.getHeslo());
//        } else {
//            if (user.getHeslo() != null && user.getHeslo().length() != 32) {
//                user.setHeslo(DigestUtils.md5Hex(user.getHeslo()));
//            }
//        }
//
//        return userRepository.save(user);
//    }
//
//    public NewUser getOne(String id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    public Map<String, Object> getAll() {
//        Sort sortByNazev = Sort.by(Sort.Direction.ASC, "nazev");
//        SolrResultPage<NewUser> users = (SolrResultPage<NewUser>) userRepository.findAll(sortByNazev);
//
//        Map<String, Object> mapToMatchOldFormat = new HashMap<>();
//        mapToMatchOldFormat.put("docs", users.toList());
//        mapToMatchOldFormat.put("numFound", users.getSize());
//        return mapToMatchOldFormat;
//    }
//
//    public Map<String, Object> exists(String username) {
//        NewUser newUser = userRepository.findByUsernameIgnoreCase(username);
//        return Collections.singletonMap("exists", newUser != null);
//    }
//
//    @Autowired
//    public void setUserRepository(UserRepo userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    public void setModelMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//}
