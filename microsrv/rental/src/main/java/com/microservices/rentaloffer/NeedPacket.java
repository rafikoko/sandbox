package com.microservices.rentaloffer;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;

public class NeedPacket {

    public NeedPacket(String userName) {
        super();
        this.userName = userName;
    }

    public final String NEED = "car_rental_offer";
    public final String INSURANCE_NEED = "insurance_offer";
    private final List<Solution> solutions = new ArrayList<>();
    private String userName;
    private String selectedNeed = "";

    public String toJson() {
        Map<String, Object> message = new HashMap<>();
        message.put("json_class", NeedPacket.class.getName());
        message.put("need", NEED);
        message.put("userName", userName);
        message.put("solutions", solutions);
        return new Gson().toJson(message);
    }

    public static NeedPacket fromJson(String message) {
        return new Gson().fromJson(message, NeedPacket.class);
    }

    public void proposeSolution(Solution solution) {
        solutions.add(solution);
    }

    public boolean isSolutionTypeAdded(Solution.SolutionType solutionType) {
        for (Solution solution : solutions) {
            if (solution.getName().equals(solutionType)) {
                return true;
            }
        }

        return false;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public boolean hasSolutions() {
        if (solutions != null && solutions.size() > 0) {
            return true;
        }
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
