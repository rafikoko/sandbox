package com.microservices.rentaloffer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RentalOfferBestPriceChooser implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(RentalOfferBestPriceChooser.class);
    private long uuid = UUID.randomUUID().getMostSignificantBits();

    private static Connections connection;
    private int bestPrice = -1;

    /**
     * @param args
     */
    public static void main(String[] args) {
        String host = args[0];
        String busName = args[1];

        connection = new Connections(host, busName);
        connection.deliveryLoop(new RentalOfferBestPriceChooser());

    }

    @Override
    public void handle(String message) {

        logger.info("*****************************************");

        logger.info(String.format(" [x] %s  - Received a message in Rental Offer Best Price chooser with uuid: %s, message content:  %s", 
                (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")).format(new Date()), 
                uuid, 
                message));
        
        logger.info(String.format(" [x] Received a message in rental offer solution chooser: %s", message));
        NeedPacket packet = NeedPacket.fromJson(message);
        if (!packet.isSolutionTypeAdded(Solution.SolutionType.OFFER_PRICE)) {

            return;
        }

        if (!packet.isSolutionTypeAdded(Solution.SolutionType.BEST_OFFER_PRICE)) {
            handleSolution(packet);
        }
    }

    private void handleSolution(NeedPacket packet) {
        List<Solution> solutions = packet.getSolutions();
        Solution bestRentalOfferSolution = null;
        for (Solution solution : solutions) {
            if (solution.getName().equals(Solution.SolutionType.OFFER_PRICE)) {
                String valueS = solution.getValue();
                int value = Integer.parseInt(valueS);
                if (bestPrice == -1) {
                    bestPrice = value;
                } else if (value < bestPrice) {
                    bestPrice = value;
                }

                logger.info("Currently available best price: " + bestPrice);
            }

            if (solution.getName().equals(Solution.SolutionType.BEST_OFFER_PRICE)) {
                solution.setValue(Integer.toString(bestPrice));
                bestRentalOfferSolution = solution;
            }
        }

        Solution solution;
        if (bestRentalOfferSolution == null) {
            solution = new Solution(Solution.SolutionType.BEST_OFFER_PRICE, Integer.toString(bestPrice), uuid);
            packet.proposeSolution(solution);
            connection.publish(packet.toJson());
        }

    }
}
