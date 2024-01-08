package com.example.dentiste1.connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * Requete
 */
public class Requete {

    Vector<Integer> key;

//---------------------------------------------------------------------------------------------------------------------------------------------------------
  


    public Vector<Integer> getKey() {
        return key;
    }

    public void setKey(Vector<Integer> key) {
        this.key = key;
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NoColumn {
    
}


//---------------------------------------------------------------------------------------------------------------------------------------------------------


    public String getValeurInsert(String sql){
            Field[] f=this.getClass().getDeclaredFields();
            Field[] fil = filtrer(f);
            int nb=12+this.getClass().getSimpleName().length()+8;
            String val=sql.substring(nb,sql.length()-1);
            String[] v=val.split(",");
            String valeur="";
            for (int i = 0; i < fil.length; i++) {
                if (fil[i].getType().getSimpleName().equals("String")) {
                    valeur=valeur+fil[i].getName()+" = "+v[i].substring(1, v[i].length()-1);
                }else{
                    valeur=valeur+fil[i].getName()+" = "+v[i];
                }
                if (i+1!=fil.length) {
                    valeur=valeur+" / ";
                }
            }
            return valeur;
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------


    public int farany(Connection c)throws Exception{
        Vector<Integer> key=this.getKey();
        Field[] f=this.getClass().getDeclaredFields();
        Field[] fil = filtrer(f);
        int pkey=0;
        // String get="get"+(fil[pkey].getName().substring(0, 1)).toUpperCase()+ fil[pkey].getName().substring(1);
        for (int i = 0; i < key.size(); i++) {
                if (key.get(i)==1) {
                    pkey=i;
                }
        }
        String sql="select max("+ fil[pkey].getName() + ") from "+this.getClass().getSimpleName();
        //System.out.println(sql);
        Statement stat=c.createStatement();
        ResultSet res=stat.executeQuery(sql);
        int val=0;
        while (res.next()) {
            val=res.getInt(1);
        }
        return val;
    }


//---------------------------------------------------------------------------------------------------------------------------------------------------------


public String autoIncrement(Connection c)throws Exception{
    Vector<Integer> key=this.getKey();
    Field[] f=this.getClass().getDeclaredFields();
    Field[] fil = filtrer(f);
    int pkey=0;
    // String get="get"+(fil[pkey].getName().substring(0, 1)).toUpperCase()+ fil[pkey].getName().substring(1);
    for (int i = 0; i < key.size(); i++) {
            if (key.get(i)==1) {
                pkey=i;
            }
    }
    String sql="select max("+ fil[pkey].getName() + ") from "+this.getClass().getSimpleName();
    //System.out.println(sql);
    Statement stat=c.createStatement();
    ResultSet res=stat.executeQuery(sql);
    int val=0;
    while (res.next()) {
        val=res.getInt(1);
    }
    // String avant=val.get(val.size()-1);
    // //System.out.println(avant);
    int numero=val+1;
    //System.out.println(numero);
    return String.valueOf(numero);

    
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    


    public String getSequence(Connection c)throws Exception{
        Vector<Integer> key=this.getKey();
        // Field[] fil=this.getClass().getDeclaredFields();
        int pkey=0;
        // String get="get"+(fil[pkey].getName().substring(0, 1)).toUpperCase()+ fil[pkey].getName().substring(1);
        for (int i = 0; i < key.size(); i++) {
                if (key.get(i)==1) {
                    pkey=i;
                }
        }
        String sql="select*from "+this.getClass().getSimpleName();
        //System.out.println(sql);
        Statement stat=c.createStatement();
        ResultSet res=stat.executeQuery(sql);
        Vector <String> val=new Vector<>();
        while (res.next()) {
            val.add(res.getString(pkey+1));
        }
        String avant=val.get(val.size()-1);
        int index=0;
        for (int i = 0; i < avant.length(); i++) {
            if (avant.charAt(i)=='0') {
                index=i;
                //System.out.println(index);
                break;
            }
        }
        int ind=0;
        int ch1=0;
        String num=avant.substring(index);
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i)!='0') {
                ind=i;
                for (int j = i; j < num.length(); j++) {
                    ch1++;
                }
                //System.out.println(ind);
                break;
            }
        }
        //System.out.println(ch1);
        //System.out.println(num);
        int numero=Integer.parseInt(num)+1;
        String nn=String.valueOf(numero);
        int ch2=0;
        String apres="";
        for (int i = 0; i < nn.length(); i++) {
            ch2++;
        }
        //System.out.println(ch2);
        if (ch2==ch1+1&&num.charAt(0)!=0) {
            apres=avant.substring(0,index)+num.substring(1, ind)+String.valueOf(numero);
        }else{
            apres=avant.substring(0,index)+num.substring(0, ind)+String.valueOf(numero);
        }
        //System.out.println(apres);
        return apres;

        
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------



    public String[][] traduction(){
        String[][] trad=new String[2][];
        for (int i = 0; i < trad.length; i++) {
            trad[i]=new String[3]; 
        }
        trad[0][0]="String";
        trad[1][0]=" varchar(50)";
        trad[0][1]="int";
        trad[1][1]=" int";
        trad[0][2]="double";
        trad[1][2]=" decimal";
        trad[0][3]="Date";
        trad[1][3]=" date";

        return trad;

    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------



    public String getSqlCreate(){
        Field[] f=this.getClass().getDeclaredFields();
        Field[] fil = filtrer(f);
        String sql="create table "+this.getClass().getSimpleName()+"(";
        String[][] trad=traduction();
        for (int i = 0; i < fil.length; i++) {
            sql=sql+fil[i].getType().getSimpleName();
            for (int j = 0; j < trad.length; j++) {
                if (fil[i].getType().getSimpleName().equals(trad[0][j])) {
                    sql=sql+trad[1][j];
                }
            }
            if (i+1==fil.length) {
                sql=sql+");";
            } else {
                sql=sql+",";
            }            
        }
        //System.out.println(sql);
        return sql;
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------

    public Field[] filtrer(Field[] all){
        List<Field> f = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            if(!all[i].isAnnotationPresent(NoColumn.class)){
                f.add(all[i]);
            }
        }
        Field[] fil = new Field[f.size()];
        for (int i = 0; i < fil.length; i++) {
            fil[i] = f.get(i);
        }
        return fil;
    }











//---------------------------------------------------------------------------------------------------------------------------------------------------------




    public String getSqlSelect(){
        Field[] f=this.getClass().getDeclaredFields();
        Field[] fil = filtrer(f);
        String[] col=new String[fil.length];
        for (int i = 0; i < fil.length; i++) {
            col[i]="get"+(fil[i].getName().substring(0, 1)).toUpperCase()+ fil[i].getName().substring(1);
        }
        String[] values=new String[col.length];
        try {
            for (int i = 0; i < col.length; i++) {
                values[i]=String.valueOf(this.getClass().getMethod(col[i]).invoke(this));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    //maka ny indicen'ny tsy null rehetra
        Vector<Integer> indice=new Vector<>();
        for (int i = 0; i < fil.length; i++) {
            // //System.out.println(fil[i].getType().getSimpleName());
            // if ((fil[i].getType().getSimpleName()).equals("int")) {
            //     if (Integer.parseInt(values[i])!=0) {
            //         indice.add(i);
            //         // //System.out.println(i);
            //     }
            // }else{
                if (values[i].equals("null")==false&&values[i].equals("0")==false&&values[i].equals("0.0")==false) {
                    indice.add(i);
                    // //System.out.println(i);
                }
            
            // }
        }

    //isan'ny tsy null
        int nb= indice.size();

    //manambatra an'ilay requete 
        String sql="";

        if (nb==0) {
            sql="select*from "+this.getClass().getSimpleName();
            return sql;
        }
        sql="select*from "+this.getClass().getSimpleName()+" where ";
        for (int i = 0; i < indice.size(); i++) {
            if (fil[indice.get(i)].getType().getSimpleName().equals("String")||fil[indice.get(i)].getType().getSimpleName().equals("Date")) {
                sql=sql+fil[indice.get(i)].getName()+" = '"+values[indice.get(i)]+ "'";
                if (i+1!=indice.size()) {
                    sql=sql+" and ";
                }
            } else {
                sql=sql+fil[indice.get(i)].getName()+" = "+values[indice.get(i)];
                if (i+1!=indice.size()) {
                    sql=sql+" and ";
                }
            }
        }
        // sql=sql+";";
        return sql;
        
        }
    


//---------------------------------------------------------------------------------------------------------------------------------------------------------

    

    public String getSqlInsert(Connection c){
        Field[] f=this.getClass().getDeclaredFields();
        Field[] fil = filtrer(f);
        String[] col=new String[fil.length];
        Vector<Integer> key=this.getKey();
        for (int i = 0; i < fil.length; i++) {
            col[i]="get"+(fil[i].getName().substring(0, 1)).toUpperCase()+ fil[i].getName().substring(1);
        }
        String[] values=new String[col.length];
        int pkey=99;
        try {
            for (int i = 0; i < col.length; i++) {
                values[i]=String.valueOf(this.getClass().getMethod(col[i]).invoke(this));
            }
            for (int i = 0; i < key.size(); i++) {
                if (key.get(i)==1) {
                    pkey=i;
                    break;
                }
            }
            if (pkey!=99) {
                values[pkey]=this.autoIncrement(c);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        String sql="";
        if (fil[0].getType().getSimpleName().equals("String")||fil[0].getType().getSimpleName().equals("Date")){
        sql="insert into "+this.getClass().getSimpleName()+" values('"+values[0]+"'";
        } else {
        sql="insert into "+this.getClass().getSimpleName()+" values("+values[0];
        }
        for (int i = 1; i < values.length; i++) {
            if (fil[i].getType().getSimpleName().equals("String")||fil[i].getType().getSimpleName().equals("Date")) {
                sql=sql+",'"+values[i]+"'";
            } else {
                sql=sql+","+values[i];
            }
        }
        sql=sql+")";
        //System.out.println(sql);
        return sql;
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------


    
    public String getSqlUpdate(){
        try {
            Field[] f=this.getClass().getDeclaredFields();
            Field[] fil = filtrer(f);
            int ind=0;
            // for (int i = 0; i < fil.length; i++) {
            //     if (fil[i].getName().equalsIgnoreCase(attribut)) {
            //         ind=i;
            //     }
            // }
            Vector<Integer> key=this.getKey();
            for (int i = 0; i < key.size(); i++) {
                if (key.get(i)==1) {
                    ind=i;
                    break;
                }
            }
            String[] col=new String[fil.length];

            for (int i = 0; i < fil.length; i++) {
                col[i]="get"+(fil[i].getName().substring(0, 1)).toUpperCase()+ fil[i].getName().substring(1);
            }

            Vector<Integer> indice=new Vector<>();
            for (int i = 0; i < fil.length; i++) {
                if (i!=ind) {
                    indice.add(i);
                }
            }

            String sql="update "+this.getClass().getSimpleName()+" set ";
            for (int i = 0; i < indice.size(); i++) {
                if (fil[indice.get(i)].getType().getSimpleName().equals("String")&&indice.get(i)!=ind) {
                    sql=sql+fil[indice.get(i)].getName()+" = '"+String.valueOf(this.getClass().getMethod(col[indice.get(i)]).invoke(this))+"' ";
                    if (i+1!=indice.size()) {
                        sql=sql+" , ";  
                    } 
                }
                if (fil[indice.get(i)].getType().getSimpleName().equals("String")==false&&i!=ind) {
                    sql=sql+fil[indice.get(i)].getName()+" = "+String.valueOf(this.getClass().getMethod(col[indice.get(i)]).invoke(this));
                    if (i+1!=indice.size()) {
                        sql=sql+" , ";  
                    } 
                }
                
            }
            
            sql=sql+" where "+fil[ind].getName()+" = ";

            if (fil[ind].getType().getSimpleName().equals("String")) {
                sql=sql+" '"+String.valueOf(this.getClass().getMethod(col[ind]).invoke(this))+"' ";
            }else{
                sql=sql+String.valueOf(this.getClass().getMethod(col[ind]).invoke(this));
            }

            //System.out.println(sql);
            return sql;
            // //System.out.println(fil[ind].getName());
            // //System.out.println(fil[ind].getName());
            // //System.out.println(this.getClass().getSimpleName());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
        
    }
    


//---------------------------------------------------------------------------------------------------------------------------------------------------------



    public void create(Connection c)throws Exception{
        if (c==null) {
            Connect co=new Connect();
            c=co.connecter();
        }
        try {
            String sql= getSqlCreate();
            Statement stat=c.createStatement();
            stat.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            c.rollback();
            // TODO: handle exception
        }finally{
            c.close();
        }
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------



    public void insert(Connection c)throws Exception{
            if (c==null||c.isClosed())
                c=(new Connect()).connecter();
            try {
                // this.setIdPersonne(this.getSequence(c));
                String sql= getSqlInsert(c);
                Statement st=c.createStatement();
                st.executeUpdate(sql);
                c.commit();    
                
            } catch (Exception e) {
                //System.out.println(e);
                
                c.rollback();
                // TODO: handle exception
            }finally{
                c.close();
            }
            
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------



    public Object[] select(Connection c)throws Exception{

        Field[] f = this.getClass().getDeclaredFields();
        Vector<Integer> k = new Vector<>();
        for (int i = 0; i < f.length; i++) {
            if (f[i].isAnnotationPresent(Column.class)) {
                Column co = f[i].getAnnotation(Column.class);
                k.add(co.contrainte());
                // System.out.println(co.contrainte()); 
            }
        }
        this.key =k;

        if (c==null||c.isClosed())
                c=(new Connect()).connecter();
        String sql= getSqlSelect();
//         System.out.println(sql);
        String count=sql.replace("*", " count(*) ");
        Statement stat=c.createStatement();
        ResultSet result= stat.executeQuery(count);
        int nb=0;
        while (result.next()) {
            nb=result.getInt(1);
            // //System.out.println(result.getInt(1));
        }

        try {
            Object[] o=new Object[nb];
            Field[] fil=this.getClass().getDeclaredFields();
            Class cl=this.getClass();
            for (int i = 0; i < o.length; i++) {
                o[i]=cl.getDeclaredConstructor().newInstance();
            }
            // //System.out.println(sql);
            ResultSet res=stat.executeQuery(sql);
            int i=0;
            while (res.next()) {
                    for (int u = 0; u < fil.length; u++) {
                        fil[u].setAccessible(true);
                        if(fil[u].getType().equals(String.class)){
                            fil[u].set(o[i],res.getString(u+1));
                            // //System.out.println(res.getString(u+1));
                        }
                        if(fil[u].getType().equals(int.class)){
                            fil[u].set(o[i],res.getInt(u+1));
                            // //System.out.println(res.getInt(u+1));
                        }
                        if(fil[u].getType().equals(double.class)){
                            fil[u].set(o[i],res.getDouble(u+1));
                            // //System.out.println();
                        }         
                        if(fil[u].getType().equals(Date.class)){
                            fil[u].set(o[i],res.getDate(u+1));
                            // //System.out.println();
                        }
                    }       
                i++;
            }
            c.close();
            return o;
        } catch (Exception e) {
            //System.out.println(e);
            // TODO: handle exception
        }
       
        return null;
    }



//---------------------------------------------------------------------------------------------------------------------------------------------------------



    public void update(Connection c)throws Exception{
        Field[] f = this.getClass().getDeclaredFields();
        Vector<Integer> k = new Vector<>();
        for (int i = 0; i < f.length; i++) {
            if (f[i].isAnnotationPresent(Column.class)) {
                Column co = f[i].getAnnotation(Column.class);
                k.add(co.contrainte());
                // System.out.println(co.contrainte()); 
            }
        }
        this.key =k;
        Boolean coTest = false;
        try {
            if (c==null||c.isClosed()){ 
                c = (new Connect()).connecter();
                coTest = true;
            }
            String sql=getSqlUpdate();
            Statement stat=c.createStatement();
            int es=stat.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            c.rollback();
            // TODO: handle exception
        }finally{
            if (coTest==true) {
                c.close();
            }
        }
    }


//-----------------------------------------------------------------------------------------------------------------------------------------------------------

    public int execute(Connection c,String sql)throws Exception{
        Boolean coTest = false;
        try {
            if (c==null||c.isClosed()){ 
                c = (new Connect()).connecter();
                coTest = true;
            }
            Statement st = c.createStatement();
            int ok = st.executeUpdate(sql);
            return ok;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        }finally{
            if (coTest==true) {
                c.close();
            }
        }
    }

//----------------------------------------------------------------

    public ResultSet query(Connection c, String sql)throws Exception{
        Boolean coTest = false;
        try {
            if (c==null||c.isClosed()){ 
                c = (new Connect()).connecter();
                coTest = true;
            }
            Statement st = c.createStatement();
            ResultSet res = st.executeQuery(sql);
            c.commit();
            return res;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        }finally{
            if (coTest==true) {
                c.close();
            }
        }
    }
   

   

  
    
}