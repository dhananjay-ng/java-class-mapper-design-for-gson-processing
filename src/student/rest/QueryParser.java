package student.rest;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class QueryParser {

    public String parse(String json) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap = (Map<String, Object>) new Gson().fromJson(json, queryMap.getClass());
        for (Entry<String, Object> e : queryMap.entrySet()) {
            System.out.print(e.getKey() + ":");
            System.out.println(e.getValue());
            System.out.println(e.getValue().getClass());
        }
        return query(queryMap);

    }

    private String query(Map<String, Object> map) {

        String q = map.entrySet().stream().map(e -> mapOperation(e)).collect(Collectors.joining(") and (", "(", ")"));
        System.out.println(q);
        return q;
    }

    private String mapOperation(Entry<String, Object> e) {
        String op = e.getKey();
        String predicate = null;
        if (op.equals("$ne")) {
            predicate = "!=" + e.getValue();
        } else if (op.equals("$or")) {
            if (e.getValue() instanceof ArrayList) {
                List<LinkedTreeMap<String, Object>> orConditions = (ArrayList) e.getValue();
                predicate = orConditions.stream().map(e1 -> query(e1)).collect(Collectors.joining(") or (", "(", ")"));
            }
        } else if (op.equals("$in")) {
            if (e.getValue() instanceof ArrayList) {
                List<?> values = (List<?>) e.getValue();
                predicate = values.stream().map(e1 -> e1.toString()).collect(Collectors.joining(",", " in (", ")"));
            }
        } else if (op.equals("$nin")) {
            if (e.getValue() instanceof ArrayList) {
                List<?> values = (List<?>) e.getValue();
                predicate = values.stream().map(e1 -> e1.toString()).collect(Collectors.joining(",", " not in (", ")"));
            }
        } else {
            predicate = e.getKey();
            if (e.getValue() instanceof String) {
                predicate = predicate + "=" + e.getValue();
            } else if (e.getValue() instanceof LinkedTreeMap) {
                String query = query((Map) e.getValue());
                predicate = predicate + query.substring(1, query.length() - 1);
            }

        }

        return predicate;
    }

}
