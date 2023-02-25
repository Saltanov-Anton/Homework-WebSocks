package com.example.websocks.service;
import com.example.websocks.model.Color;
import com.example.websocks.model.Size;
import com.example.websocks.model.SocksBatch;

public interface SocksValidationService {

    boolean validation(SocksBatch socksBatch);

    boolean validation(Color color, Size size, int cottonMin, int cottonMax);

}
