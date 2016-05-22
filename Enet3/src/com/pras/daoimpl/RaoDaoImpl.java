package com.pras.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.pras.dao.RaoDao;
import com.pras.dto.BranchDto;
import com.pras.dto.LineItemDto;
import com.pras.dto.RaoDto;
import com.pras.dtohelper.BranchDtoHelper;
import com.pras.dtohelper.LineItemDtoHelper;
import com.pras.dtohelper.RaoDtoHelper;
import com.pras.model.Branch;
import com.pras.model.Customer;
import com.pras.model.LineItem;
import com.pras.model.Rao;
import com.pras.model.User;
import com.pras.model.Website;
import com.pras.util.HibernateUtil;

public class RaoDaoImpl implements RaoDao{

	@Override
	public void addRao(RaoDto dto) {
		// TODO Auto-generated method stub
		//Get Session
        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        Rao rao = new Rao();
        rao.setBranch((Branch) session.get(Branch.class, dto.getBranchId()));
        rao.setCustomer((Customer) session.get(Customer.class, dto.getCustomerId()));
        rao.setDescription(dto.getDescription());
        rao.setUser((User) session.get(User.class, dto.getUserId()));
        rao.setWebsite((Website) session.get(Website.class, dto.getWebsiteId()));
        List<LineItemDto> items = dto.getLineItems();
        List<LineItem> it = new ArrayList<>();
        for(int i=0;i<items.size();i++) {
        	it.add(LineItemDtoHelper.getEntityFromDto(items.get(i)));
        }
        rao.setItems(it);
        //Save the Model object
        session.save(rao);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void removeRao(long id) {
		// TODO Auto-generated method stub
		//Get Session
        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        Rao rao = (Rao) session.get(Rao.class, id);
        //Save the Model object
        session.delete(rao);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void editRao(long id, RaoDto dto) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        Rao rao = (Rao) session.get(Rao.class, id);
        rao.setDescription(dto.getDescription());
        rao.setBranch((Branch) session.get(Branch.class, dto.getBranchId()));
        rao.setCustomer((Customer) session.get(Customer.class, dto.getCustomerId()));
        rao.setUser((User) session.get(User.class, dto.getUserId()));
        rao.setWebsite((Website) session.get(Website.class, dto.getWebsiteId()));
        
        session.saveOrUpdate(rao);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public List<RaoDto> getAll() {
		Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        List<Rao> raos = session.createCriteria(Rao.class).list();
		List<RaoDto> dtos = new ArrayList<RaoDto>();
		RaoDto dto = null;
		for(int i=0;i<raos.size();i++) {
			dto = RaoDtoHelper.getDtoFromEntity(raos.get(i));
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public RaoDto getRao(long id) {
		Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        Rao dao = (Rao) session.get(Rao.class, id);
        return RaoDtoHelper.getDtoFromEntity(dao);
	}

}