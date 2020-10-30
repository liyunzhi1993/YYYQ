package yyyq.ffxiv.model.select;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * UserFFXIVModel
 *
 * @author liyunzhi
 * @date 2018/8/21 14:54
 */
@Getter
@Setter
public class UserFFXIVSelectListModel {
    public Map<Integer,String> areaList;
    public Map<Integer,String> chinaSDServerList;
    public Map<Integer,String> classList;
    public Map<Integer,String> raceList;
}
