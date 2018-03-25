# spring-boot
使用spring-boot + spring-data-jpa + h2database 开发的样例

   里面的功能实现主要是实现查询功能：
        数据特点是 树形结构
                                 cust_info (root)
                         account_info           cust_detail_info    --- level one 
                     bank_card_info                                 --- level two
        查询特点是：
                 可以根据查询条件，查询一条树枝
