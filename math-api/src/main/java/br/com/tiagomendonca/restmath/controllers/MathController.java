package br.com.tiagomendonca.restmath.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiagomendonca.restmath.exception.UnsupportedMathOperationException;
import br.com.tiagomendonca.restmath.math.SimpleMath;
import br.com.tiagomendonca.restmath.request.converters.NumberConverter;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();
    
    // http://localhost:8080/math/sum/10/20
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return math.sum(
            NumberConverter.convertToDouble(numberOne),
            NumberConverter.convertToDouble(numberTwo)
        );
    }

    // http://localhost:8080/math/subtraction/10/20
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return math.subtraction(
            NumberConverter.convertToDouble(numberOne),
            NumberConverter.convertToDouble(numberTwo)
        );
    }

    // http://localhost:8080/math/multiplication/10/20
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return math.multiplication(
            NumberConverter.convertToDouble(numberOne),
            NumberConverter.convertToDouble(numberTwo)
        );
    }

    // http://localhost:8080/math/division/10/20
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return math.division(
            NumberConverter.convertToDouble(numberOne),
            NumberConverter.convertToDouble(numberTwo)
        );
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) throws Exception{
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return math.mean(
            NumberConverter.convertToDouble(numberOne),
            NumberConverter.convertToDouble(numberTwo)
        );
    }

    // http://localhost:8080/math/sqrt/25
    @RequestMapping("/sqrt/{number}")
    public Double squareRoot(
        @PathVariable("number") String number
    ) throws Exception{
        if (!NumberConverter.isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return math.squareRoot(NumberConverter.convertToDouble(number));
    }
}
