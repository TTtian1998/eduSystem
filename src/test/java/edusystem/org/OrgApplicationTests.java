package edusystem.org;

import edusystem.org.mapper.PermissionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrgApplicationTests {

    @Autowired
    PermissionMapper permissionMapper;
    @Test
    void contextLoads() {
        System.out.println(permissionMapper.getUserPermission("222"));
    }

}
