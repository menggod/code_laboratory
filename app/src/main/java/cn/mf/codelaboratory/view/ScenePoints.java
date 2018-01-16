//package cn.mf.codelaboratory.view;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//public class ScenePoints implements Parcelable {
//    public static final Creator<ScenePoints> CREATOR = new Creator<ScenePoints>() {
//        @Override
//        public ScenePoints createFromParcel(Parcel source) {
//            return new ScenePoints(source);
//        }
//
//        @Override
//        public ScenePoints[] newArray(int size) {
//            return new ScenePoints[size];
//        }
//    };
//    private String subject;
//    private double x;
//    private Good goods;
//    private double y;
//    private int id;
//    private int scene;
//    private int sort;
//
//    public ScenePoints() {
//    }
//
//    protected ScenePoints(Parcel in) {
//        this.subject = in.readString();
//        this.x = in.readDouble();
//        this.goods = in.readParcelable(Good.class.getClassLoader());
//        this.y = in.readDouble();
//        this.id = in.readInt();
//        this.scene = in.readInt();
//        this.sort = in.readInt();
//    }
//
//    public int getSort() {
//        return sort;
//    }
//
//    public void setSort(int sort) {
//        this.sort = sort;
//    }
//
//    public String getSubject() {
//        return this.subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public double getX() {
//        return this.x;
//    }
//
//    public void setX(double x) {
//        this.x = x;
//    }
//
//    public Good getGoods() {
//        return this.goods;
//    }
//
//    public void setGoods(Good goods) {
//        this.goods = goods;
//    }
//
//    public double getY() {
//        return this.y;
//    }
//
//    public void setY(double y) {
//        this.y = y;
//    }
//
//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getScene() {
//        return this.scene;
//    }
//
//    public void setScene(int scene) {
//        this.scene = scene;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.subject);
//        dest.writeDouble(this.x);
//        dest.writeParcelable(this.goods, flags);
//        dest.writeDouble(this.y);
//        dest.writeInt(this.id);
//        dest.writeInt(this.scene);
//        dest.writeInt(this.sort);
//    }
//}
