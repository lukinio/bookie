package pl.lukinio.bookie.data.entity.users;

import java.math.RoundingMode;

public enum Currency {
    PLN(1),
    EUR(4);

    private final int scale;
    Currency(final int scale){
        this.scale = scale;
    }
    public int getScale(){ 
        return scale;
    }
    public RoundingMode getRoundingMode(){
        return RoundingMode.CEILING;
    }
}
