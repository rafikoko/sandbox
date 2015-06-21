package com.microservices.rentaloffer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralRentalOfferHandler implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(GeneralRentalOfferHandler.class);

    private long uuid = UUID.randomUUID().getMostSignificantBits();

    private static Connections connection;

    public GeneralRentalOfferHandler() {
        logger.info(String.format(" [x] Created general rental offer handler with uuid: " + uuid));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String host = args[0];
        String busName = args[1];

        connection = new Connections(host, busName);
        connection.deliveryLoop(new GeneralRentalOfferHandler());

    }

    @Override
    public void handle(String message) {

        logger.info("*****************************************");

        logger.info(String.format(" [x] %s  - Received a need in GeneralRentalOfferHandler with uuid: %s, message content:  %s", 
                (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")).format(new Date()), 
                uuid, 
                message));
        NeedPacket packet = NeedPacket.fromJson(message);
        logger.info("Verifying received package. Has OFFER_PRICE solutions: " + packet.isSolutionTypeAdded(Solution.SolutionType.OFFER_PRICE)
                + "; Username: " + packet.getUserName() + "");
        if (!packet.isSolutionTypeAdded(Solution.SolutionType.OFFER_PRICE)) {
            handleNeed(packet);
        }

    }

    private void handleNeed(NeedPacket packet) {

        //handle only packages without user name 
        if (packet.getUserName() == null || packet.getUserName().trim().length() == 0) {

            String value = Integer.toString(new Random().nextInt(50));

            Solution solution = new Solution(
                    Solution.SolutionType.OFFER_PRICE,
                    value,
                    uuid);
            packet.proposeSolution(solution);
            logger.info("Handling need. NEW Offer Price solution created with value:"
                    + solution.getValue()
                    + ", uuid of handler: "
                    + solution.getLastHandlerUUID()
                    + ". User name from package: " + packet.getUserName());

            connection.publish(packet.toJson());
        }
    }

}
