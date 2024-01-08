package com.example.route1;

import java.sql.Connection;

import com.example.route1.connection.Connect;
import com.example.route1.model.Consultation;
import com.example.route1.model.PetiteRoute;
import com.example.route1.model.EtatDeLaRoute;

public class Test {
    public static void main(String[] args) {
        try {
//             Connect co = new Connect();
//             Connection c = co.connecter();
//             PetiteRoute d = new PetiteRoute();
//             d.setIdDent(1);
//             d = (PetiteRoute)d.select(c)[0];
//             EtatDeLaRoute et = new EtatDeLaRoute(d, 5);
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
