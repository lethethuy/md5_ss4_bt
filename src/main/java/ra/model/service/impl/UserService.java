package ra.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.User;
import ra.model.repository.UserRepository;
import ra.model.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            // có tồn tại
            return userOptional.get();
        }
        // không tồn tại
        return null;
    }

    // Vì dữ liệu trên đường dẫn là dạng String
    // nền cần 1 method để chuyển đổi từ String sang dạng Long
    @Override
    public User findById(String id) {
        Optional<User> user = null;
        try {
            Long idFind = Long.valueOf(id);
            user = userRepository.findById(idFind);
        } catch (Exception e) {
            return null;
        }
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User save(User p) {
        userRepository.save(userRepository.save(p));

        return p;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(String name, int page, int size, String sort, String by) {
        Sort s;
        if (by.equals("desc")) {
            s = Sort.by(sort).descending();
        } else {
            s = Sort.by(sort).ascending();
        }
        return userRepository.findAllByFullNameContains(name, PageRequest.of(page, size, s));

    }


}
