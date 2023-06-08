package models;

public class Character extends BaseModel {
    private String namSinh;
    private String namMat;
    private String trieuDai;
    public Character(String name, String desc, String namSinh, String namMat, String trieuDai){
        super.name = name;
        super.desc = desc;
        this.namSinh = namSinh;
        this.namMat = namMat;
        this.trieuDai = trieuDai;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNamMat() {
        return namMat;
    }

    public void setNamMat(String namMat) {
        this.namMat = namMat;
    }

    public Character() {
        super.name = null;
        super.desc = null;
        this.namMat = null;
        this.namSinh = null;
        this.trieuDai = null;
    }

    @Override
    public String toString() {
        return "Character{" +
                "namSinh='" + namSinh + '\'' +
                ", namMat='" + namMat + '\'' +
                ", trieuDai='" + trieuDai + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
