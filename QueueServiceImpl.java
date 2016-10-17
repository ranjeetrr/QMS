package com.retail.springservice.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

import com.retail.springservice.model.ExistingCustomer;
import com.retail.springservice.model.History;
import com.retail.springservice.model.Queue;
import com.retail.springservice.model.SalesRepDetails;
import com.retail.springservice.utility.DBUtility;
import com.retail.springservice.dao.QueueService;


public class QueueServiceImpl implements QueueService{

	
	 private Connection connection;
	 Logger log=Logger.getLogger(ClassName.class.getName());
	 public QueueServiceImpl()
	 {
		 connection = DBUtility.getConnection();
	 }
  
	 @Override
	 public List<Queue> getAllCustomers() throws Exception
	 {
		 List<Queue> queues = new ArrayList<Queue>();
		 try 
		 {
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery("select * from queue_details WHERE STATUS<>'CLOSE' ORDER BY queueid ASC");
			while (rs.next()) {
		    
			Queue queue = new Queue();
			queue.setQueueId(rs.getInt("queueId"));
			queue.setfName(rs.getString("fName"));
			queue.setlName(rs.getString("lName"));
			queue.setPhone(rs.getString("phone"));
			queue.setReason(rs.getString("reason"));
			queue.setType(rs.getString("type"));
			queue.setqComment(rs.getString("qComment"));
			queue.setqDate(rs.getTimestamp("qDate"));
			queue.setSALES_REP_ASSIGNED(rs.getString("SALES_REP_ASSIGNED"));
			queue.setStatus(rs.getString("STATUS"));
			queues.add(queue);
			
			}
			connection.commit();
			statement.close();
		  }catch (SQLException e) {
			  e.printStackTrace();
		  	}
		  
		  return queues;
	 }
	 
	 @Override
	public List<SalesRepDetails> getAllRep() throws Exception {
			
		 List<SalesRepDetails> reps = new ArrayList<SalesRepDetails>();
		 try 
		 {
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery("select * from SALES_REP_AVAILABILITY");
			while (rs.next()) {
		    
			SalesRepDetails rep = new SalesRepDetails();
			
			rep.setSales_rep_id(rs.getInt("SALES_REP_ID"));
			rep.setFirst_name(rs.getString("FIRST_NAME"));
			rep.setLast_name(rs.getString("LAST_NAME"));
			rep.setContact_no(rs.getString("CONTACT_NO"));
			rep.setEmail(rs.getString("EMAIL"));
			rep.setPassword(rs.getString("PASSWORD"));
			rep.setDesig(rs.getString("DESIG"));
			
			reps.add(rep);
			
			}
			connection.commit();
			statement.close();
		  }catch (SQLException e) {
			  e.printStackTrace();
		  	}
		  return reps;
		}
 
