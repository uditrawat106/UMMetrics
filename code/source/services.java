

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.pcbsys.nirvana.nAdminAPI.*;
import com.pcbsys.nirvana.client.*;
import java.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class services

{
	// ---( internal utility methods )---

	final static services _instance = new services();

	static services _newInstance() { return new services(); }

	static services _cast(Object o) { return (services)o; }

	// ---( server methods )---




	public static final void allMetrics (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(allMetrics)>> ---
		// @sigtype java 3.5
		// [i] field:0:required hostName
		// [i] field:0:required portNumber
		// [i] field:0:required realmProviderURL
		// [i] object:1:required assetMetrics
		// [o] field:0:required realmProviderURL
		// [o] record:1:required assetMetrics
		// [o] - object:1:required name
		// [o] - object:0:required type
		// [o] - object:0:required mode
		// [o] - object:0:required queueDepth
		// [o] - object:0:required eventId
		// [o] - object:0:required published
		// [o] - object:0:required consumed
		// [o] - object:0:required totalConnection
		// [o] - object:0:required currentConn
		// [o] - object:0:required publishRate
		// [o] - object:0:required consumeRate
		// [o] - object:0:required connectionRate
		// [o] - object:0:required fanoutTime
		// [o] - object:0:required usedSpaceKb
		// [o] record:1:required realmMetrics
		// [o] - object:0:required clusterState
		// [o] - object:0:required clusterInfo
		// [o] - object:0:required totalChannels
		// [o] - object:0:required totalQueues
		// [o] - object:0:required published
		// [o] - object:0:required consumed
		// [o] - object:0:required totalConnections
		// [o] - object:0:required totalMemory 
		// [o] - object:0:required currentConnections
		// [o] - object:0:required freeMemory
		// [o] - object:0:required usedMemory
		// [o] - object:0:required totalDirectMemory
		// [o] - object:0:required totalNodes
		// [o] - object:0:required heapPercentage
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String hostName = IDataUtil.getString( pipelineCursor, "hostName" ); String
		portNumber = IDataUtil.getString( pipelineCursor, "portNumber" ); String
		realmProviderURL = "nsp://"+hostName+":"+portNumber;
		ArrayList<String> qList = new ArrayList<String>();
		ArrayList<String> cList = new ArrayList<String>(); 
		String[] chanNames = cList.toArray(new String[cList.size()]);
		int chanCount = cList.size();
		int queCount = qList.size();
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "realmProviderURL", realmProviderURL );
		Object[]	assetMetrics = IDataUtil.getObjectArray( pipelineCursor, "assetMetrics" );// pipeline
		
		try{
			nRealmNode realmNode = initiateRealmNode(realmProviderURL);
			mySession=initiateSession(realmProviderURL);
			
			com.pcbsys.nirvana.client.nChannelAttributes nca = new com.pcbsys.nirvana.client.nChannelAttributes();
			
		    //For Channels
			for(int i=0;i<chanCount;i++){
				if(chanNames[i]!=null){
				nca.setName(chanNames[i]);
				nQueue nq = mySession.findQueue(nca);
				nQueueDetails nqd = nq.getDetails();
				nca.setName(chanNames[i]);
				nChannel nc= mySession.findChannel(nca);
				nDurableManager ndm = nc.getDurableManager(); 
				nDurable[] nd = ndm.getAll();
				IData[]	channelStatistics = new IData[nd.length]; 
				IData[] channelStatistics1 = new IData[1];
				Object[]	assetMetrics_1 = new Object[1];
				nNode found = realmNode.findNode(chanNames[i]);
				nLeafNode nlf = (nLeafNode)found;
				String channelType=new String();
				nca =nlf.getAttributes();
				int types = nca.getType();
				if(types == 1){
					channelType="Reliable";
				}else if (types == 2){
					channelType="Persistent";
				}else if (types == 3){
					 channelType= "Mixed";
				}else if (types == 4){
					channelType= "Simple";
				}else if (types == 5){
					channelType= "Transient";
				}else if (types == 7){
					channelType= "Off Heap";
				}else if (types == 8){
					channelType= "Paged";
				}
				assetMetrics_1[0] = new Object();
				IDataUtil.put( pipelineCursor_1, "assetMetrics", assetMetrics_1 );
				Object name = new Object();
				IDataUtil.put( pipelineCursor_1, "name", chanNames[i] );
				Object type = new Object();
				IDataUtil.put( pipelineCursor_1, "type", "Channel" );
				Object mode = new Object();
				IDataUtil.put( pipelineCursor_1, "mode", channelType );
				Object queueDepth = new Object();
				IDataUtil.put( pipelineCursor_1, "queueDepth", nqd.getNoOfEvents() );
				Object eventId = new Object();
				IDataUtil.put( pipelineCursor_1, "eventId", nlf.getLastEID() );
				Object published = new Object();
				IDataUtil.put( pipelineCursor_1, "published", nlf.getTotalPublished() );
				Object consumed = new Object();
				IDataUtil.put( pipelineCursor_1, "consumed", nlf.getTotalConsumed() );
				Object currentConn = new Object();
				IDataUtil.put( pipelineCursor_1, "currentConn", nlf.getCurrentNoOfConnections() );
				Object totalConnection = new Object();
				IDataUtil.put( pipelineCursor_1, "totalConnection", nlf.getTotalNoOfConnections() );
				Object publishRate = new Object();
				IDataUtil.put( pipelineCursor_1, "publishRate", nlf.getPublishRate() );
				Object consumeRate = new Object();
				IDataUtil.put( pipelineCursor_1, "consumeRate", nlf.getConsumedRate() );
				Object connectionRate = new Object();
				IDataUtil.put( pipelineCursor_1, "connectionRate", nlf.getConnectionRate() );
				Object fanoutTime = new Object();
				IDataUtil.put( pipelineCursor_1, "fanoutTime", nlf.getFanoutTime() );
				Object usedSpaceKb = new Object();
				IDataUtil.put( pipelineCursor_1, "usedSpaceKb", nlf.getUsedSpace()/1024 );
				}else{
					System.out.println("No channel exists");
				}
			}
			// realmMetrics
			IData[]	realmMetrics = new IData[1];
			realmMetrics[0] = IDataFactory.create();
			IDataCursor realmMetricsCursor = realmMetrics[0].getCursor();
			
			nClusterNode clusterNode = realmNode.getCluster();
		     if(clusterNode == null) {
		    	 
		     } 
		     else {
		            
		            Map<String, String> nodeSiteMap = new HashMap<>();
		            Map<String, String> statusMap = new HashMap<>();
		            //Object clusterState = new Object();
		            //Object clusterInfo = new Object();
		            String clusterInfo = "";
		            String clusterState = "";
		            List<nClusterStatus> clusterConnectionStatus = clusterNode.getClusterConnectionStatus();
		            for (nClusterStatus status : clusterConnectionStatus) {
		            	String mnode = status.getElectedMaster();
		            	if (status.getName().equalsIgnoreCase(status.getElectedMaster())){
		            		if (status.getState().equalsIgnoreCase("Master")) {
		            			clusterInfo = clusterInfo + status.getName() + " - "+status.getState() + " | ";
		            			statusMap.put(status.getName(), status.getState());
		            			for (int j=0; j<status.size(); j++){
		            				if (!(status.getStatus(j).getName().equalsIgnoreCase(mnode))){
		            					  if(status.getStatus(j).isOnline() == false) {
		            						  clusterInfo = clusterInfo + status.getStatus(j).getName() + " - Offline" + " | ";
		            						  statusMap.put(status.getStatus(j).getName(), "Offline");
		                                 } else if (status.getStatus(j).isOnline() == true) {
		                                	 clusterInfo = clusterInfo +status.getStatus(j).getName() + " - Slave" + " | ";
		                                	 statusMap.put(status.getStatus(j).getName(), "Slave");
		                                 }
		            				}
		            			}
		            		} } else {
		            			//statusMap.put(status.getStatus(i).getName(), "Slave");
		            		}
		            	}
		            
		            if (clusterInfo.length()>1)
		            	clusterState="true";
		            else 
		            	clusterState="false";
			IDataUtil.put( pipelineCursor_1, "clusterState", clusterState );
			IDataUtil.put( pipelineCursor_1, "clusterInfo", clusterInfo );
		    }
			
		     Object totalChannels = new Object();
		     IDataUtil.put( realmMetricsCursor, "totalChannels", chanCount );
		     Object totalQueues = new Object();
		     IDataUtil.put( realmMetricsCursor, "totalQueues", queCount );
		     Object published_1 = new Object();
		     IDataUtil.put( realmMetricsCursor, "published", Long.toString(realmNode.getTotalPublished()) );
		     Object consumed_1 = new Object();
		     IDataUtil.put( realmMetricsCursor, "consumed", Long.toString(realmNode.getTotalSubscribed()) );
		     Object totalConnections = new Object();
		     IDataUtil.put( realmMetricsCursor, "totalConnections", nlf.getTotalNoOfConnections() );
		     Object totalMemory_ = new Object();
		     IDataUtil.put( realmMetricsCursor, "totalMemory ", Long.toString(realmNode.getTotalMemory()/(1024*1024)) );
		     Object currentConnections = new Object();
		     IDataUtil.put( realmMetricsCursor, "currentConnections", nlf.getConnectionRate() );
		     Object freeMemory = new Object();
		     IDataUtil.put( realmMetricsCursor, "freeMemory", Long.toString(realmNode.getFreeMemory()/(1024*1024)) );
		     Object usedMemory = new Object();
		     IDataUtil.put( realmMetricsCursor, "usedMemory", Long.toString(((realmNode.getTotalMemory()/(1024*1024)) - (realmNode.getFreeMemory()/(1024*1024)))) );
		     Object totalDirectMemory = new Object();
		     IDataUtil.put( realmMetricsCursor, "totalDirectMemory", Long.toString(realmNode.getTotalDirectMemory()/(1024*1024)) );
		     Object totalNodes = new Object();
		     IDataUtil.put( realmMetricsCursor, "totalNodes", Integer.toString(realmNode.getTotalNodes()) );
		     Object heapPercentage = new Object();
		     IDataUtil.put( realmMetricsCursor, "heapPercentage", (Double.toString(realmNode.getHeapPercentage())) );
		     realmMetricsCursor.destroy();
		     IDataUtil.put( pipelineCursor_1, "realmMetrics", realmMetrics );
		}catch (Exception e) {
			System.out.println("Error : " +e.getMessage()); 
		}
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void assetTypes (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(assetTypes)>> ---
		// @sigtype java 3.5
		// [i] field:0:required hostName
		// [i] field:0:required portNumber
		// [i] record:0:required aTypes
		// [o] record:0:required assetTypes
		// [o] - field:0:required channels
		// [o] - field:0:required queues
		// [o] - field:0:required totalChannels
		// [o] - field:0:required totalQueues
		// [o] - field:0:required published
		// [o] - field:0:required consumed
		// [o] - field:0:required totalRealms
		// [o] - field:0:required totalConnectionStatus
		// [o] - field:0:required currentConnectionStatus
		// [o] - field:0:required totalMemoryUsage
		// [o] - field:0:required freeMemory
		// [o] - field:0:required usedMemory
		// [o] - field:0:required totalDirectMemory
		// [o] - field:0:required heapPercentage
		// [o] - field:0:required clusterInfo
		// [o] - field:0:required clusterState
		IDataCursor pipelineCursor = pipeline.getCursor(); 
		String hostName = IDataUtil.getString( pipelineCursor, "hostName" );
		String portNumber = IDataUtil.getString( pipelineCursor, "portNumber" );
		String realmProviderURL = "nsp://"+hostName+":"+portNumber;	
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		ArrayList<String> qList = new ArrayList<String>();
		ArrayList<String> cList = new ArrayList<String>(); 
		 try
		 {   
			// assetTypes
			IData aTypes = IDataUtil.getIData( pipelineCursor, "aTypes" );
			if ( aTypes != null)
			{
			}
			pipelineCursor.destroy();
			
			// assetTypes
			IData assetTypes = IDataFactory.create();
			IDataCursor assetTypesCursor = assetTypes.getCursor();
			realmNode = initiateRealmNode(realmProviderURL);
			listChannelsAndQueues(realmNode, realmNode.getNodes(),qList,cList);
			IDataUtil.put( assetTypesCursor, "channels", cList.toArray(new String[cList.size()]) );
			IDataUtil.put( assetTypesCursor, "queues", qList.toArray(new String[qList.size()]) );
			IDataUtil.put(assetTypesCursor, "totalChannels", Integer.toString(cList.size()));
			IDataUtil.put(assetTypesCursor, "totalQueues", Integer.toString(qList.size()));
			IDataUtil.put(assetTypesCursor, "published", Long.toString(realmNode.getTotalPublished()));
			IDataUtil.put(assetTypesCursor, "consumed", Long.toString(realmNode.getTotalSubscribed()));
			IDataUtil.put(assetTypesCursor, "totalRealms", Integer.toString(realmNode.getTotalNodes()));
			IDataUtil.put(assetTypesCursor, "totalConnectionStatus", Long.toString(realmNode.getTotalConnections()));
			IDataUtil.put(assetTypesCursor, "currentConnectionStatus", Integer.toString(realmNode.getCurrentConnections()));
			IDataUtil.put(assetTypesCursor, "totalMemoryUsage", Long.toString(realmNode.getTotalMemory()/(1024*1024)));
			IDataUtil.put(assetTypesCursor, "freeMemory", Long.toString(realmNode.getFreeMemory()/(1024*1024)));
			IDataUtil.put(assetTypesCursor, "usedMemory", Long.toString(((realmNode.getTotalMemory()/(1024*1024)) - (realmNode.getFreeMemory()/(1024*1024)))));
			IDataUtil.put(assetTypesCursor, "totalDirectMemory", Long.toString(realmNode.getTotalDirectMemory()/(1024*1024)));
			IDataUtil.put(assetTypesCursor, "heapPercentage", (Double.toString(realmNode.getHeapPercentage())));
			nClusterNode clusterNode = realmNode.getCluster();
		    if(clusterNode == null) {
		   	 
		    } 
		    else {
		           
		           Map<String, String> nodeSiteMap = new HashMap<>();
		           Map<String, String> statusMap = new HashMap<>();
		           String clusterInfo = "";
		           String clusterState = "";
		           List<nClusterStatus> clusterConnectionStatus = clusterNode.getClusterConnectionStatus();
		           for (nClusterStatus status : clusterConnectionStatus) {
		           	String mnode = status.getElectedMaster();
		           	if (status.getName().equalsIgnoreCase(status.getElectedMaster())){
		           		if (status.getState().equalsIgnoreCase("Master")) {
		           			clusterInfo = clusterInfo + status.getName() + " - "+status.getState() + " | ";
		           			statusMap.put(status.getName(), status.getState());
		           			for (int i=0; i<status.size(); i++){
		           				if (!(status.getStatus(i).getName().equalsIgnoreCase(mnode))){
		           					  if(status.getStatus(i).isOnline() == false) {
		           						  clusterInfo = clusterInfo + status.getStatus(i).getName() + " - Offline" + " | ";
		           						  statusMap.put(status.getStatus(i).getName(), "Offline");
		                                 } else if (status.getStatus(i).isOnline() == true) {
		                                	 clusterInfo = clusterInfo +status.getStatus(i).getName() + " - Slave" + " | ";
		                                	 statusMap.put(status.getStatus(i).getName(), "Slave");
		                                 }
		           				}
		           			}
		           		} } else {
		           			//statusMap.put(status.getStatus(i).getName(), "Slave");
		           		}
		           	}
		           
		           if (clusterInfo.length()>1)
		           	clusterState="true";
		           else 
		           	clusterState="false";
		           IDataUtil.put(assetTypesCursor, "clusterInfo", clusterInfo.toUpperCase());
		           IDataUtil.put(assetTypesCursor, "clusterState", clusterState);
		    }
			IDataUtil.put( pipelineCursor_1, "assetTypes", assetTypes );
			assetTypesCursor.destroy();
		 }
		 catch(Exception e)
		 {
			 throw new ServiceException(e.getMessage());
		 }
		 pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void fetchChannelMetrics (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fetchChannelMetrics)>> ---
		// @sigtype java 3.5
		// [i] field:0:required hostName
		// [i] field:0:required portNumber
		// [i] field:0:required channelName
		// [i] record:0:required mChan
		// [i] object:0:required sample
		// [i] object:0:required Untitled
		// [o] record:0:required assetMetrics
		// [o] - field:0:required channelName
		// [o] - field:0:required type
		// [o] - field:0:required eventId
		// [o] - field:0:required currentNoOfConnections
		// [o] - field:0:required queueLength
		// [o] - field:0:required realmProviderURL
		// [o] - field:0:required lastEventID
		// [o] - field:0:required totalPublished
		// [o] - field:0:required totalConsumed
		// [o] - field:0:required usedSpace
		// [o] - field:0:required channelType
		// [o] - field:0:required totalNoOfConnections
		// [o] - field:0:required publishRate
		// [o] - field:0:required consumedRate
		// [o] - field:0:required connectionRate
		// [o] - field:0:required fanoutTime
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String hostName = IDataUtil.getString( pipelineCursor, "hostName" ); String
		portNumber = IDataUtil.getString( pipelineCursor, "portNumber" ); String
		realmProviderURL = "nsp://"+hostName+":"+portNumber;
		 
		String channelName = IDataUtil.getString( pipelineCursor, "channelName" );
		String channelType=new String();
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		try{
		nRealmNode realmNode = initiateRealmNode(realmProviderURL);
		mySession=initiateSession(realmProviderURL);
		
		com.pcbsys.nirvana.client.nChannelAttributes nca = new com.pcbsys.nirvana.client.nChannelAttributes();
		nca.setName(channelName);
		nChannel nc= mySession.findChannel(nca);
		nDurableManager ndm = nc.getDurableManager(); 
		nDurable[] nd = ndm.getAll();
		IData[]	channelStatistics = new IData[nd.length]; 
		IData[] channelStatistics1 = new IData[1];
		
		nNode found = realmNode.findNode(channelName);
		nLeafNode nlf = (nLeafNode)found;
		
		nca =nlf.getAttributes();
		int type = nca.getType();
		if(type == 1){
			channelType="Reliable";
		}else if (type == 2){
			channelType="Persistent";
		}else if (type == 3){
			 channelType= "Mixed";
		}else if (type == 4){
			channelType= "Simple";
		}else if (type==5){
			channelType= "Transient";
		}else if (type==7){
			channelType= "Off Heap";
		}else if (type == 8){
			channelType= "Paged";
		}
		
		
			// mChan
			IData	mChan = IDataUtil.getIData( pipelineCursor, "mChan" );
			if ( mChan != null)
			{
			}
		pipelineCursor.destroy();
		//int totalPublished = Integer.parseInt();
		
		// metricsChan
		IData	metricsChan = IDataFactory.create();
		IDataCursor metricsChanCursor = metricsChan.getCursor();
		IDataUtil.put( metricsChanCursor, "channelName", channelName );
		IDataUtil.put( metricsChanCursor, "type", "Channel");
		IDataUtil.put( metricsChanCursor, "lastEventId", nlf.getLastEID() );
		IDataUtil.put( metricsChanCursor, "currentConnections", nlf.getCurrentNoOfConnections() );
		IDataUtil.put( metricsChanCursor, "queueLength", nlf.getCurrentNumberOfEvents() );
		IDataUtil.put( metricsChanCursor, "realmProviderURL", realmProviderURL );
		IDataUtil.put( metricsChanCursor, "totalPublished", nlf.getTotalPublished() );
		IDataUtil.put( metricsChanCursor, "totalConsumed", nlf.getTotalConsumed() );
		IDataUtil.put( metricsChanCursor, "percentageFreeInStore",nlf.getUsedSpace()/1024 );
		IDataUtil.put( metricsChanCursor, "mode", channelType);
		IDataUtil.put( metricsChanCursor, "totalConnections", nlf.getTotalNoOfConnections() );
		IDataUtil.put( metricsChanCursor, "publishedRate", nlf.getPublishRate() );
		IDataUtil.put( metricsChanCursor, "consumedRate", nlf.getConsumedRate() );
		IDataUtil.put( metricsChanCursor, "connectionsRate", nlf.getConnectionRate() );
		IDataUtil.put( metricsChanCursor, "fanoutRate", nlf.getFanoutTime());
		metricsChanCursor.destroy();
		IDataUtil.put( pipelineCursor_1, "assetMetrics", metricsChan );
		} catch (Exception e) {
			System.out.println(channelName +": " +e.getMessage()); 
		} 
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void queueLength (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(queueLength)>> ---
		// @sigtype java 3.5
		// [i] field:0:required hostName
		// [i] field:0:required portNumber
		// [i] field:1:required queueNames
		// [o] record:1:required assetMetrics
		// [o] - field:0:required clientId
		// [o] - field:0:required queueLength
		// [o] - field:0:required numEventsPublished
		// [o] - field:0:required lastEventTime
		// [o] - field:0:required mode
		// [o] - field:0:required type
		// [o] - field:0:required mode
		// [o] - field:0:required totalPublished
		// [o] - field:0:required totalConsumed
		// [o] - field:0:required lastEventId
		// [o] - field:0:required concurrentConnections
		// [o] - field:0:required totalConnections
		// [o] - field:0:required publishedRate
		// [o] - field:0:required consumedRate
		// [o] - field:0:required connectionsRate
		// [o] - field:0:required fanoutRate
		// [o] - field:0:required percentageFreeInStore
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String hostName = IDataUtil.getString( pipelineCursor, "hostName" ); String
		portNumber = IDataUtil.getString( pipelineCursor, "portNumber" ); String
		realmProviderURL = "nsp://"+hostName+":"+portNumber;
		String[] queueNames = IDataUtil.getStringArray( pipelineCursor, "queueNames" );//{"PurchaseOrder"};//IDataUtil.getStringArray( pipelineCursor, "queueNames" );
		String channelType=new String();
		mySession = null;
		try{
			nRealmNode realmNode = initiateRealmNode(realmProviderURL);
			mySession=initiateSession(realmProviderURL);
			// pipeline
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			IData[]	umClients = new IData[queueNames.length];
			for(int x=0;x<queueNames.length;x++)
			{
				String q=queueNames[x];
				umClients[x] = IDataFactory.create();
				IDataCursor umClientsCursor = umClients[x].getCursor();
				com.pcbsys.nirvana.client.nChannelAttributes cattrib = new com.pcbsys.nirvana.client.nChannelAttributes();
				cattrib.setName(q);
				nQueue nq = mySession.findQueue(cattrib);
				nQueueDetails nqd = nq.getDetails();
				
				nNode found = realmNode.findNode(q);
				nLeafNode nlf = (nLeafNode)found;
		
				cattrib =nlf.getAttributes();
				int type = cattrib.getType();
				if(type == 1){
					channelType="Reliable";
				}else if (type == 2){
					channelType="Persistent";
				}else if (type == 3){
					 channelType= "Mixed";
				}else if (type == 4){
					channelType= "Simple";
				}else if (type==5){
					channelType= "Transient";
				}else if (type==7){
					channelType= "Off Heap";
				}else if (type == 8){
					channelType= "Paged";
				}
				IDataUtil.put( umClientsCursor, "clientId", q);
				IDataUtil.put( umClientsCursor, "queueLength", nqd.getNoOfEvents() );
				IDataUtil.put( umClientsCursor, "numEventsPublished", nlf.getLastEID() );
				if(nqd.getLastEventTime()!=0)
					IDataUtil.put( umClientsCursor, "lastEventTime", new Date(nqd.getLastEventTime()) );
				else
					IDataUtil.put( umClientsCursor, "lastEventTime", "null" );
				IDataUtil.put( umClientsCursor, "mode", channelType);
				IDataUtil.put( umClientsCursor, "type", "Queue");
				IDataUtil.put( umClientsCursor, "mode", channelType);
				IDataUtil.put( umClientsCursor, "totalPublished", nlf.getTotalPublished() );
				IDataUtil.put( umClientsCursor, "totalConsumed", nlf.getTotalConsumed() );
				IDataUtil.put( umClientsCursor, "lastEventId", "-" );
				IDataUtil.put( umClientsCursor, "concurrentConnections", nlf.getCurrentNoOfConnections() );
				IDataUtil.put( umClientsCursor, "totalConnections", nlf.getTotalNoOfConnections() );
				IDataUtil.put( umClientsCursor, "publishedRate", nlf.getPublishRate() );
				IDataUtil.put( umClientsCursor, "consumedRate", nlf.getConsumedRate() );
				IDataUtil.put( umClientsCursor, "connectionsRate", nlf.getConnectionRate() );
				IDataUtil.put( umClientsCursor, "fanoutRate", nlf.getFanoutTime() );
				IDataUtil.put( umClientsCursor, "percentageFreeInStore", nlf.getUsedSpace()/1024 );	
				umClientsCursor.destroy();
			}
			IDataUtil.put( pipelineCursor, "assetMetrics", umClients );			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	public static nRealmNode initiateRealmNode(String realmProviderURL ) throws ServiceException
	{
	if(!mapRealm.containsKey(realmProviderURL))
	{
		String[] RNAME = realmProviderURL.split(",|;");
		try {
			nSessionAttributes nsa=new nSessionAttributes(RNAME);
			realmNode = new nRealmNode(nsa);
			Thread.sleep(2000);
			mapRealm.put(realmProviderURL, realmNode);
		}
		catch(Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	else
	{
		realmNode = mapRealm.get(realmProviderURL);
		if(!realmNode.isConnected())
		{
			realmNode.close();
			realmNode=null;
			mapRealm.remove(realmProviderURL);
			initiateRealmNode(realmProviderURL);
		}
			
	}
	return realmNode;
	}
	public static nSession initiateSession(String realmProviderURL) throws ServiceException
	{
		if(!mapSession.containsKey(realmProviderURL))
		{
			String[] RNAME = realmProviderURL.split(",|;");
			try {
				nSessionAttributes nsa=new nSessionAttributes(RNAME);
				mySession=nSessionFactory.create(nsa);
				mySession.init();
				
				mapSession.put(realmProviderURL, mySession);
			}
			catch(Exception e)
			{
				throw new ServiceException(e.getMessage());
			}
		}
		else
		{
			mySession = mapSession.get(realmProviderURL);
			if(!mySession.isConnected())
			{
				mySession.close();
				mySession=null;
				mapSession.remove(realmProviderURL);
				initiateSession(realmProviderURL);
			}
		}
	return mySession;
	}
	
	public static void listChannelsAndQueues(nRealmNode realmNode, Enumeration enum1,ArrayList<String> qList,ArrayList<String> cList)
	{
		 while(enum1.hasMoreElements())
		 {
			 Object obj = enum1.nextElement();
	
			 if ( obj instanceof nContainer ) 
			 {
			        nContainer cont = (nContainer)obj;
			        listChannelsAndQueues(realmNode,cont.getNodes(),qList,cList);
			 }
			 else if (obj instanceof nLeafNode)
			 {
				 nLeafNode leafNode = (nLeafNode)obj;
				 if(leafNode.isChannel())
				 {	
					 if(!leafNode.getAbsolutePath().equals("/naming/defaultContext"))
					 {
						 cList.add(leafNode.getAbsolutePath());
					 }
				 }
				 if(leafNode.isQueue())
				 {
					 qList.add(leafNode.getAbsolutePath());
				 }
			 }
			 else if ( obj instanceof nRealmNode ) 
			 {
				 listChannelsAndQueues( (nRealmNode)obj,((nRealmNode)obj).getNodes(),qList,cList);
		     }
		 }
	}
	
	public static nSession mySession = null;
	public static nRealmNode realmNode = null;
	public static HashMap<String,nSession> mapSession = new HashMap<String,nSession>();
	public static HashMap<String,nRealmNode> mapRealm = new HashMap<String,nRealmNode>();
		
	// --- <<IS-END-SHARED>> ---
}

