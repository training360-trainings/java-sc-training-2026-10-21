package employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AclEntryRepository extends JpaRepository<AclEntry, Long> {

    Optional<AclEntry> findByEmployeeIdAndUsername(long employeeId, String username);
}
