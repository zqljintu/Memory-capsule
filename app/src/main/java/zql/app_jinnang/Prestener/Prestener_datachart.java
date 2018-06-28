package zql.app_jinnang.Prestener;

import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.DataChartActivityImp;

public class Prestener_datachart implements PrestenerImp_datachart {
    private DataChartActivityImp dataChartActivityImp;
    private UserSeting userSeting;
    private NoteInfoModelImp noteInfoModelImp;
    public Prestener_datachart(DataChartActivityImp mdataChartActivityImp){
        this.dataChartActivityImp=mdataChartActivityImp;
        userSeting=(UserSeting)dataChartActivityImp.getAddApplication();
        noteInfoModelImp=new NoteInfoModel(dataChartActivityImp.getAddActivityContext());
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        dataChartActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public List<SliceValue> getPieChartNumberfromDatatoActivity() {
        return noteInfoModelImp.getPieChartNumberfromData();
    }

    @Override
    public List<Integer> getPieChartListfromData() {
        return noteInfoModelImp.getPieChartTypeListfromData();
    }

    @Override
    public int getPieChartSumfromDatatoActivity() {
        return noteInfoModelImp.QueryAllNoteSumfromfromData();
    }
}
