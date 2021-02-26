package dckj.arrange.producer.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import dckj.arrange.common.entity.BaseUser;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.response.BaseUserRes;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.MD5Utils;
import dckj.arrange.producer.mapper.BaseUserMapper;
import dckj.arrange.producer.service.BaseUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Classname BaseUserServiceImpl
 * @Description TODO
 * @Date 2019/8/13 16:49
 * @Created by JinPeng
 * @Version 1.0
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    @Autowired
    private dckj.arrange.producer.mapper.PkSchoolMapper pkSchoolMapper;

    @Autowired
    private dckj.arrange.producer.mapper.PkTeacherInfoMapper pkTeacherInfoMapper;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public BaseUserRes login(BaseUser user) {
        BaseUser bu = new BaseUser();
        BaseUserRes bur = new BaseUserRes();
        if (!EmptyUtil.isEmpty(user.getAccount()) && (EmptyUtil.isEmpty(user.getPhone()))) {
            bu = this.selectOne(new EntityWrapper<BaseUser>().eq("account",user.getAccount()));
        }else if (EmptyUtil.isEmpty(user.getAccount()) && (!EmptyUtil.isEmpty(user.getPhone()))){
            bu = this.selectOne(new EntityWrapper<BaseUser>().eq("phone",user.getPhone()));
        }
        if (EmptyUtil.isEmpty(bu)) {
            throw new CheckedException("该用户未注册！");
        }else if (bu.getStatus() == 0) {
            throw new CheckedException("该用户已禁用，请联系管理员！");
        }
        if (!bu.getPassword().equals(MD5Utils.encodeMemberPassword(user.getPassword()))) {
            throw new CheckedException("密码错误！");
        }
        this.updateById(bu);
        PkTeacherInfo pti = new PkTeacherInfo();
        if (bu.getRegisterType() != 0 && bu.getRegisterType() != 3) {
            pti.setUserId(bu.getId());
            pti = pkTeacherInfoMapper.selectOne(pti);
        }

        bu.setLastLoginTime(new Date());
        bur.setSchoolCode(pti.getSchoolCode());
        BeanUtils.copyProperties(bu,bur);
        //登录成功

        return bur;
    }

}
