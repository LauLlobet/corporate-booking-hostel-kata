package model;

import actions.RoomSetOfKind;

import java.util.*;
import java.util.stream.Collectors;

public class Hotels {
    private HashMap<String,RoomSetOfKind> hashtable = new HashMap();
    private String separator = "--";

    public void upsert(int hotelId, RoomSetOfKind roomSetOfKind) {
        hashtable.put(hotelId+ separator +roomSetOfKind.getRoomType(),roomSetOfKind);
    }

    public List<RoomSetOfKind> getBy(int hotelId) {
        return hashtable.keySet().stream()
                .map(Objects::toString)
                .filter(x -> x.startsWith(hotelId+separator))
                .map(x -> hashtable.get(x)).collect(Collectors
                .toCollection(ArrayList::new));
    }
}
