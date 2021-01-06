package com.qiu.service;
import com.qiu.entity.Product;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/13 14:47
 * @description 商品信息 service层
 */
public interface ProductService {
    //通过ID查询
    Product selectById(Integer productId);
    //通过 商品编号 查询
    Product selectByKey(String productNo);
    //通过 商品编号 查Id
    Integer selectIdByKey(String productNo);
    //查询记录个数
    Integer selectCount();
    //判断 商品编号 是否存在
    Boolean existsWithPrimaryKey(String productNo);
    //判断商品类别是否存在
    Boolean existsProductType(String productType);
    //判断商品品牌是否存在
    Boolean existsProductBrand(String productBrand);
    //查询所有
    List<Product> selectAll();
    //查询所有上架商品，并按开售时间排序
    List<Product> selectAllSale();
    //根据商品分类查询,并通过开售时间优先排序
    List<Map<String,Object>> selectAllByType();
    //插入一条数据
    Boolean insertData(Product product);
    //通过ID更新
    Boolean updateById(Product product);
    //通过ID删除
    Boolean deleteById(Integer productId);
    //根据商品名称模糊查询
    List<Product> selectAllLikeName(String keyWord);
    //根据商品类型模糊查询
    List<Product> selectAllLikeType(String keyWord);
    //根据商品品牌模糊查询
    List<Product> selectAllLikeBrand(String keyWord);
    //查询商品数量、商品上架数量、商品下架数量和库存紧张数量
    Map<String,Object> productOverview();
}
