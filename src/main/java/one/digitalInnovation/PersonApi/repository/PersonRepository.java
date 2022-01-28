package one.digitalInnovation.PersonApi.repository;

import one.digitalInnovation.PersonApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
