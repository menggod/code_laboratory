package cn.mf.codelaboratory.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：CodeLaboratory
 *
 * @author menggod
 * @date 2017/12/20
 */
public class Test1Bean implements Serializable {

    public int AlbumType;
    public int Count;
    public int GroupId;
    public String GroupName;
    public List<PhotoListBean> PhotoList;

    public static class PhotoListBean {
        public int RowNumber;
        public int PhotoId;
        public int ModelId;
        public String ModelName;
        public String StyleName;
        public int StyleId;
        public int ColorId;
        public int InnerColorId;
        public String PhotoName;
        public int GroupId;
        public String GroupName;
        public int PositionId;
        public String PositionName;
        public String PhotoUrl;
        public String Url;
        public String MUrl;
        public int AlbumId;
        public String AlbumName;
        public int UserId;
    }
}
