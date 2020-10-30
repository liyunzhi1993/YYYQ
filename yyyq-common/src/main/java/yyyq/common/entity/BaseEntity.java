package yyyq.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 * BaseEntity
 *
 * @author liyunzhi
 * @date 2018/7/19 14:05
 */
@Getter
@Setter
public class BaseEntity {
    private Date createTime;
    private String createBy;
    private Date modifyTime;
    private String modifyBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        if (createTime==null) {
            createTime = Calendar.getInstance().getTime();
        }
        return createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getModifyTime() {
        return Calendar.getInstance().getTime();
    }

    public BaseEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BaseEntity setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public BaseEntity setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public BaseEntity setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
        return this;
    }
}
