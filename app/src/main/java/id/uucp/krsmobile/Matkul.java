package id.uucp.krsmobile;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

@IgnoreExtraProperties
public class Matkul implements Serializable {

    private String kodeMatkul;
    private String namaMatkul;
    private String jmlSks;
    private String key;
    public Matkul(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public void setKodeMatkul(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getJmlSks() {
        return jmlSks;
    }

    public void setJmlSks(String jmlSks) {
        this.jmlSks = jmlSks;
    }

    @Override
    public String toString(){
        return " "+kodeMatkul+"\n" +
                " "+namaMatkul+"\n" +
                        " "+jmlSks;
    }

    public Matkul(String kd, String nm, String sks){
        kodeMatkul = kd;
        namaMatkul = nm;
        jmlSks = sks;
    }
}
