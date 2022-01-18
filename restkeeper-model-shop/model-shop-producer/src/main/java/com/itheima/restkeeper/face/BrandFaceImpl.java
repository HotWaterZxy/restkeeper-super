package com.itheima.restkeeper.face;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.AffixFace;
import com.itheima.restkeeper.BrandFace;
import com.itheima.restkeeper.enums.BrandEnum;
import com.itheima.restkeeper.exception.ProjectException;
import com.itheima.restkeeper.pojo.Brand;
import com.itheima.restkeeper.req.AffixVo;
import com.itheima.restkeeper.req.BrandVo;
import com.itheima.restkeeper.service.IBrandService;
import com.itheima.restkeeper.utils.BeanConv;
import com.itheima.restkeeper.utils.EmptyUtil;
import com.itheima.restkeeper.utils.ExceptionsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BrandFaceImpl.java
 * @Description 品牌dubbo接口定义实现
 */
@DubboService(version = "${dubbo.application.version}",timeout = 5000,
    methods ={
        @Method(name = "findBrandVoPage",retries = 2),
        @Method(name = "createBrand",retries = 0),
        @Method(name = "updateBrand",retries = 0),
        @Method(name = "deleteBrand",retries = 0)
    })
@Slf4j
public class BrandFaceImpl implements BrandFace {

    @Autowired
    IBrandService brandService;



    @Override
    public Page<BrandVo> findBrandVoPage(BrandVo brandVo,
                                         int pageNum,
                                         int pageSize) throws ProjectException{
        try {
            //查询Page<Brand>图片分页
            //转化Page<Brand>为Page<BrandVo>
            //转换List<Brand>为 List<BrandVo>
            //处理附件
            //返回结果
            return null;
        } catch (Exception e) {
            log.error("查询品牌列表异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(BrandEnum.PAGE_FAIL);
        }
    }

    @Override
    public BrandVo createBrand(BrandVo brandVo) throws ProjectException{
        try {
            //执行保存
            //绑定附件
            return null;
        } catch (Exception e) {
            log.error("保存品牌异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(BrandEnum.CREATE_FAIL);
        }
    }

    @Override
    public Boolean updateBrand(BrandVo brandVo)throws ProjectException {
        try {
            //执行修改
            //修改对应的附件图片（先删除后添加）
                    //删除图片
                    //绑定新图片
            return null;
        } catch (Exception e) {
            log.error("修改品牌列表异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(BrandEnum.UPDATE_FAIL);
        }

    }

    @Override
    public Boolean deleteBrand(String[] checkedIds) throws ProjectException{
        try {
            //执行删除
            //删除图片
            return null ;
        } catch (Exception e) {
            log.error("删除品牌列表异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(BrandEnum.DELETE_FAIL);
        }
    }

    @Override
    public BrandVo findBrandByBrandId(Long brandId)throws ProjectException {
        try {
            return null;
        } catch (Exception e) {
            log.error("查找品牌所有品牌异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(BrandEnum.SELECT_BRAND_FAIL);
        }
    }

    @Override
    public List<BrandVo> findBrandVoList()throws ProjectException {
        try {
            return null;
        } catch (Exception e) {
            log.error("查询品牌列表异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(BrandEnum.PAGE_FAIL);
        }
    }
}
