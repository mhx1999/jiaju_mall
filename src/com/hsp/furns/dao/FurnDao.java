package com.hsp.furns.dao;

import com.hsp.furns.entity.Furn;

import java.util.List;

/**
 * @author 马海鑫
 * @version 1.0
 */
public interface FurnDao {

/*
返回所有家居信息集合，后面再考虑分页
 */
    public List<Furn> queryFurns();



    //添加家居,返回1表示成功，返回0表示失败
    public int addFurn(Furn furn);


    //通过id删除家居,返回1表示成功，返回0表示失败
    public int deleteFurnById(Integer id);


    //通过id查询家居,返回Furn对象


    public  Furn queryFurnById(Integer id);


    //通过传入一个furn对象 修改家居,返回1表示成功，返回0表示失败
    //updateFurn


    public  int updateFurn(Furn furn);


    //page的哪些属性是可以直接从数据库中获取，就把这个任务放在Dao层   totalRow   items
//把  totalRow 共有多少条记录  items 填充任务放在Dao层
    public int getTotalRow();

//获取当前页要显示的数据
    //begin:表示从第几条记录开始获取
    //pageSize 表示取出多少记录记录
public List<Furn> getPageItems(int begin,int pageSize);


//通过模糊家居名返回该家居在第几页
    public int getPageTotalRowByName(String name);

//通过模糊家居名返回 数据
public List<Furn>   getPageItemsByName(String name,int begin,int pageSize);

}
