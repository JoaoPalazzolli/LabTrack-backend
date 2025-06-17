package br.com.project.labtrack.utils;

import org.modelmapper.ModelMapper;

import java.util.List;

public class Mapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseTo(O origin, Class<D> destionationType) {
        return mapper.map(origin, destionationType);
    }

    public static <O, D> List<D> parseListTo(List<O> origin, Class<D> destionationType) {
        return origin.stream().map(x -> mapper.map(x, destionationType)).toList();
    }
}
