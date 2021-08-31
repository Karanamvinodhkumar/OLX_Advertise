package com.advertise.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.advertise.entity.AdvertiseEntity;

@Repository
@Transactional
public class CustomAdvertiseRepoImpl implements CustomAdvertiseRepo {
	
	@Autowired
    EntityManager entityManager;

	@Override
	public List<AdvertiseEntity> filterandsearch(int page, int size, String title, Integer category, Integer status,
			Double price, String dateCondition, String onDate, String fromDate, String toDate) {
		StringBuilder statusQuery = new StringBuilder();

        statusQuery.append("");

        if (title != null){
            statusQuery.append(" AND advertise.title like '"+title+"' ");
        }
        if (category != null){
            statusQuery.append(" AND advertise.category = "+category+"");
        }
        if (status != null){
            statusQuery.append(" AND advertise.status = "+status+"");
        }
        if (price != null){
            statusQuery.append(" AND advertise.price = "+price+"");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("BETWEEN") && fromDate !=null && toDate !=null){
            statusQuery.append(" AND advertise.createddate BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("LESS THAN")  && toDate !=null){
            statusQuery.append(" AND advertise.createddate <= '"+toDate+"' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("GREATER THAN")  && fromDate !=null){
            statusQuery.append(" AND advertise.createddate >= '"+fromDate+"' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("EQUAL")  && onDate !=null){
            statusQuery.append(" AND advertise.createddate BETWEEN '"+onDate+"' AND '"+onDate+"' ");
        }

        javax.persistence.Query query = entityManager.createQuery(" SELECT advertise FROM AdvertiseEntity  advertise WHERE advertise.id != null "+
                statusQuery.toString(),AdvertiseEntity.class);

        query.setFirstResult((page) * size);
        query.setMaxResults(size);

        return  query.getResultList();
	}
	

}
