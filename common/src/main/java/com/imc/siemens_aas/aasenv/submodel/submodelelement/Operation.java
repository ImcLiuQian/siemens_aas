package com.imc.siemens_aas.aasenv.submodel.submodelelement;

import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.OperationVar;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Operation extends SubmodelElement{
    private List<OperationVar> inputVariable;
    private List<OperationVar> outputVariable;
    private List<OperationVar> inoutputVariable;

    /**
     * 判断传入的inputVars和Operation中的参数个数以及类型是否匹配
     * 复制一份inputVars，如果有匹配的，就让其size - 1，如果最后size可以减少到0，说明可以完全可以匹配上，就return true，否则返回false
     * @return
     */
    public boolean inputParaMatcher(List<OperationVar> inputVars) {
        Iterator<OperationVar> thisInputVarIt = inputVariable.iterator();
        //如果参数个数不一样，直接返回false
        if (inputVars.size() != inputVariable.size()) {
            return false;
        }
        //如果参数个数一样，判断参数类型是否一样，这里复制出一份进行操作
        ArrayList<OperationVar> operationVarArrayList = new ArrayList<>(inputVars);
        while (thisInputVarIt.hasNext()) {
            OperationVar thisInputVar = thisInputVarIt.next();

            Iterator<OperationVar> inputVarIt = operationVarArrayList.iterator();
            while (inputVarIt.hasNext()) {
                OperationVar inputVar = inputVarIt.next();
                if (inputVar.hasSameFormat(thisInputVar)) {//如果传入的inputVars中的inputVar参数类型一样，就直接删除一个元素
                    inputVarIt.remove();
                }
            }
        }
        return operationVarArrayList.size() == 0 ? true : false;
    }
}
