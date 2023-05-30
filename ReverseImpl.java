import ReverseModule.ReversePOA;

// import java.lang.*;

public class ReverseImpl extends ReversePOA {

  public String reverse_string(String name) {
    StringBuffer str = new StringBuffer(name);
    str.reverse();
    return ("Server Send " + str);
  }
}
