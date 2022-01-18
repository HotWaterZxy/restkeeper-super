package com.itheima.restkeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.basic.BasicPojo;
import com.itheima.restkeeper.constant.SuperConstant;
import com.itheima.restkeeper.pojo.Store;
import com.itheima.restkeeper.mapper.StoreMapper;
import com.itheima.restkeeper.req.StoreVo;
import com.itheima.restkeeper.service.IStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.restkeeper.utils.BeanConv;
import com.itheima.restkeeper.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description：门店信息账号 服务实现类
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {

    @Override
    public Page<Store> findStoreVoPage(StoreVo storeVo, int pageNum, int pageSize) {
        //分页条件构建
        //按门店名称查询
        //按门店状态查询
        return null;
    }

    @Override
    public Store createStore(StoreVo storeVo) {
        return null;
    }

    @Override
    public Boolean updateStore(StoreVo storeVo) {
        return null;
    }

    @Override
    public Boolean deleteStore(String[] checkedIds) {
        return null;
    }

    @Override
    public List<Store> findStoreVoList() {
        return null;
    }

}
