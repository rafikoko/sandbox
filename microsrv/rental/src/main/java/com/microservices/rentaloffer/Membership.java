package com.microservices.rentaloffer;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Membership implements MessageHandler {
	
	private long uuid = UUID.randomUUID().getMostSignificantBits();

    public Membership(Connections connection) {
		super();
		this.connection = connection;
	}

	protected static Logger logger = LoggerFactory.getLogger(Membership.class);
    
    private Connections connection = null;
    
    enum MembershipType{
    	BASIC,
    	PREMIUM
    }

    public static void main(String[] args) {
        String host = args[0];
        String busName = args[1];

        Connections connection = new Connections(host, busName);
        connection.deliveryLoop(new Membership(connection));
    }

    public void handle(String message) {
        logger.info(String.format(" [x] Message received in Membership handler: %s", message));
        NeedPacket packet = NeedPacket.fromJson(message);
        
        boolean isMembershipDetermined = packet.isSolutionTypeAdded(Solution.SolutionType.MEMBERSHIP);
        if(isMembershipDetermined){
        	//connection.publish(packet.toJson());
        	return;
        }
        
        String membership = "NO";
        
        if(packet.getUserName().equals("Rafal")){
        	membership = MembershipType.BASIC.toString();
        }
        if(packet.getUserName().equals("Sally")){
        	membership = MembershipType.PREMIUM.toString();
        }
        
        Solution solution = new Solution(Solution.SolutionType.MEMBERSHIP, membership, uuid);
		packet.proposeSolution(solution);
		connection.publish(packet.toJson());
		
    }

}
