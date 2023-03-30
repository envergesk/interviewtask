package ru.kildeev.marketApplication.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kildeev.marketApplication.core.api.ProductDto;
import ru.kildeev.marketApplication.core.converters.ProductConverter;
import ru.kildeev.marketApplication.core.entities.Order;
import ru.kildeev.marketApplication.core.entities.Product;
import ru.kildeev.marketApplication.core.entities.Role;
import ru.kildeev.marketApplication.core.entities.User;
import ru.kildeev.marketApplication.core.repositories.ProductRepository;
import ru.kildeev.marketApplication.core.repositories.RoleRepository;
import ru.kildeev.marketApplication.core.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductConverter productConverter;

    private final OrderService orderService;

    private final ProductService productService;

    private final ProductRepository productRepository;

    public boolean checkAccess(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователя с таким id не существует"));
        if (user.getRoles().stream().map(Role::getTitle).toList().contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    public void updateAccess(Long adminId, Long userId) {
        User admin = userRepository.findById(adminId).orElseThrow(() -> new RuntimeException("Пользователя с таким id не существует"));
        if (admin.getRoles().stream().map(Role::getTitle).toList().contains("ROLE_ADMIN")) {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователя с таким id не существует"));
            user.getRoles().add(roleRepository.findByTitle("ROLE_ADMIN"));
        }
        //TODO: refactor
    }

    public Product updateProductInfo(Long adminId, ProductDto productDto) {
        if (checkAccess(adminId)) {
            Product product = productConverter.dtoToEntity(productDto);
            productService.saveProduct(product);
            return product;
        }
        return null; //TODO: refactor
    }

    public void updateUserBalance(Long adminId, Long userId, BigDecimal delta) {
        if (checkAccess(adminId)) {
            User user = userRepository.findById(userId).get();
            user.setBalance(user.getBalance().add(delta));
        }
    }

    public void checkUserInfo(Long adminId, Long userId) {
        if (checkAccess(adminId)) {
            User user = userRepository.findById(userId).get();
            System.out.println(user.toString());
        }
    }

    public void deleteUser(Long adminId, Long userId) {
        if (checkAccess(adminId)) {
            userRepository.delete(userRepository.findById(userId).get());
        }
    }

    public void deprecateUser(Long adminId, Long userId) {
        if (checkAccess(adminId)) {
            User user = userRepository.findById(userId).get();
            user.setIsDeprecated(Boolean.TRUE);
        }
    }

    public void changeProduct(Long adminId, Product product) {
        if(checkAccess(adminId)) {
            productService.saveProduct(product);
        }
    }

    @Transactional
    public void buyProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        if (user.getBalance().compareTo(product.getPrice()) >= 0) {
            user.setBalance(user.getBalance().subtract(product.getPrice()));
            Order order = new Order();
//            order.setUsername(user.getUsername());
//            order.setProduct(product.getTitle());
            orderService.saveOrder(order);
        }
        //TODO: return statuscode
    }

    public void commentToProduct(Long userId, Long productId) {

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким именем не найден"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }
}
