package JavaWeb.SpringBoot.repository;

import JavaWeb.SpringBoot.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Page<Cart>> findAll(Pageable pageable);
}
