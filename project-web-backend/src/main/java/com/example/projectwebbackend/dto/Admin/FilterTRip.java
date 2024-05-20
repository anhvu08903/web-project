package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTRip {

    private Province TinhDen;

    private Province TinhDi;

    private String Date;

}
