package com.hsp.furns.service;

import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Page;

import java.util.List;

/**
 * @author 马海鑫
 * @version 1.0
 */
public interface FurnService {



    /*
返回所有家居信息集合，后面再考虑分页
 */
    public List<Furn> queryFurns();

    public int add(Furn furn);

    public int deleteFurnById(Integer id);



    //通过id查询家居,返回Furn对象
    public  Furn queryFurnById(Integer id);




    //通过传入一个furn对象 修改家居,返回1表示成功，返回0表示失败
      public  int updateFurn(Furn furn);


//根据传入的pageNo和pageSize返回对象的page对象
    public Page<Furn> page(int pageNo,int pageSize);

//通过传入一个模糊的家居名  和 int pageNo, int pageSize  ，返回page对象
    public Page<Furn> pageByName(String name,int pageNo, int pageSize);
}
