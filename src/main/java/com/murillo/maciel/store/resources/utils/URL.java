package com.murillo.maciel.store.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL
{
    public static List<Integer> StringToIntList(String value)
    {
//        List<Integer> list = new ArrayList<>();
//        String[] split = value.split(",");
//        for (String str : split)
//        {
//            list.add(Integer.parseInt(str));
//        }
//        return list;

//        return Arrays.asList(value.split(","))
//                .stream()
//                .map(x -> Integer.parseInt(x))
//                .collect(Collectors.toList());

        return Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static String decodeParam(String param)
    {
        try
        {
            return URLDecoder.decode(param, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
}
