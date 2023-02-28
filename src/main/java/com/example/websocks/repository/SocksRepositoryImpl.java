package com.example.websocks.repository;

import com.example.websocks.model.Socks;
import com.example.websocks.model.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository{

    private final Map<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public int delete(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);

            if (socksBatch.getQuantity() < quantity) {
                socksMap.replace(socks, quantity - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
