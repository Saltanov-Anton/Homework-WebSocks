package com.example.websocks.repository;

import com.example.websocks.model.Socks;
import com.example.websocks.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {

    void save(SocksBatch socksBatch);

    int delete(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
