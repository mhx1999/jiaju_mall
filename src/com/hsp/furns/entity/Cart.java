package com.hsp.furns.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @author 马海鑫
 * @version 1.0
 *
 * Cart 包含多个CartItem
 */
public class Cart {

    public Cart() {
    }


    //使用HashMap保存多个CartItem
    private  HashMap<Integer,CartItem> items = new HashMap<>();




    //clear 清空购物车的方法

    public void clear(){

//        Set<Integer> keySet = items.keySet();
//        for(Integer key:keySet){
//            items.remove(key);
//        }
     items.clear();
    }

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    //购物车商品的总数量

    public Integer getTotalCount() {
        int totalCount=0;

        //遍历items 统计totalCount
        Set<Integer> keys = items.keySet();
        for(Integer id: keys){
            totalCount += ((CartItem)items.get(id)).getCount();
        }

        return totalCount;
    }


    public double getTotalPrice(){

double totalPrice=0;

        Collection<CartItem> values = items.values();
        for(CartItem cartItem :values){

            double value = cartItem.getTotalPrice().doubleValue();

totalPrice += value;

        }


        return totalPrice;
    }

    //添加家居包含多个CartItem到cart
    public void addItem(CartItem cartItem){

        CartItem item = items.get(cartItem.getId());
        if(null == item){

            //说明当前购物车还没有这个cartItem
            items.put(cartItem.getId(),cartItem);
        }else {

            item.setCount(item.getCount()+1);
           item.setTotalPrice(item.getTotalPrice().add(item.getPrice()));
        }
    }


    //删除CartItem中的莫格cart
    public void deleteItem(int id){

        CartItem item = items.get(id);

        if(null != item){

         items.remove(id);

        }

    }








    //修改指定的CartItem的数量和总价
public void updateCount(int id,int count){


    CartItem item = items.get(id);
    if(null != item){

        item.setCount(count);
        item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

    }




}






    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }




}
