package com.hsp.furns.service.impl;

import com.hsp.furns.dao.FurnDao;
import com.hsp.furns.dao.impl.FurnDaoImpl;
import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Page;
import com.hsp.furns.service.FurnService;

import java.util.List;

/**
 * @author 马海鑫
 * @version 1.0
 */
public class FurnServiceImpl implements FurnService {

//定义属性 FurnDao对象
   private FurnDao furnDao =  new FurnDaoImpl();
    @Override
    public List<Furn> queryFurns() {
        return furnDao.queryFurns();
    }

    @Override
    public int add(Furn furn) {
        return furnDao.addFurn(furn);
    }

    @Override
    public int deleteFurnById(Integer id) {
        return furnDao.deleteFurnById(id);
    }


    @Override
    public Furn queryFurnById(Integer id) {
        return furnDao.queryFurnById(id);
    }


    @Override
    public int updateFurn(Furn furn) {
        return furnDao.updateFurn(furn);
    }


    @Override
    public Page<Furn> page(int pageNo, int pageSize) {


        //先创建一个Page对象，然后根据实际情况填充属性
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDao.getTotalRow();
        page.setTotalRow(totalRow);

        int pageTotalCount = totalRow/pageSize;
        if(totalRow % pageSize >0){
            pageTotalCount += 1;
        }

        page.setPageTotalCount(pageTotalCount);


        int begin = (pageNo-1)*pageSize;

        List<Furn> pageItems = furnDao.getPageItems(begin, pageSize);

        page.setItems(pageItems);

//这里还有url分页导航的字符串，分页导航时再加入
        return page;
    }


    //通过传入一个模糊的家居名  和 int pageNo, int pageSize  ，返回page对象
    @Override
    public Page<Furn> pageByName(String name,int pageNo, int pageSize) {



        //先创建一个Page对象，然后根据实际情况填充属性
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDao.getPageTotalRowByName(name);
        page.setTotalRow(totalRow);

        int pageTotalCount = totalRow/pageSize;
        if(totalRow % pageSize >0){
            pageTotalCount += 1;
        }

        page.setPageTotalCount(pageTotalCount);


        int begin = (pageNo-1)*pageSize;

        List<Furn> pageItems = furnDao.getPageItemsByName(name,begin,pageSize);

        page.setItems(pageItems);

//这里还有url分页导航的字符串，分页导航时再加入
        return page;

    }
}
