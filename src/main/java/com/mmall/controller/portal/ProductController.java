package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import com.mmall.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by snowWave
 *
 */

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    /**
     * 前台获取商品详情
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    //RESTful风格适用于查，不合适增删改，因为 增删查要传太多参数,Restful本身是资源定位的一种方式
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId){
        return iProductService.getProductDetail(productId);
    }


    /**
     * 前台搜索获取商品列表+分页+动态排序
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }

    //http://www.happymmall.com/product/手机/100012/1/10/price_asc
    @RequestMapping(value = "/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "keyword")String keyword,
                                                @PathVariable(value = "categoryId")Integer categoryId,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize,
                                                @PathVariable(value = "orderBy") String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        if(StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }




    //http://www.happymmall.com/product/keyword/手机/1/10/price_asc
    @RequestMapping(value = "/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "keyword")String keyword,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize,
                                                @PathVariable(value = "orderBy") String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        if(StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword,null,pageNum,pageSize,orderBy);
    }


    //http://www.happymmall.com/product/category/100012/1/10/price_asc
    @RequestMapping(value = "/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "categoryId")Integer categoryId,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize,
                                                @PathVariable(value = "orderBy") String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        if(StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory("",categoryId,pageNum,pageSize,orderBy);
    }



}
