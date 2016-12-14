package team.com.ibus;

/**
 * Created by igor_ on 26/11/2016.
 */
public class Util{

    public boolean soTemLetra(String msg){
        for(char c : msg.toCharArray())
            if(c < 'a' || c > 'z')
                return false;
        return true;
    }


}