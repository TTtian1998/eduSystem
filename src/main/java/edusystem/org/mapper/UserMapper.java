package edusystem.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edusystem.org.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 详细查询
    IPage<User> selectUserDetail(Page<User> user, @Param("deleteStatus") int deleteStatus);

    List<User> selectUserList(@Param("user") User user);

}
