package com.itheima.restkeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.constant.SuperConstant;
import com.itheima.restkeeper.pojo.Brand;
import com.itheima.restkeeper.mapper.BrandMapper;
import com.itheima.restkeeper.req.BrandVo;
import com.itheima.restkeeper.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.restkeeper.utils.BeanConv;
import com.itheima.restkeeper.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description：品牌管理 服务实现类
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public Page<Brand> findBrandVoPage(BrandVo brandVo, int pageNum, int pageSize) {
        //构建分页对象
        //构建查询条件
        //按品牌名称查询
        //按品牌分类查询
        //按品牌状态查询
        //按创建时间降序
        //执行分页查询
        return null;
    }

    @Override
    public Brand createBrand(BrandVo brandVo) {
        //转换BrandVo为Brand
        //执行保存
        return null;
    }

    @Override
    public Boolean updateBrand(BrandVo brandVo) {
        //转换BrandVo为Brand
        return null;
    }

    @Override
    public Boolean deleteBrand(String[] checkedIds) {
        //转换数组为集合
        return null;
    }

    @Override
    public List<Brand> findBrandVoList() {
        //构建查询条件
        //查询有效状态
        return null;
    }
}
