package com.example.websocks.service;
import com.example.websocks.model.Color;
import com.example.websocks.model.Size;
import com.example.websocks.model.SocksBatch;

public interface SocksService {

    void accept(SocksBatch socksBatch);

    int release(SocksBatch socksBatch);

    int delete(SocksBatch socksBatch);

    int getRemains(Color color, Size size, int cottonPartMin, int cottonPartMax);
}
