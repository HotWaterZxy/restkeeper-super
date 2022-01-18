package com.itheima.restkeeper.face;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.AffixFace;
import com.itheima.restkeeper.UserFace;
import com.itheima.restkeeper.enums.UserEnum;
import com.itheima.restkeeper.exception.ProjectException;
import com.itheima.restkeeper.pojo.Role;
import com.itheima.restkeeper.pojo.User;
import com.itheima.restkeeper.req.AffixVo;
import com.itheima.restkeeper.req.UserVo;
import com.itheima.restkeeper.service.IUserAdapterService;
import com.itheima.restkeeper.service.IUserService;
import com.itheima.restkeeper.utils.BeanConv;
import com.itheima.restkeeper.utils.EmptyUtil;
import com.itheima.restkeeper.utils.ExceptionsUtil;
import com.itheima.restkeeper.utils.ResponseWrapBuild;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserFaceImpl.java
 * @Description 用户dubbo服务实现
 */
@Slf4j
@DubboService(version = "${dubbo.application.version}",timeout = 5000,
    methods ={
        @Method(name = "findUserVoPage",retries = 2),
        @Method(name = "createUser",retries = 0),
        @Method(name = "updateUser",retries = 0),
        @Method(name = "deleteUser",retries = 0)
    })
public class UserFaceImpl implements UserFace {


    @Autowired
    IUserService userService;

    @Autowired
    IUserAdapterService userAdapterService;

    @Override
    public Page<UserVo> findUserVoPage(UserVo userVo,
                                       int pageNum,
                                       int pageSize)throws ProjectException {
        try {
            //执行查询
            //结果集转换
                    //处理附件
                    //处理角色
            return null;
        } catch (Exception e) {
            log.error("查询用户列表异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(UserEnum.PAGE_FAIL);
        }
    }

    @Override
    public UserVo createUser(UserVo userVo)throws ProjectException {
        try {
            //保存用户
            //绑定附件
            //数据回显
            //处理角色
            return null;
        } catch (Exception e) {
            log.error("保存用户异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(UserEnum.CREATE_FAIL);
        }
    }

    @Override
    public Boolean updateUser(UserVo userVo) throws ProjectException{
        try {
            //执行修改
                    //删除图片
                    //绑定新图片
            return null;
        } catch (Exception e) {
            log.error("保存用户异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(UserEnum.UPDATE_FAIL);
        }
    }

    @Override
    public Boolean deleteUser(String[] checkedIds)throws ProjectException {
        try {
            //执行删除
                //删除图片
            return null;
        } catch (Exception e) {
            log.error("删除用户异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(UserEnum.DELETE_FAIL);
        }
    }

    @Override
    public UserVo findUserByUserId(Long userId)throws ProjectException {
        try {
            return null;
        } catch (Exception e) {
            log.error("查找用户所有角色异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(UserEnum.SELECT_USER_FAIL);
        }
    }

    @Override
    public List<UserVo> findUserVoList()throws ProjectException {
        try {
            return null;
        } catch (Exception e) {
            log.error("查找用户list异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(UserEnum.SELECT_USER_LIST_FAIL);
        }
    }
}
