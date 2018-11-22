package cn.itcast.erp.dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.entity.Goods;
/**
 * 商品数据访问类
 * @author Administrator
 *
 */
public class GoodsDao extends BaseDao<Goods> implements IGoodsDao {

	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Goods goods1,Goods goods2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Goods.class);
		if(goods1!=null){
			if(null != goods1.getName() && goods1.getName().trim().length()>0){
				dc.add(Restrictions.like("name", goods1.getName(), MatchMode.ANYWHERE));
			}
			if(null != goods1.getOrigin() && goods1.getOrigin().trim().length()>0){
				dc.add(Restrictions.like("origin", goods1.getOrigin(), MatchMode.ANYWHERE));
			}
			if(null != goods1.getProducer() && goods1.getProducer().trim().length()>0){
				dc.add(Restrictions.like("producer", goods1.getProducer(), MatchMode.ANYWHERE));
			}
			if(null != goods1.getUnit() && goods1.getUnit().trim().length()>0){
				dc.add(Restrictions.like("unit", goods1.getUnit(), MatchMode.ANYWHERE));
			}
			//根据商品类型查询
			if(null != goods1.getGoodstype()/* && goods1.getGoodstype().getName() != null*/){
				dc.add(Restrictions.eq("goodstype", goods1.getGoodstype()));
			}
			//进货价格范围查询
			if(null != goods1.getInprice()) {
				dc.add(Restrictions.ge("inprice",goods1.getInprice()));
			}
			//销售价格范围查询
			if(null != goods1.getOutprice()) {
				dc.add(Restrictions.ge("outprice",goods1.getOutprice()));
			}

		}
		if(null != goods2) {
			if(null != goods2.getInprice()) {
				dc.add(Restrictions.le("inprice",goods2.getInprice()));
			}
			if(null != goods2.getOutprice()) {
				dc.add(Restrictions.le("outprice",goods2.getOutprice()));
			}
		}
		return dc;
	}

}
