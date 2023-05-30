import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class ReverseServer {

  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);

      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootPOA.the_POAManager().activate();

      Reverse h_ref = ReverseHelper.narrow(
        rootPOA.servant_to_reference(new ReverseImpl())
      );

      NamingContextExt ncRef = NamingContextExtHelper.narrow(
        orb.resolve_initial_references("NameService")
      );
      NameComponent path[] = ncRef.to_name("Reverse");
      ncRef.rebind(path, h_ref);

      orb.run();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
