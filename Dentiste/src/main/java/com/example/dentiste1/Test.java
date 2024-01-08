package com.example.dentiste1;

import java.sql.Connection;

import com.example.dentiste1.connection.Connect;
import com.example.dentiste1.model.Action;
import com.example.dentiste1.model.Consultation;
import com.example.dentiste1.model.Dent;
import com.example.dentiste1.model.EtatDuDent;

public class Test {
    public static void main(String[] args) {
        try {
            Connect co = new Connect();
            Connection c = co.connecter();
            Dent d = new Dent();
            String dent= "D1;D4";
            String note = "10;1";
            d.insertDent(c, dent, note);

//             d.setIdDent(1);
//             d = (Dent)d.select(c)[0];
//             EtatDuDent et = new EtatDuDent(d, 5);
//             Consultation con = new Consultation();
// //            System.out.println("eto");
//             con = con.analyseOneTeeth(c, et);
// //            System.out.println(con.getAllAction().length);
//             // Action[] all = con.actionForOneTeeth(c, et);
//             for (int i = 0; i < con.getAllAction().length; i++) {
// //                System.out.println("tao");
//                 System.out.println(con.getAllAction()[i].getAction().getPrestation().getLibelle()+" -- "+con.getAllAction()[i].getPrixUnitaire()+" -nb = "+con.getAllAction()[i].getAction().getNombre());
//             }
//             System.out.println(con.getArgent());

            // for (int i = 0; i < all.length; i++) {
            //     System.out.println(all[i].getPrestation().getLibelle()+" nb = "+all[i].getNombre());
            // }
            

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
