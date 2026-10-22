package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AclEntryService {

    private final AclEntryRepository aclEntryRepository;

//    @Cacheable("access")
    public boolean hasAccess(long employeeId, String username) {
        return aclEntryRepository
                .findByEmployeeIdAndUsername(employeeId, username)
                .isPresent();
    }
}
