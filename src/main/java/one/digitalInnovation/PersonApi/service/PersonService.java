package one.digitalInnovation.PersonApi.service;

import one.digitalInnovation.PersonApi.dto.MessageResponseDTO;
import one.digitalInnovation.PersonApi.entity.Person;
import one.digitalInnovation.PersonApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
        public MessageResponseDTO createPerson(Person person){
            Person savedPerson = personRepository.save(person);
            return MessageResponseDTO
                    .builder()
                    .message("Created person with ID " + savedPerson.getId())
                    .build();
    }
}
