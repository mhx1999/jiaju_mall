package com.hsp.furns.dao.impl;

import com.hsp.furns.dao.BasicDAO;
import com.hsp.furns.dao.FurnDao;
import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Manager;

import java.util.List;

/**
 * @author 马海鑫
 * @version 1.0
 */
public class FurnDaoImpl extends BasicDAO implements FurnDao {



    @Override
    public List<Furn> queryFurns() {


      String sql =" SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath FROM furn ";


        return  queryMulti(sql,Furn.class);
    }


    @Override
    public int addFurn(Furn furn) {

        String sql =  "INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`) VALUES(NULL,?,?,?,?,?,?)";

        int update = update(sql, furn.getName(), furn.getMaker(),furn.getPrice(),furn.getSales(),furn.getStock(),furn.getImgPath());
        return update;

    }

    //通过id删除家居,返回1表示成功，返回0表示失败

    @Override
    public int deleteFurnById(Integer id) {

        String sql = "DELETE FROM furn WHERE `id` = ?";
        int update = update(sql, id);

        return update;
    }



    //通过id查询家居,返回Furn对象


    @Override
    public Furn queryFurnById(Integer id) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath FROM furn WHERE `id` = ?";
        Object object = querySingle(sql, Furn.class, id);
        Furn furn = (Furn)object;
        return furn;
    }



    //通过id修改家居,返回1表示成功，返回0表示失败
    //updateFurn


    @Override
    public int updateFurn(Furn furn) {

        String sql = "UPDATE furn SET `name`=?,`maker`=?,`price`=?,`sales`=?,`stock`=? WHERE `id`=?";
        int update = update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getId());
        return update;
    }




    //
    @Override
    public int getTotalRow() {
        String sql = "SELECT COUNT(*) FROM `furn`";

        return ((Number)queryScalar(sql)).intValue();
    }


    @Override
    public List<Furn> getPageItems(int begin,int pageSize) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath FROM furn LIMIT ?,?";

        return queryMulti(sql,Furn.class,begin,pageSize);
    }

    //通过模糊家居名返回该家居在第几页
    @Override
    public int getPageTotalRowByName(String name) {
        String sql = "SELECT COUNT(*) FROM `furn` WHERE `name` LIKE ?";

return ((Number)queryScalar(sql,"%"+name+"%")).intValue();

    }

    //通过模糊家居名返回 数据
    @Override
    public List<Furn> getPageItemsByName(String name,int begin,int pageSize) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath FROM furn WHERE `name` LIKE ? LIMIT ?,?";

        return queryMulti(sql,Furn.class,"%"+name+"%",begin,pageSize);

    }
}
