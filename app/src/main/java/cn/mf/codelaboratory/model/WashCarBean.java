package cn.mf.codelaboratory.model;

/**
 * 项目名称：CodeLaboratory
 *
 * @author menggod
 * @date 2018/1/25
 */
public class WashCarBean {

    public static final int wash_car_tpye1 = 0;
    public static final int wash_car_tab = 1;
    public static final int wash_car_tpye3 = 2;
    public int adapterType;

    public String centerText;

    public WashCarBean setAdapterType(int adapterType) {
        this.adapterType = adapterType;
        return this;
    }

    public WashCarBean setCenterText(String centerText) {
        this.centerText = centerText;
        return this;
    }

}
