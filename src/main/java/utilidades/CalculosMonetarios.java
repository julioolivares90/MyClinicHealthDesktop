package utilidades;

import java.math.BigDecimal;

public class CalculosMonetarios {
    public static BigDecimal CalcularGanancia(BigDecimal costoPublico,BigDecimal costoPorUnidad){

        BigDecimal result =  costoPublico.subtract(costoPorUnidad);
       return result.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