	 @Override 
	 public List<Queue> getCustomerByqueueId(int queueId) throws Exception
	 {	 
		 List<Queue> queues = new ArrayList<Queue>();
		
		 try 
		 {
		     PreparedStatement preparedStatement = connection.
		     prepareStatement("select * from queue_details where QUEUEID=?");
		     preparedStatement.setInt(1, queueId);
		     ResultSet rs = preparedStatement.executeQuery();
		   
		     while (rs.next()) {
 
		    	Queue queue = new Queue();
			   	queue.setQueueId(rs.getInt("queueId"));
				queue.setfName(rs.getString("fName"));
				queue.setlName(rs.getString("lName"));
				queue.setPhone(rs.getString("phone"));
				queue.setReason(rs.getString("reason"));
				queue.setType(rs.getString("type"));
				queue.setqComment(rs.getString("qComment"));
				queue.setqDate(rs.getTimestamp("qDate"));
				queue.setSALES_REP_ASSIGNED(rs.getString("SALES_REP_ASSIGNED"));
				queue.setStatus(rs.getString("STATUS"));
				queues.add(queue);
		     }
		     connection.commit();
				preparedStatement.close();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return queues;
	 }

@Override
public List<Queue> deleteCustomerByqueueId(int queueId) throws Exception 
{
		   
	try {
			   CallableStatement cs=connection.prepareCall("{call updatedQueueData(?)}");
			   cs.setInt(1, queueId);
			   cs.executeUpdate();
			   //System.out.println("Result of callable statement :"+x);
			  
			   connection.commit();
				cs.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	return getAllCustomers();
}

@Override
public List<Queue> saveQueue(Queue queue) throws Exception {

	
	try {
		CallableStatement ps=connection.prepareCall("{call storeQueueData(?,?,?,?,?,?,?,?)}");
		
		ps.setString(1, queue.getfName());
		ps.setString(2, queue.getlName());
		ps.setString(3, queue.getPhone()); 
		ps.setString(4, queue.getType());
		ps.setString(5, queue.getReason());
		ps.setString(6, queue.getqComment());
		ps.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
		ps.setString(8, queue.getSALES_REP_ASSIGNED());
		
		ps.executeUpdate();
		
		//System.out.println("Customer Object " + queue);

		//System.out.println("Connection Object " + connection);
		//System.out.println(x);
		connection.commit();
		ps.close();
		
	} catch (Exception e) {
		
		e.printStackTrace();}
	
	return getAllCustomers();
	
	}
	
@Override
public List<SalesRepDetails> repLoginCheck(String email, String pwd) 
{

	List<SalesRepDetails> queues = new ArrayList<SalesRepDetails>();
	ResultSet rs=null;
	try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from sales_rep_availability where  email=? and password =?");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,pwd);
			rs = preparedStatement.executeQuery();
			//System.out.println(connection);
			//System.out.println("rs= " + rs);
			while (rs.next()) 
			{
				SalesRepDetails queue= new SalesRepDetails();
				queue.setSales_rep_id(rs.getInt("Sales_rep_id"));
				queue.setFirst_name(rs.getString("First_name"));
				queue.setLast_name(rs.getString("Last_name"));
				queue.setContact_no(rs.getString("Contact_no"));
				queue.setEmail(rs.getString("Email"));
				queue.setPassword(rs.getString("Password"));
				queue.setDesig(rs.getString("DESIG"));
				queues.add(queue);
				
			}
			connection.commit();
			preparedStatement.close();
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		//System.out.println(queues);
		return queues;
	}

	@Override
	public List<Queue> getRepQueue(String sales_rep_assigned) throws Exception 
	{
		
		List<Queue> queues=new ArrayList<Queue>();
		ResultSet rs=null;
		
		  try 
		  {
		     PreparedStatement preparedStatement = connection.prepareStatement("select * from queue_details where sales_rep_assigned=? AND STATUS='ASSISTING' order by queueid");
		     preparedStatement.setString(1,sales_rep_assigned);
		     rs = preparedStatement.executeQuery();
		     while(rs.next()) 
		     {
		    	Queue queue = new Queue();
		 	   	queue.setQueueId(rs.getInt("queueId"));
		 		queue.setfName(rs.getString("fName"));
		 		queue.setlName(rs.getString("lName"));
		 		queue.setPhone(rs.getString("phone"));
		 		queue.setReason(rs.getString("reason"));
		 		queue.setType(rs.getString("type"));
		 		queue.setqComment(rs.getString("qComment"));
		 		queue.setqDate(rs.getTimestamp("qDate"));
		 		queue.setSALES_REP_ASSIGNED(rs.getString("SALES_REP_ASSIGNED"));
		 		queue.setStatus(rs.getString("STATUS"));
		 		queues.add(queue);
		     }
		     connection.commit();
				preparedStatement.close();
		   } catch (SQLException e) {
		    e.printStackTrace();
		   }
		   return queues;
	 }

	@Override
	public List<Queue> getRepNext(String sales_rep_assigned) throws Exception 
	{
		
		List<Queue> queues=new ArrayList<Queue>();
		ResultSet rs=null;
		
		  try 
		  {
		     PreparedStatement preparedStatement = connection.prepareStatement("select * from queue_details where sales_rep_assigned IN ('NEXT AVAILABLE',?) AND STATUS NOT IN ('CLOSE','ASSISTING') order by queueid");
		     preparedStatement.setString(1,sales_rep_assigned);
		     rs = preparedStatement.executeQuery();
		     while(rs.next()) 
		     {
		    	Queue queue = new Queue();
		 	   	queue.setQueueId(rs.getInt("queueId"));
		 		queue.setfName(rs.getString("fName"));
		 		queue.setlName(rs.getString("lName"));
		 		queue.setPhone(rs.getString("phone"));
		 		queue.setReason(rs.getString("reason"));
		 		queue.setType(rs.getString("type"));
		 		queue.setqComment(rs.getString("qComment"));
		 		queue.setqDate(rs.getTimestamp("qDate"));
		 		queue.setSALES_REP_ASSIGNED(rs.getString("SALES_REP_ASSIGNED"));
		 		queue.setStatus(rs.getString("STATUS"));
		 		queues.add(queue);
		     }
		     connection.commit();
				preparedStatement.close();
		   } catch (SQLException e) {
		    e.printStackTrace();
		   }
		   return queues;
	 }

	
	
	
	
	@Override
	public List<Queue> assistNextCustomer(int queueId,String repName)
			throws Exception {
		
		//System.out.println("Inside Impl Assist quid: "+queueId); 
		//System.out.println("Inside Impl Assist with :"+repName);
		int x;	   
		try {
				   CallableStatement cs=connection.prepareCall("{call assistQueueData(?,?)}");
				   cs.setInt(1,queueId);
				   cs.setString(2,repName);
				   x=cs.executeUpdate();
				   //System.out.println("Result of ASSIST callable statement :"+x);
				   
				   connection.commit();
					cs.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		return getAllCustomers();
	}

	@Override
	public List<Queue> moveToQueueLast(int queueId) throws Exception {
		
		//System.out.println("Inside move to last quid: "+queueId); 
		int x;	   
		try {
				   CallableStatement cs=connection.prepareCall("{call moveToLastInQueue(?)}");
				   cs.setInt(1,queueId);
				   x=cs.executeUpdate();
				   //System.out.println("Result of move to last callable statement :"+x);
				   connection.commit();
					cs.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		return getAllCustomers();
	}

	@Override
	public void setStillBrowsing(int queueId) throws Exception {
		int x;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE QUEUE_DETAILS SET STATUS='STILL BROWSING' WHERE QUEUEID=?");
			preparedStatement.setInt(1,queueId);
			x=preparedStatement.executeUpdate();
			//System.out.println("Record updated as x: "+x);
			connection.commit();
			preparedStatement.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Queue> getAndSetNewRep(String repName,int queueId) throws Exception
	{
		//System.out.println("Inside get and set new Rep, quid: "+queueId+"and rep Name to assign is :"+repName); 
		int x;	   
		try {
				   CallableStatement cs=connection.prepareCall("{call getNewRep(?,?)}");
				   cs.setString(2,repName);
				   cs.setInt(1,queueId);
				   x=cs.executeUpdate();
				   //System.out.println("Result of setting new rep is :"+x);
				   connection.commit();
					cs.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return getAllCustomers();
	}

	@Override
	public List<History> getHistory(String date) throws Exception {
		List<History> historyList = new ArrayList<History>();
		 try 
		 {
			PreparedStatement statement = connection.prepareStatement("select * from queue_details_history WHERE close_time is not null AND to_char(qdate, 'DD-MON-YYYY')=?");
			statement.setString(1,date);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
		    
			History history = new History();
			
			history.setARRIVAL_TIME(rs.getTimestamp("ARRIVAL_TIME"));
			history.setASSIST_TIME(rs.getTimestamp("ASSIST_TIME"));
			history.setNA_TIME(rs.getTimestamp("NA_TIME"));
			history.setCLOSE_TIME(rs.getTimestamp("CLOSE_TIME"));
			history.setFNAME(rs.getString("FNAME"));
			history.setLNAME(rs.getString("LNAME"));
			history.setPHONE(rs.getString("PHONE"));
			history.setQUEUEID(rs.getInt("QUEUEID"));
			history.setSALES_REP_ASSIGNED(rs.getString("SALES_REP_ASSIGNED"));
			history.setQDATE(rs.getDate("QDATE"));
			history.setREASON(rs.getString("REASON"));
			historyList.add(history);
			
			}
			connection.commit();
			statement.close();
		  }catch (SQLException e) {
			  e.printStackTrace();
		  	}
		  
		  return historyList;
	}

	@Override
	public List<SalesRepDetails> saveRepInfo(SalesRepDetails rep)
			throws Exception {
		int x;
		try {
			CallableStatement ps=connection.prepareCall("{call storeRepData(?,?,?,?,?,?)}");
			
			ps.setString(1, rep.getFirst_name());
			ps.setString(2, rep.getLast_name());
			ps.setString(3, rep.getContact_no()); 
			ps.setString(4, rep.getDesig());
			ps.setString(5, rep.getEmail());
			ps.setString(6, rep.getPassword());
			
			x=ps.executeUpdate();
			//System.out.println(x);
			connection.commit();
			ps.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();}
		
		return getAllRep();
	}

	@Override
	public List<SalesRepDetails> RemoveRep(String email) throws Exception 
	{
	

		try {
			
			PreparedStatement statement = connection.prepareStatement("delete from sales_rep_availability where email=?");
			statement.setString(1,email);
			statement.executeQuery();
			
			connection.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
		return getAllRep();

	}

	@Override
	public List<ExistingCustomer> getExistingCustomer(String phone) {
		List<ExistingCustomer> cec = new ArrayList<ExistingCustomer>();
		
		try 
		 {
			 Statement statement = connection.createStatement();
			 String qry="select * from exe_cust WHERE phone='"+phone+"' AND STATUS='ACTIVE'";
			 ResultSet rs = statement.executeQuery(qry);
			while (rs.next()) {
		    
			ExistingCustomer c = new ExistingCustomer();
			
			c.setFirst_name(rs.getString("first_name"));
			c.setLast_name(rs.getString("last_name"));
			c.setStatus(rs.getString("status"));
			c.setPhone(phone);
			
			cec.add(c);
			}
			connection.commit();
			statement.close();
		  }catch (SQLException e) {
			  e.printStackTrace();
		  	}
		  
		  return cec;
	}
}






