package com.excilys.computerdatabase.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> s) {
        String rs = null;
        for (String e : s) {
            rs += e.trim();
        }
        System.out.println(rs);
        return rs;
    }

    @Override
    public List<String> convertToEntityAttribute(String arg0) {
        String[] arr = arg0.split(",");
        List<String> ls = new ArrayList<String>();
        for (String s : arr) {
            ls.add(s.trim());
        }
        System.out.println(ls.toString());
        return ls;
    }

}
