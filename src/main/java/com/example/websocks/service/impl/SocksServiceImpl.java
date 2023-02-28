package com.example.websocks.service.impl;

import com.example.websocks.exceptions.SocksValidationException;
import com.example.websocks.model.Color;
import com.example.websocks.model.Size;
import com.example.websocks.model.Socks;
import com.example.websocks.model.SocksBatch;
import com.example.websocks.repository.SocksRepository;
import com.example.websocks.service.SocksService;
import com.example.websocks.service.SocksValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {

    private SocksRepository socksRepository;
    private SocksValidationService socksValidationService;

    @Override
    public void accept(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int release(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.delete(socksBatch);
    }

    @Override
    public int delete(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.delete(socksBatch);
    }

    @Override
    public int getRemains(Color color, Size size, int cottonPartMin, int cottonPartMax) {

        if (!socksValidationService.validation(color, size, cottonPartMin, cottonPartMax)){
            throw new SocksValidationException();
        }

        int count = 0;

        Map<Socks, Integer> socksMap = socksRepository.getAll();

        for (Map.Entry<Socks, Integer> socksEntry : socksMap.entrySet()) {
            Socks socks = socksEntry.getKey();
            if (socks.getColor().equals(color) && socks.getSize().equals(size)
                    && socks.getCottonPart() >= cottonPartMin && socks.getCottonPart() <= cottonPartMax) {
                count += socksEntry.getValue();
            }
        }
        return count;
    }

    private void checkSocksBatch(SocksBatch socksBatch) {

        if (!socksValidationService.validation(socksBatch)) {
            throw new SocksValidationException();
        }
    }
}
