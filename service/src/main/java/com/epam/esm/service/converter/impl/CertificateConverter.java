package com.epam.esm.service.converter.impl;

import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.model.Certificate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CertificateConverter implements Converter<Certificate, CertificateDto> {

    @Autowired
    private ModelMapper dtoRouter;

    @Override
    public Certificate convertDtoToObject(CertificateDto dto) {
        return dtoRouter.map(dto, Certificate.class);
    }

    @Override
    public CertificateDto convertObjectToDto(Certificate entity) {
        CertificateDto certificateDto = new CertificateDto();
        dtoRouter.map(entity, certificateDto);
        return certificateDto;
    }

    @Override
    public List<Certificate> convertDtoToObjectList(List<CertificateDto> dtoList) {
        return dtoList.stream().map(this::convertDtoToObject).collect(Collectors.toList());
    }

    @Override
    public List<CertificateDto> convertObjectListToDto(List<Certificate> objectList) {
        return objectList.stream().map(this::convertObjectToDto).collect(Collectors.toList());
    }
}
