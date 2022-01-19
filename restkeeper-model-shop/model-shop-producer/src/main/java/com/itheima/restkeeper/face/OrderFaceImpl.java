 package com.itheima.restkeeper.face;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.restkeeper.*;
import com.itheima.restkeeper.constant.AppletCacheConstant;
import com.itheima.restkeeper.constant.SuperConstant;
import com.itheima.restkeeper.constant.TradingConstant;
import com.itheima.restkeeper.enums.OrderEnum;
import com.itheima.restkeeper.enums.OrderItemEnum;
import com.itheima.restkeeper.enums.ShoppingCartEnum;
import com.itheima.restkeeper.enums.TradingEnum;
import com.itheima.restkeeper.exception.ProjectException;
import com.itheima.restkeeper.pojo.Dish;
import com.itheima.restkeeper.pojo.Order;
import com.itheima.restkeeper.pojo.OrderItem;
import com.itheima.restkeeper.req.*;
import com.itheima.restkeeper.service.IDishService;
import com.itheima.restkeeper.service.IOrderItemService;
import com.itheima.restkeeper.service.IOrderService;
import com.itheima.restkeeper.utils.BeanConv;
import com.itheima.restkeeper.utils.EmptyUtil;
import com.itheima.restkeeper.utils.ExceptionsUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName OrderFaceImpl.java
 * @Description 订单服务实现
 */
@Slf4j
@DubboService(version = "${dubbo.application.version}",timeout = 5000,
    methods ={
        @Method(name = "findOrderVoPage",retries = 2),
        @Method(name = "opertionToOrderItem",retries = 0),
        @Method(name = "handleTrading",retries = 0),
        @Method(name = "handleTradingRefund",retries = 0),
        @Method(name = "queryQrCode",retries = 2),
        @Method(name = "findOrderVoPaid",retries = 2),
        @Method(name = "findOrderVoPaying",retries = 2),
        @Method(name = "synchTradingState",retries = 2)

    })
public class OrderFaceImpl implements OrderFace {


    @DubboReference(version = "${dubbo.application.version}", check = false)
    TableFace tableFace;

    @DubboReference(version = "${dubbo.application.version}", check = false)
    CustomerFace customerFace;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderItemService orderItemService;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    IDishService dishService;

    @DubboReference(version = "${dubbo.application.version}", check = false)
    CreditPayFace creditPayFace;

    @DubboReference(version = "${dubbo.application.version}", check = false)
    CashPayFace cashPayFace;

    @DubboReference(version = "${dubbo.application.version}", check = false)
    NativePayFace nativePayFace;

