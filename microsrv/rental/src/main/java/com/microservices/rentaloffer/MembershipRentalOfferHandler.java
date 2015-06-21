package com.microservices.rentaloffer;

import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MembershipRentalOfferHandler implements MessageHandler {

	protected static Logger logger = LoggerFactory.getLogger(MembershipRentalOfferHandler.class);
	
	private long uuid = UUID.randomUUID().getMostSignificantBits();
	
	private static Connections connection;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = args[0];
        String busName = args[1];

        connection = new Connections(host, busName);
        connection.deliveryLoop(new MembershipRentalOfferHandler());

	}

	@Override
	public void handle(String message) {
		logger.info(String.format(" [x] Received a need in need GeneralRentalOfferHandler: %s", message));
		NeedPacket packet = NeedPacket.fromJson(message);
		if(!packet.hasSolutions()){
			handleNeed(packet);
		}
		
		
	}
	
	private void handleNeed(NeedPacket packet){
		if(packet.getUserName()!=null || packet.getUserName().trim().length()>0){
			Solution solution = new Solution(
                                Solution.SolutionType.OFFER_PRICE,
                                Integer.toString(new Random().nextInt(50)), uuid);
			packet.proposeSolution(solution);
			logger.info("User name: " + packet.getUserName());
			connection.publish(packet.toJson());
		}
	}
	

}
