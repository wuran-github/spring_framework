package group.bridge.web.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wuran
 * @Created on 2019/3/19
 */
public class SysLog {
    String operator;
    String operationType;
    String table;
    Date date;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString(){
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String str="operator:"+getOperator()+" ";
        str+="operationType:"+getOperationType()+" ";
        str+="table:"+getTable()+" ";
        str+="Date:"+format.format(getDate())+" ";

        return str;
    }
}
