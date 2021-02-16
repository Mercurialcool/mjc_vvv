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
    public Certificate dtoObject(CertificateDto dto) {
        Certificate certificate = new Certificate();
        dtoRouter.map(dto, certificate);
        return certificate;
    }

    @Override
    public CertificateDto objectDto(Certificate entity) {
        CertificateDto certificateDto = new CertificateDto();
        dtoRouter.map(entity, certificateDto);
        return certificateDto;
    }

    @Override
    public List<Certificate> dtoObjectList(List<CertificateDto> dtoList) {
        return dtoList.stream().map(this::dtoObject).collect(Collectors.toList());
    }

    @Override
    public List<CertificateDto> objectDtoList(List<Certificate> objectList) {
        return objectList.stream().map(this::objectDto).collect(Collectors.toList());
    }
}