    @Override
    public Page<OrderVo> findOrderVoPage(OrderVo orderVo,
                                         int pageNum,
                                         int pageSize) throws ProjectException{
        try {
            Page<Order> page = orderService.findOrderVoPage(orderVo, pageNum, pageSize);
            Page<OrderVo> pageVo = new Page<>();
            BeanConv.toBean(page,pageVo);
            //结果集转换
            List<Order> orderList = page.getRecords();
            List<OrderVo> orderVoList = BeanConv.toBeanList(orderList,OrderVo.class);
            //处理订单项
            if (!EmptyUtil.isNullOrEmpty(orderVoList)){
                orderVoList.forEach(n->{
                    List<OrderItem> orderItems = orderItemService.findOrderItemByOrderNo(n.getOrderNo());
                    List<OrderItemVo> orderItemVoList = BeanConv.toBeanList(orderItems, OrderItemVo.class);
                    n.setOrderItemVoStatisticsList(orderItemVoList);
                });
            }
            pageVo.setRecords(orderVoList);
            return pageVo;
        } catch (Exception e) {
            log.error("查询订单列表异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(OrderEnum.PAGE_FAIL);
        }
    }

    @Override
    @GlobalTransactional
    public OrderVo opertionToOrderItem(Long dishId,
                                       Long orderNo,
                                       String opertionType)throws ProjectException {
        //1、判定订单待支付状态才可操作
        //2、判定菜品处于起售状态才可操作
        //3、锁定订单
                //4.1、添加可核算订单项
                //4.2、移除可核算订单项
                //5、计算订单总金额
                //6、修改订单总金额
                //7、返回新订单信息

        return null;
    }

    /**
     * @description 移除订单项
     * @param dishId 菜品ID
     * @param orderNo 订单编号
     * @param atomicLong 原子计数器
     * @return
     */
    private void removeToOrderItem(Long dishId, Long orderNo, RAtomicLong atomicLong)  {
        //1、修改订单项
        //2、菜品库存
        //3、添加缓存中的库存数量
    }

    /**
     * @description 添加可核算订单项
     * @param dishId 菜品ID
     * @param orderNo 订单编号
     * @param atomicLong 原子计数器
     * @return
     */
    private void addToOrderItem(Long dishId, Long orderNo, RAtomicLong atomicLong)  {
        //1、如果库存够，redis减库存
            //2、修改可核算订单项
            //3、减菜品库存
                //4、减菜品库存失败，归还redis菜品库存
            //5、redis库存不足，虽然可以不处理，但建议还是做归还库存
    }


    @Override
    @GlobalTransactional
    public TradingVo handleTrading(OrderVo orderVo) throws ProjectException{
        //1、根据订单生成交易单
        //2、调用统一收单线下交易预创建
        //3、结算后桌台状态修改：开桌-->空闲
            //4、修改桌台状态
        return null;
    }

    @Override
    public String queryQrCode(OrderVo orderVo) {
        return nativePayFace.queryQrCodeUrl(orderVo);
    }

    @Override
    @GlobalTransactional
    public Boolean handleTradingRefund(OrderVo orderVo)throws ProjectException {
        //1、获取当前交易单信息
        //2、退款收款人不为同一人，退款操作拒绝
        //3、当前交易单信息，退款操作拒绝
        //4、根据订单生成交易单
        //5、执行统一收单交易退款接口
        return null;
    }

    @Override
    @GlobalTransactional
    public Boolean handleTradingMd(OrderVo orderVo) throws ProjectException {
        //1、根据订单生成交易单
        TradingVo tradingVo = tradingConvertor(orderVo);
        if (EmptyUtil.isNullOrEmpty(tradingVo)){
            throw new ProjectException(OrderEnum.FAIL);
        }
        //2、调用免单接口
        TradingVo tradingVoResult = creditPayFace.createCreditMdTrading(tradingVo);
        if (EmptyUtil.isNullOrEmpty(tradingVoResult)){
            throw new ProjectException(OrderEnum.FAIL);
        }
        Boolean flag = true;
        //4、结算后桌台状态修改：开桌-->空闲
        if (EmptyUtil.isNullOrEmpty(tradingVoResult)){
            throw new ProjectException(OrderEnum.FAIL);
        }else {
            TableVo tableVo = TableVo.builder()
                    .id(orderVo.getTableId())
                    .tableStatus(SuperConstant.FREE).build();
            //4、修改桌台状态
            flag = tableFace.updateTable(tableVo);
            if (!flag){
                throw new ProjectException(OrderEnum.FAIL);
            }
        }
        return flag;
    }

    @Override
    @GlobalTransactional
    public Boolean handleTradingGz(OrderVo orderVo) {
        //1、根据订单生成交易单
        TradingVo tradingVo = tradingConvertor(orderVo);
        if (EmptyUtil.isNullOrEmpty(tradingVo)){
            throw new ProjectException(OrderEnum.FAIL);
        }
        //2、调用挂账接口
        TradingVo tradingVoResult = creditPayFace.createCreditGzTrading(tradingVo);
        if (EmptyUtil.isNullOrEmpty(tradingVoResult)){
            throw new ProjectException(OrderEnum.FAIL);
        }
        Boolean flag = true;
        //3、结算后桌台状态修改：开桌-->空闲
        if (EmptyUtil.isNullOrEmpty(tradingVoResult)){
            throw new ProjectException(OrderEnum.FAIL);
        }else {
            TableVo tableVo = TableVo.builder()
                    .id(orderVo.getTableId())
                    .tableStatus(SuperConstant.FREE).build();
            //4、修改桌台状态
            flag = tableFace.updateTable(tableVo);
            if (!flag){
                throw new ProjectException(OrderEnum.FAIL);
            }
        }
        return flag;
    }

    /***
     * @description 转换订单为交易单
     * @param orderVo 订单信息
     * @return: com.itheima.restkeeper.req.TradingVo
     * TODO 策略模式优化
     */
    private TradingVo tradingConvertor(OrderVo orderVo)throws ProjectException {
        //付款动作
        if (orderVo.getTradingType().equals(TradingConstant.TRADING_TYPE_FK)){
            return payTradingVo(orderVo);
        //退款动作
        }else if (orderVo.getTradingType().equals(TradingConstant.TRADING_TYPE_TK)){
            return refundTradingVo(orderVo);
        //免单动作
        }else if (orderVo.getTradingType().equals(TradingConstant.TRADING_TYPE_MD)) {
            return freeChargeTradingVo(orderVo);
        //挂账动作
        }else if (orderVo.getTradingType().equals(TradingConstant.TRADING_TYPE_GZ)) {
            return creditTradingVo(orderVo);
        }else {
            throw new ProjectException(TradingEnum.TRADING_TYPE_FAIL);
        }
    }

    /***
     * @description 付款动作
     * @param orderVo
     * @return TradingVo 交易单
     */
    private TradingVo payTradingVo(OrderVo orderVo){
        //应付总金额
        //打折
        //优惠
        //计算实付总金额
        //实付金额
        //收银人id
        //收银人名称
        //支付渠道
        //支付类型
        //订单状态：现金结算：YJS,支付宝，微信结算：FKZ
        //构建交易单
        return null;
    }

    /***
     * @description 退款动作
     * @param orderVo
     * @return TradingVo 交易单
     */
    private TradingVo refundTradingVo(OrderVo orderVo){
        //修改退款金额
        //有退款行为
        //构建交易单
        return null;
    }

    /***
     * @description 免单动作
     * @param orderVo
     * @return TradingVo 交易单
     */
    private TradingVo freeChargeTradingVo(OrderVo orderVo){
        Order order = orderService.getById(orderVo.getId());
        CustomerVo customerVo = customerFace.findCustomerByCustomerId(orderVo.getBuyerId());
        //支付渠道
        order.setTradingChannel(orderVo.getTradingChannel());
        //支付类型
        order.setTradingType(orderVo.getTradingType());
        //订单状态：MD
        order.setOrderState(TradingConstant.MD);
        //收银人id
        order.setCashierId(orderVo.getCashierId());
        //收银人名称
        order.setCashierName(orderVo.getCashierName());
        //结算保存订单信息
        boolean flag = orderService.updateById(order);
        //构建交易单
        if (flag){
            TradingVo tradingVo = TradingVo.builder()
                .tradingAmount(order.getPayableAmountSum())
                .tradingChannel(orderVo.getTradingChannel())
                .tradingType(orderVo.getTradingType())
                .tradingState(order.getOrderState())
                .enterpriseId(orderVo.getEnterpriseId())
                .storeId(orderVo.getTableId())
                .payerId(customerVo.getId())
                .payerName(customerVo.getMobil())
                .payeeId(orderVo.getCashierId())
                .payeeName(orderVo.getCashierName())
                .productOrderNo(orderVo.getOrderNo())
                .memo(orderVo.getTableName()+":"+orderVo.getOrderNo())
                .build();
            return tradingVo;
        }else {
            throw new ProjectException(OrderEnum.UPDATE_FAIL);
        }
    }

    /***
     * @description 挂账动作
     * @param orderVo
     * @return TradingVo 交易单
     */
    private TradingVo creditTradingVo(OrderVo orderVo){
        Order order = orderService.getById(orderVo.getId());
        CustomerVo customerVo = customerFace.findCustomerByCustomerId(orderVo.getBuyerId());
        //支付渠道
        order.setTradingChannel(orderVo.getTradingChannel());
        //支付类型
        order.setTradingType(orderVo.getTradingType());
        //订单状态：MD
        order.setOrderState(TradingConstant.GZ);
        //收银人id
        order.setCashierId(orderVo.getCashierId());
        //收银人名称
        order.setCashierName(orderVo.getCashierName());
        //结算保存订单信息
        boolean flag = orderService.updateById(order);
        //构建交易单
        if (flag){
            TradingVo tradingVo = TradingVo.builder()
                .tradingAmount(order.getPayableAmountSum())
                .tradingChannel(orderVo.getTradingChannel())
                .tradingType(orderVo.getTradingType())
                .tradingState(order.getOrderState())
                .enterpriseId(orderVo.getEnterpriseId())
                .storeId(orderVo.getTableId())
                .payerId(customerVo.getId())
                .payerName(customerVo.getMobil())
                .payeeId(orderVo.getCashierId())
                .payeeName(orderVo.getCashierName())
                .productOrderNo(orderVo.getOrderNo())
                .memo(orderVo.getTableName()+":"+orderVo.getOrderNo())
                .build();
            return tradingVo;
        }else {
            throw new ProjectException(OrderEnum.UPDATE_FAIL);
        }
    }

    @Override
    public OrderVo findOrderVoPaid(Long orderNo)throws ProjectException {
        return orderService.findOrderVoPaid(orderNo);
    }

    @Override
    public List<OrderVo> findOrderVoPaying() throws ProjectException{
        return orderService.findOrderVoPaying();
    }

    @Override
    @GlobalTransactional
    public Boolean synchTradingState(Long orderNo, String tradingState)throws ProjectException {
        OrderVo orderVo = orderService.findOrderByOrderNo(orderNo);
        orderVo.setOrderState(tradingState);
        return orderService.saveOrUpdate(BeanConv.toBean(orderVo,Order.class));
    }

}
