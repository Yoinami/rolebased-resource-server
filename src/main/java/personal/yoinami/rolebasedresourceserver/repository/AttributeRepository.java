package personal.yoinami.rolebasedresourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.yoinami.rolebasedresourceserver.model.Attribute;

import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    public Optional<Attribute> getByName(String name);
}
