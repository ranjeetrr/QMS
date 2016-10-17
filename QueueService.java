package com.retail.springservice.dao;

import java.util.List;


import com.retail.springservice.model.ExistingCustomer;
import com.retail.springservice.model.History;
import com.retail.springservice.model.Queue;
import com.retail.springservice.model.SalesRepDetails;


public interface QueueService 
{
	public List<Queue> getAllCustomers() throws Exception;
	
	public List<SalesRepDetails> getAllRep() throws Exception;
	
	public List<Queue> getCustomerByqueueId(int queueId) throws Exception;
	public List<Queue> deleteCustomerByqueueId(int queueId)throws Exception;
	public List<Queue> saveQueue(Queue queue) throws Exception;
	public List<Queue> getRepQueue(String sales_rep_assigned)throws Exception;//for home page of specific rep 
	
	public List<SalesRepDetails> repLoginCheck(String email,String pwd)throws Exception;
	
	public List<Queue> assistNextCustomer(int queueId, String repName)throws Exception;
	public List<Queue> moveToQueueLast(int queueId)throws Exception;
	public void setStillBrowsing(int queueId)throws Exception;
	public List<Queue> getAndSetNewRep( String repName,int queueId) throws Exception;
	public List<Queue> getRepNext(String sales_rep_assigned)throws Exception;
	
	public List<History> getHistory(String date)throws Exception;

	public List<SalesRepDetails> saveRepInfo(SalesRepDetails rep)throws Exception;
	
	public List<SalesRepDetails> RemoveRep(String email)throws Exception;

	public List<ExistingCustomer> getExistingCustomer(String phone);
}
