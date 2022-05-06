create table if not exists  dws.dws_drama_user_d(
user_id                     string comment '剧本杀玩家id或者设备id',
pv_count                    bigint comment '访问统计',
session_cnt                 bigint comment '会话次数',
order_count                 bigint comment '下单个数',
pay_count                   bigint comment '支付订单数',
pay_coupon_price            decimal(20, 2) comment '支付优惠券金额',
pay_total_price             decimal(20, 2) comment '支付金额',
received_count              bigint comment '核销订单数',
received_coupon_price       decimal(20, 2) comment '核销优惠券金额',
received_total_price        decimal(20, 2) comment '订单销售额(核销)',
refund_count                bigint comment '退款次数',
refund_amount               decimal(20, 2) comment '退款金额',
wish_count                  bigint comment '想玩剧本的个数',
wish_spu_detail             array<struct<sku_id:string,sku_name:string>> COMMENT '想玩spu的统计'
post_count                  bigint comment '发帖个数'
)